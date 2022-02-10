package com.mk.android.yora.fragments;



import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.MenuRes;
import androidx.annotation.Nullable;

import com.mk.android.yora.R;
import com.mk.android.yora.dialogue.ChangePasswordDialog;
import com.mk.android.yora.infrastructure.User;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends BaseFragmentWithOptions implements View.OnClickListener {

    private static final int REQUEST_SELECT_IMAGE = 100;
    private ImageView avtarImageView;
    private View avatarProgressFrame;
    private File temporaryImage;

    private static final int STATE_VIEWING = 1;
    private static final int STATE_EDITING = 2;
    private static final String BUNDLE_STATE = "BUNDLE_STATE";
    private int currentState;
    private EditText displayNameText;
    private EditText emailText;
    private View changeAvatarButton;
    private ActionMode editProfileActionMode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        if(!isTablet){
           View textFields =  view.findViewById(R.id.fragment_profile_textFields);
           RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) textFields.getLayoutParams();
           params.setMargins(0,params.getMarginStart(),0,0);
           params.setMarginStart(0);
           params.removeRule(RelativeLayout.END_OF);
           params.addRule(RelativeLayout.BELOW,R.id.fragment_profile_change_avatar);
           textFields.setLayoutParams(params);
        }
        avtarImageView = view.findViewById(R.id.fragment_profile_avatar);
        avatarProgressFrame = view.findViewById(R.id.fragment_profile_progress_frame);
        this.changeAvatarButton = view.findViewById(R.id.fragment_profile_change_avatar);
        this.changeAvatarButton.setOnClickListener(this);
        this.displayNameText = view.findViewById(R.id.fragment_profile_display_name_edit);
        this.emailText = view.findViewById(R.id.fragment_profile_email_edit);

        temporaryImage = new File(getActivity().getExternalCacheDir(),"temp-image.jpg");
        avtarImageView.setOnClickListener(this);

        User user = application.getAuth().getUser();
        updateActionBarTitle(user.getDisplayName());
        this.displayNameText.setText(user.getDisplayName());
        this.emailText.setText(user.getEmail());
        avatarProgressFrame.setVisibility(View.GONE);
        changeState(STATE_VIEWING);
        return view;
    }

    private void changeState(int state) {
        if(currentState==state){
            return;
        }
        currentState = state;
        if(state==STATE_EDITING){
            displayNameText.setEnabled(true);
            emailText.setEnabled(true);
            changeAvatarButton.setVisibility(View.GONE);

            editProfileActionMode = baseActivity.getToolbar().startActionMode(new EditProfileActionCallback());

        }else if(state == STATE_VIEWING){
            displayNameText.setEnabled(false);
            emailText.setEnabled(false);
            changeAvatarButton.setVisibility(View.VISIBLE);
            if(editProfileActionMode!=null){
                editProfileActionMode.finish();
                editProfileActionMode = null;
            }

        }else {
            throw new RuntimeException("Invalid State"+state);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_profile, menu);
    }

    @Override
    protected @MenuRes int getMenuId() {
        return R.menu.fragment_page1;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId() == R.id.fragment_profile_menuEdit){
                changeState(STATE_EDITING);
                return true;
            }else if(item.getItemId()== R.id.fragment_profile_menuChangePassword){

                FragmentTransaction transaction = getActivity().getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null);
                ChangePasswordDialog dialog = new ChangePasswordDialog();
                dialog.show(transaction,null);

            }
            return false;
    }

    @Override
    public int getTitle() {
        return R.string.profile;
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if(viewId == R.id.fragment_profile_change_avatar || viewId == R.id.fragment_profile_avatar){
            changeAvatar();
        }
    }

    private void changeAvatar() {
        List<Intent> otherImageCaptureIntents = new ArrayList<>();
        List<ResolveInfo> otherImageCaptureActivities = getActivity()
                .getPackageManager()
                .queryIntentActivities(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),0);

        for(ResolveInfo info : otherImageCaptureActivities){
            Intent captureintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureintent.setClassName(info.activityInfo.packageName,info.activityInfo.name);
            captureintent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(temporaryImage));
            otherImageCaptureIntents.add(captureintent);
        }

        Intent selectImageIntent = new Intent(Intent.ACTION_PICK);
        selectImageIntent.setType("image/*");
        Intent chooser = Intent.createChooser(selectImageIntent,"Choose your Avatar");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS,otherImageCaptureIntents.toArray());
        startActivityForResult(chooser,REQUEST_SELECT_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri tempOutputfile = Uri.fromFile(temporaryImage);
        if(resultCode!=RESULT_OK){
            temporaryImage.delete();
            return;
        }
        if(requestCode == REQUEST_SELECT_IMAGE){
            Uri outputfile;

            if(data !=null &&
                    (data.getAction()==null||
                            !data.getAction().equals(MediaStore.ACTION_IMAGE_CAPTURE))){
                outputfile = data.getData();
            }else {
                outputfile = tempOutputfile;
            }

            new Crop(outputfile).asSquare().output(tempOutputfile).start(getActivity());
//            avtarImageView.setImageResource(0);
//            avtarImageView.setImageURI(tempOutputfile);
        }
        else if(requestCode == Crop.REQUEST_CROP){
            // send tempOutputfile to server as new avatar
            avtarImageView.setImageResource(0);
            avtarImageView.setImageURI(tempOutputfile);
        }
    }

    private class EditProfileActionCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            baseActivity.getMenuInflater().inflate(R.menu.fragment_profile_edit,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(item.getItemId() == R.id.fragment_profile_edit_menuDone){
                //TODO send request for updating username and email
                User user = application.getAuth().getUser();
                user.setDisplayName(displayNameText.getText().toString());
                user.setEmail(emailText.getText().toString());
                changeState(STATE_VIEWING);
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if(currentState!= STATE_VIEWING){
                changeState(STATE_VIEWING);
            }
        }
    }
}
