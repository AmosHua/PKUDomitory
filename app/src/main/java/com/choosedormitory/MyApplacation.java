package com.choosedormitory;

import android.app.Application;

/**
 * Created by amoshua on 23/11/2017.
 */

public class MyApplacation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetUtil.initHttpManager(getApplicationContext());
    }
}
