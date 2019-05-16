package com.example.daggerpractice.di.module;

import com.example.daggerpractice.di.module.network.AuthModule;
import com.example.daggerpractice.di.module.network.MainModule;
import com.example.daggerpractice.di.module.viewmodel.AuthViewModelModule;
import com.example.daggerpractice.di.module.viewmodel.MainViewModelsModule;
import com.example.daggerpractice.ui.auth.AuthActivity;
import com.example.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Classes with @ContributesAndroidInjector needs to be abstract.
 */
@Module
public abstract class ActivityBuildersModule {

    /**
     * This tells Dagger that AuthActivity is potential client in which dependencies can be injected.
     * And with this we do not need to go inside AuthActivity and inject anything.
     * <p>
     * Only the AuthActivity sub-component will be able to use AuthViewModel as it is only for
     * AuthActivity.
     * NOTE: Do not put any @Provides method in ActivityBuildersModule as it is not the right way.
     * Also, declare all the activities inside this module just like below.
     *
     * @return
     * @ContributesAndroidInjector means we already have a sub-component. In this case AuthActivity
     * sub-component which has AuthViewModel module.
     * You can manually do it as well. But in that case, you have to manually declare the
     * sub-component inside app component as it is a parent component.
     *
     * These modules now exist inside the AuthActivity sub-component.
     *
     * We created AuthModule as it required a dependency of AuthAPI.
     */
    @ContributesAndroidInjector(
            modules = {AuthViewModelModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    /**
     * This sub-component contains the fragments, hence MainFragmentBuildersModule module is added
     *
     * One more thing to note is that the dependencies provided by all the modules which are declared
     * below in modules section will be available across complete Main sub-component.
     * @return
     */
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class,
                    MainModule.class}
    )
    abstract MainActivity contributeMainActivity();

}
