package com.mk.android.yora.views;

import android.view.View;
import android.widget.Toast;

import com.mk.android.yora.R;
import com.mk.android.yora.activities.BaseActivity;
import com.mk.android.yora.activities.MainActivity;

public class MainNavDrawer extends NavDrawer {
    public MainNavDrawer(final BaseActivity activity) {
        super(activity);

        addItem(new ActivityNavDrawerItem(MainActivity.class, "Inbox",null, R.drawable.ic_drawer,R.id.include_main_nav_drawer_top_items));
        addItem(new BasicNavDrawerItem("Logout" ,null, R.drawable.ic_drawer,R.id.include_main_nav_drawer_bottom_items){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Toast.makeText(activity,"You have logged out",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
