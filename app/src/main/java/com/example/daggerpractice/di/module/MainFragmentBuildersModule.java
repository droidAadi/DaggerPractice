package com.example.daggerpractice.di.module;

import com.example.daggerpractice.ui.main.profile.ProfileFragment;
import com.example.daggerpractice.ui.main.profile.ProfileViewModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This class injects the fragments which are required for Main component i.e. Main Activity.
 */
@Module
public abstract class MainFragmentBuildersModule {


    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();
}