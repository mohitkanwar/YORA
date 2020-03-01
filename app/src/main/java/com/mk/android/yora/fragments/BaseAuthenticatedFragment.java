package com.mk.android.yora.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mk.android.yora.activities.LoginActivity;

public abstract class BaseAuthenticatedFragment extends BaseFragmentWithOptions {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!application.getAuth().getUser().isLoggedIn()){
            startActivity(new Intent(getActivity(), LoginActivity.class));
            return;
        }
    }
}
