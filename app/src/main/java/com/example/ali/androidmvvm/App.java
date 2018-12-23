package com.example.ali.androidmvvm;

import android.app.Application;


/**
 * Created by Ali Esa Assadi on 10/03/2018.
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
