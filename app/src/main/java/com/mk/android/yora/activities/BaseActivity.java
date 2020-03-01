package com.mk.android.yora.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mk.android.yora.R;
import com.mk.android.yora.fragments.BaseFragment;
import com.mk.android.yora.infrastructure.YoraApplication;
import com.mk.android.yora.views.NavDrawer;

public abstract class BaseActivity extends AppCompatActivity {

    protected YoraApplication application;
    protected NavDrawer navDrawer;
    private Toolbar toolbar;

    private BaseFragment activeBaseFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication)getApplication();
    }

    public void switchPage(int containerID, BaseFragment fragment) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerID, fragment);
        transaction.commit();
        this.activeBaseFragment = fragment;
        getSupportActionBar().setTitle(fragment.getTitle());
    }

    public void setNavDrawer(NavDrawer navDrawer) {
        this.navDrawer = navDrawer;
        this.navDrawer.create();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = findViewById(R.id.include_toolbar);

        if(toolbar!=null){
            toolbar.setNavigationIcon(R.drawable.ic_drawer);
            setSupportActionBar(toolbar);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public YoraApplication getYoraApplication() {
        return application;
    }

    public BaseFragment getActiveBaseFragment() {
        return activeBaseFragment;
    }
}
