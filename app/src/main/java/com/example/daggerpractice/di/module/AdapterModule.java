package com.example.daggerpractice.di.module;

import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerpractice.ui.main.posts.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * We can include this module either to MainComponent i.e. inside ActivityBuildersModule ->
 * contributeMainActivity or to fragment sub-component i.e MainFragmentBuildersModule ->
 * contributePostsFragment.
 * <p>
 * If we want adapter to be available across activity, declare it in ActivityBuildersModule else if
 * you want it to be available only to Fragment, declare it in MainFragmentBuildersModule.
 */
@Module
public class AdapterModule {

    @Provides
    static PostsRecyclerAdapter provideAdapter() {
        return new PostsRecyclerAdapter();
    }

}
