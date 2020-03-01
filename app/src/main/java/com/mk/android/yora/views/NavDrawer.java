package com.mk.android.yora.views;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.mk.android.yora.R;
import com.mk.android.yora.activities.BaseActivity;
import com.mk.android.yora.fragments.BaseFragment;

import java.util.ArrayList;

public class NavDrawer {
    private ArrayList<NavDrawerItem> items;

    private NavDrawerItem selectedItem;

    protected BaseActivity activity;
    protected DrawerLayout drawerLayout;
    protected ViewGroup navDrawerView;

    public NavDrawer(BaseActivity activity) {
        this.activity = activity;
        items = new ArrayList<>();
        drawerLayout = activity.findViewById(R.id.drawer_layout);
        navDrawerView = activity.findViewById(R.id.nav_drawer);
        if(drawerLayout==null|| navDrawerView==null){
            throw new RuntimeException("Unable to find a drawerlayout with id drawer_layout or nav_drawer, or something like that. just check this class");
        }

        Toolbar toolbar = activity.getToolbar();
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOpen(!isOpen());
            }
        });
    }

    public void addItem(NavDrawerItem item){
        items.add(item);
        item.navDrawer = this;
    }

    public boolean isOpen(){
        return drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    public void setOpen(boolean isOpen){
        if(isOpen){
            drawerLayout.openDrawer(GravityCompat.START);
        }else {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

    }

    public void setSelectedItem(NavDrawerItem selectedItem) {
        if(this.selectedItem!=null){
            this.selectedItem.setSelected(false);
        }
        this.selectedItem = selectedItem;
        this.selectedItem.setSelected(true);
    }

    public void create(){
        LayoutInflater inflater = activity.getLayoutInflater();
        for(NavDrawerItem item : items){
            item.inflate(inflater,navDrawerView);
        }
    }


    public static abstract class NavDrawerItem {
        protected NavDrawer navDrawer;
        public abstract void inflate(LayoutInflater inflater,ViewGroup container);
        public abstract void setSelected(boolean isSelected);
    }

    public static class BasicNavDrawerItem extends NavDrawerItem implements View.OnClickListener {

        private String text;
        private String badgeValue;
        private int iconId;
        private int containerId;

        private ImageView icon;
        private TextView textView;
        private TextView badgeView;
        private View view;
        private int defaultTextColourId;

        public BasicNavDrawerItem(String text, String badgeValue, int iconId, int containerId) {
            this.text = text;
            this.badgeValue = badgeValue;
            this.iconId = iconId;
            this.containerId = containerId;
        }


        @Override
        public void inflate(LayoutInflater inflater, ViewGroup navDrawerView) {
            ViewGroup container = navDrawerView.findViewById(containerId);
            if(container==null){
                throw new RuntimeException("Nav drawer item "+text+" Could not be attached to the Viewgroup. View not found.");
            }
            view = inflater.inflate(R.layout.list_item_nav_drawer,container,false);
            container.addView(view);
            view.setOnClickListener(this);
            icon = view.findViewById(R.id.list_item_nav_drawer_icon);
            textView = view.findViewById(R.id.list_item_nav_drawer_text);
            badgeView = view.findViewById(R.id.list_item_nav_drawer_badge);
            defaultTextColourId = textView.getCurrentTextColor();

            icon.setImageResource(iconId);
            textView.setText(text);

            if(badgeValue!=null){
                badgeView.setText(badgeValue);
            }else {
                badgeView.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            navDrawer.setSelectedItem(this);
        }

        @Override
        public void setSelected(boolean isSelected) {
            if(isSelected){
                view.setBackgroundResource(R.drawable.list_item_nav_drawer_selected_item_background);
                textView.setTextColor(navDrawer.activity.getColor(R.color.list_item_nav_drawer_selected_item_text_color));
            }
            else {
                view.setBackground(null);
                textView.setTextColor(defaultTextColourId);

            }
        }

        public void setText(String text) {
            this.text = text;
            if(view!=null){
                textView.setText(text);
            }
        }

        public void setBadgeValue(String badgeValue) {
            this.badgeValue = badgeValue;
            if(view!=null){
                if(badgeValue!=null){
                    badgeView.setVisibility(View.VISIBLE);
                    badgeView.setText(badgeValue);
                }else {
                    badgeView.setVisibility(View.GONE);
                }
            }
        }

        public void setIconId(int iconId) {
            this.iconId = iconId;
            if(view!=null){
                icon.setImageResource(iconId);
            }
        }
    }

    public static class FragmentNavDrawerItem extends BasicNavDrawerItem {
        private final BaseFragment targetFragment;
        private final int targetContainerId;
        public FragmentNavDrawerItem(BaseFragment targetFragment,int targetContainerId, String text, String badgeValue, int iconId, int containerId) {
            super(text, badgeValue, iconId, containerId);
            this.targetFragment = targetFragment;
            this.targetContainerId = targetContainerId;
        }

        @Override
        public void inflate(LayoutInflater inflater, ViewGroup navDrawerView) {
            super.inflate(inflater, navDrawerView);
            if(targetFragment.equals(navDrawer.activity.getActiveBaseFragment())){
                this.navDrawer.setSelectedItem(this);
            }
        }

        @Override
        public void onClick(View v) {

            navDrawer.setOpen(false);
            if(targetFragment.equals(navDrawer.activity.getActiveBaseFragment())){
                return;
            }
            super.onClick(v);
            //TODO Animations
            navDrawer.activity.switchPage(targetContainerId,targetFragment);
        }
    }
}
