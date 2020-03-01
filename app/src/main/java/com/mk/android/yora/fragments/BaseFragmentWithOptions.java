package com.mk.android.yora.fragments;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.MenuRes;
import androidx.annotation.Nullable;


public abstract class BaseFragmentWithOptions extends BaseFragment {



    protected abstract @MenuRes int getMenuId();


    public abstract boolean onOptionsItemSelected(MenuItem item);
}
