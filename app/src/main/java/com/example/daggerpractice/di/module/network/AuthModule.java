package com.example.daggerpractice.di.module.network;

import com.example.daggerpractice.di.scope.AuthScope;
import com.example.daggerpractice.network.auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    /**
     * We can access Retrofit here as this module is inside the modules of sub-component which is
     * in turn dependent on AppComponent.
     *
     * @param retrofit
     * @return
     */
    @AuthScope
    @Provides
    static AuthApi providesAuthApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }
}
