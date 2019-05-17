package com.example.daggerpractice.di.component;

import android.app.Application;

import com.example.daggerpractice.BaseApplication;
import com.example.daggerpractice.SessionManager;
import com.example.daggerpractice.di.module.ActivityBuildersModule;
import com.example.daggerpractice.di.module.AppModule;
import com.example.daggerpractice.di.module.viewmodel.factory.ViewModelFactoryModule;
import com.example.daggerpractice.di.scope.AppScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


/*
 * Since we are using Dagger-Android, we will be extending the interface with another one.
 * Android Injector here tells the Dagger that, we will be injecting BaseApplication in this
 * component and BaseApplication is a client for AppComponent.
 * ViewModelFactoryModule will be added to app component as it will be used across the
 * application by multiple view models.
 */
@AppScope
@Component(
        /**
         * The AndroidSupportInjectionModule.class is mandatory to define when using Dagger Android.
        */
        modules = {
                AppModule.class,
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                ViewModelFactoryModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    /*
     * Since we want the Session Manager to have application wide scope, we declare it here as
     * it does not have any dependency in the constructor. Else, we would have created a module
     * for the same.
     */
    SessionManager sessionManager();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        //Mandatory step
        AppComponent build();
    }
}
