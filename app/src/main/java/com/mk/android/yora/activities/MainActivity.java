package com.mk.android.yora.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.mk.android.yora.R;
import com.mk.android.yora.fragments.BaseFragment;
import com.mk.android.yora.fragments.Page1Fragment;
import com.mk.android.yora.fragments.Page2Fragment;

public class MainActivity extends BaseAuthenticatedActivity implements View.OnClickListener {
    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main_page1_button).setOnClickListener(this);
        findViewById(R.id.activity_main_page2_button).setOnClickListener(this);

        switchPage(new Page1Fragment());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activity_main_page1_button) {
            switchPage(new Page1Fragment());
        } else if (view.getId() == R.id.activity_main_page2_button) {
            switchPage(new Page2Fragment());
        }
    }

    private void switchPage(BaseFragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_main_fragment_container, fragment);
        transaction.commit();
    }
}
