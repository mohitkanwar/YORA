package com.mk.android.yora.infrastructure;

import android.content.Context;

public class Auth {
    private User user;
    private final Context context;

    public Auth(Context context) {
        this.context = context;
        user = new User();
    }

    public User getUser() {
        return user;
    }
}
