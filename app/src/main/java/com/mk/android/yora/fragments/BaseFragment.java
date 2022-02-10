package com.mk.android.yora.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;

import com.mk.android.yora.activities.BaseActivity;
import com.mk.android.yora.infrastructure.YoraApplication;

import java.util.Objects;

public abstract class BaseFragment extends Fragment {
    protected YoraApplication application;
    protected boolean isTablet;
    protected BaseActivity baseActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication) getActivity().getApplication();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        isTablet = metrics.widthPixels/metrics.density>=600;
        this.baseActivity = (BaseActivity)getActivity();
    }

    public abstract int getTitle();
    public void updateActionBarTitle(String title){
        baseActivity.updateActionBarTitle(title);
    }
}
