package com.dev.bins.shop;

import android.app.Application;

import com.dev.bins.shop.bean.User;
import com.dev.bins.shop.util.UserManager;

import org.litepal.LitePal;

/**
 * Created by bin on 12/03/2017.
 */

public class App extends Application {

    private static App mInstance;
    private User mUser = null;

    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        mInstance = this;
        mUser = UserManager.read(this);
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }
}
