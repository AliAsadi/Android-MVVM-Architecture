package com.aliasadi.mvvm;

import android.app.Application;


/**
 * Created by Ali Asadi on 10/03/2018.
 */
public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App getInstance() {
        return sInstance;
    }

}
