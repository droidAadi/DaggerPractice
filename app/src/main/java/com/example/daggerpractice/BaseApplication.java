package com.example.daggerpractice;


import android.util.Log;

import com.example.daggerpractice.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    private static final String TAG = "BaseApplication";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        /**
         * Base Application is client and AppComponent is server.
         */
        return DaggerAppComponent.builder().application(this).build();
    }
}
