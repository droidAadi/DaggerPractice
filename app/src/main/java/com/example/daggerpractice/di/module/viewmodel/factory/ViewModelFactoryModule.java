package com.example.daggerpractice.di.module.viewmodel.factory;

import androidx.lifecycle.ViewModelProvider;

import com.example.daggerpractice.viewmodels.VMProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * This class is for ViewModel factory. For ViewModels, we need to create other modules.
 */
@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindVMFactory(VMProviderFactory vmProviderFactory);

    /**
     * We could have written the above method using @Provides as well.
     * But since, we are not doing anything and just returning the argument, so we are using @Binds
     * instead which is more optimized in this case.
     */

   /* @Provides
    static ViewModelProvider.Factory providesVMFactory(VMProviderFactory vmProviderFactory){
        return vmProviderFactory;
    }*/
}
