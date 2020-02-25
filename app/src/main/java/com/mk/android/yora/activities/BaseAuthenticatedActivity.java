package com.mk.android.yora.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;


import com.mk.android.yora.R;

public abstract class BaseAuthenticatedActivity extends BaseActivity {
    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!application.getAuth().getUser().isLoggedIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        onYoraCreate(savedInstanceState);

    }

    protected abstract void onYoraCreate(Bundle savedInstanceState);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_authenticated, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.isCheckable()) {
            item.setChecked(!item.isChecked());
        }


        if (item.getItemId()==R.id.activity_authenticated_menu_settings){
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
