package com.mk.android.yora.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mk.android.yora.infrastructure.YoraApplication;

public abstract class BaseActivity extends AppCompatActivity {
    protected YoraApplication application;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication)getApplication();
    }
}
