package com.mk.android.yora.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mk.android.yora.R;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private Button loginButton;
    private Callbacks callbacks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = view.findViewById(R.id.fragment_login_loginButton);
        loginButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            application.getAuth().getUser().setLoggedIn(true);
            callbacks.onLoggedIn();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbacks = (Callbacks) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @Override
    public int getTitle() {
        return R.string.log_in;
    }

    public interface Callbacks {
        void onLoggedIn();
    }
}
