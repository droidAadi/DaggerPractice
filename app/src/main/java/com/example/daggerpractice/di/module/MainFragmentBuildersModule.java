package com.example.daggerpractice.di.module;

import com.example.daggerpractice.di.scope.PostFragmentScope;
import com.example.daggerpractice.ui.main.posts.PostsFragment;
import com.example.daggerpractice.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This class injects the fragments which are required for Main component i.e. Main Activity.
 */
@Module
public abstract class MainFragmentBuildersModule {


    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @PostFragmentScope
    @ContributesAndroidInjector(
            modules = {AdapterModule.class}
    )
    abstract PostsFragment contributePostsFragment();
}
