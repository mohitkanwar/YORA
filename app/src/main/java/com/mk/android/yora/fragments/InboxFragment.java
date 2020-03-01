package com.mk.android.yora.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.MenuRes;
import androidx.annotation.Nullable;

import com.mk.android.yora.R;

public class InboxFragment extends BaseAuthenticatedFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }

    @Override
    protected @MenuRes int getMenuId() {
        return R.menu.fragment_page1;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId() == R.id.fragment_page1_menu_item1){
                Toast.makeText(getActivity(), "Inbox Frag"+ item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
    }

    @Override
    public int getTitle() {
        return R.string.inbox;
    }
}
