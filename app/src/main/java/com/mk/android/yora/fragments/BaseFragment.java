package com.mk.android.yora.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;

import com.mk.android.yora.infrastructure.YoraApplication;

import java.util.Objects;

public abstract class BaseFragment extends Fragment {
    protected YoraApplication application;
    protected boolean isTablet;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication) getActivity().getApplication();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        isTablet = metrics.widthPixels/metrics.density>=600;
    }

    public abstract int getTitle();
}
