package com.mk.android.yora.activities;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;

public abstract class BaseAuthenticatedActivity extends BaseActivity {
    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!application.getAuth().getUser().isLoggedIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        onYoraCreate(savedInstanceState);
    }

    protected abstract void onYoraCreate(Bundle savedInstanceState);
}
