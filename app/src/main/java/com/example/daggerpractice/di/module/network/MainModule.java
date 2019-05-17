package com.example.daggerpractice.di.module.network;

import com.example.daggerpractice.di.scope.MainScope;
import com.example.daggerpractice.network.main.MainAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class MainModule {

    @MainScope
    @Provides
    static MainAPI providesMainApi(Retrofit retrofit){
        return retrofit.create(MainAPI.class);
    }
}
