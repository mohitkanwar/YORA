package com.mk.android.yora.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.MenuRes;
import androidx.annotation.Nullable;

import com.mk.android.yora.R;

public class ProfileFragment extends BaseFragmentWithOptions {

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
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_page1, menu);
    }

    @Override
    protected @MenuRes int getMenuId() {
        return R.menu.fragment_page1;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId() == R.id.fragment_page1_menu_item1){
                Toast.makeText(getActivity(), "Inbox Frag"+ item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
    }

    @Override
    public int getTitle() {
        return R.string.profile;
    }
}
