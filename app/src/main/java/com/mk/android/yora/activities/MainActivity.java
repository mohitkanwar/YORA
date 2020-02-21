package com.mk.android.yora.activities;

import android.os.Bundle;

import com.mk.android.yora.R;

public class MainActivity extends BaseAuthenticatedActivity {
    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }
}
