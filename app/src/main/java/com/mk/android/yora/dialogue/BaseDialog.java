package com.mk.android.yora.dialogue;

import android.app.DialogFragment;
import android.os.Bundle;

import androidx.annotation.Nullable;


import com.mk.android.yora.infrastructure.YoraApplication;

public class BaseDialog extends DialogFragment {
    protected YoraApplication application;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication) getActivity().getApplication();
    }
}
