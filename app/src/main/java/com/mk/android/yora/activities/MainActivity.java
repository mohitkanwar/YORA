package com.mk.android.yora.activities;

import android.os.Bundle;
import android.view.View;

import com.mk.android.yora.R;
import com.mk.android.yora.fragments.InboxFragment;
import com.mk.android.yora.fragments.Page1Fragment;
import com.mk.android.yora.fragments.Page2Fragment;
import com.mk.android.yora.views.MainNavDrawer;


public class MainActivity extends BaseAuthenticatedActivity implements View.OnClickListener {
    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        setNavDrawer(new MainNavDrawer(this));

        switchPage(R.id.activity_main_fragment_container,new Page1Fragment());
        findViewById(R.id.activity_main_page1_button).setOnClickListener(this);
        findViewById(R.id.activity_main_page2_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activity_main_page1_button) {
            switchPage(R.id.activity_main_fragment_container,new InboxFragment());

        } else if (view.getId() == R.id.activity_main_page2_button) {
            switchPage(R.id.activity_main_fragment_container,new Page2Fragment());

        }
    }


}
