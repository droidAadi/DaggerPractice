package com.example.daggerpractice.di.module.network;

import com.example.daggerpractice.network.main.MainAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MainAPI providesMainApi(Retrofit retrofit){
        return retrofit.create(MainAPI.class);
    }

}
