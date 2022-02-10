package com.mk.android.yora.dialogue;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mk.android.yora.R;

public class ChangePasswordDialog extends BaseDialog implements View.OnClickListener {
    private EditText currentPassword;
    private EditText newPassword;
    private EditText confirmPassword;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_change_password,null,false);
        currentPassword = dialogView.findViewById(R.id.change_password_dialog_current_pwd);
        newPassword = dialogView.findViewById(R.id.change_password_dialog_new_pwd);
        confirmPassword = dialogView.findViewById(R.id.change_password_dialog_confirm_pwd);
        if(!application.getAuth().getUser().isHasPassword()){
            currentPassword.setVisibility(View.GONE);
        }
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(dialogView)
                .setPositiveButton("Update",null)
                .setNegativeButton("Cancel",null)
                .setTitle("Change Password")
                .show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(this);
        return dialog;
    }

    @Override
    public void onClick(View v) {
        //TODO update password on server
        Toast.makeText(getActivity(),"Password updated", Toast.LENGTH_SHORT).show();
        dismiss();
    }
}
