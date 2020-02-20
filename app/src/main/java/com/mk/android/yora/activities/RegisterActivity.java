package com.mk.android.yora.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.mk.android.yora.R;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText userNameText;
    private EditText emailText;
    private EditText password;
    private Button registerButton;
    private View progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        userNameText = findViewById(R.id.activity_register_username);
        emailText = findViewById(R.id.activity_register_email);
        password = findViewById(R.id.activity_register_password);
        registerButton = findViewById(R.id.activity_register_register_button);
        progressBar = findViewById(R.id.activity_register_progressbar);
        progressBar.setVisibility(View.GONE);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == registerButton) {
            application.getAuth().getUser().setLoggedIn(true);
            setResult(RESULT_OK);
            finish();
        }
    }
}
