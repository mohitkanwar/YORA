package com.mk.android.yora.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mk.android.yora.R;
import com.mk.android.yora.activities.BaseActivity;
import com.mk.android.yora.fragments.ContactsFragment;
import com.mk.android.yora.fragments.InboxFragment;
import com.mk.android.yora.fragments.ProfileFragment;
import com.mk.android.yora.fragments.SentMessagesFragment;
import com.mk.android.yora.infrastructure.User;

public class MainNavDrawer extends NavDrawer {

    private final TextView displayNameText;
    private final ImageView avatarImage;

    public MainNavDrawer(final BaseActivity activity) {
        super(activity);


        addItem(new FragmentNavDrawerItem(new InboxFragment(), R.id.activity_main_fragment_container,"Inbox",null, R.drawable.ic_drawer,R.id.include_main_nav_drawer_top_items));
        addItem(new FragmentNavDrawerItem(new SentMessagesFragment(),R.id.activity_main_fragment_container, "Sent Items",null, R.drawable.ic_drawer,R.id.include_main_nav_drawer_top_items));
        addItem(new FragmentNavDrawerItem(new ContactsFragment(), R.id.activity_main_fragment_container,"Contacts",null, R.drawable.ic_drawer,R.id.include_main_nav_drawer_top_items));
        addItem(new FragmentNavDrawerItem(new ProfileFragment(),R.id.activity_main_fragment_container, "Profile",null, R.drawable.ic_drawer,R.id.include_main_nav_drawer_top_items));
        addItem(new BasicNavDrawerItem("Logout" ,null, R.drawable.ic_drawer,R.id.include_main_nav_drawer_bottom_items){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Toast.makeText(activity,"You have logged out",Toast.LENGTH_SHORT).show();
            }
        });
        displayNameText = navDrawerView.findViewById(R.id.include_main_nav_drawer_displayname);
        avatarImage = navDrawerView.findViewById(R.id.include_main_nav_drawer_avatar);

        User loggedinUser = activity.getYoraApplication().getAuth().getUser();
        displayNameText.setText(loggedinUser.getDisplayName());

        //TODO change avatar image url from logged in user
    }

}
