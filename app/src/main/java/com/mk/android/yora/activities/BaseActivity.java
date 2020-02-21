package com.mk.android.yora.activities;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mk.android.yora.infrastructure.YoraApplication;

public abstract class BaseActivity extends Activity {
    protected YoraApplication application;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication)getApplication();
    }
}
