package com.mk.android.yora.fragments;

import android.app.Fragment;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mk.android.yora.infrastructure.YoraApplication;

import java.util.Objects;

public abstract class BaseFragment extends Fragment {
    protected YoraApplication application;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication) getActivity().getApplication();
    }

    public abstract int getTitle();
}
