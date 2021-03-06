package com.example.daggerpractice.di.module.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.daggerpractice.di.ViewModelKey;
import com.example.daggerpractice.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * This class will provide the ViewModels required in Auth sub-component.
 *
 * @IntoMap : This is required for linking view-model with Dagger. This is multi-binding.
 * You can also add scope here if you want it to be limited to any certain component scope.
 *
 * For eg, if you want AuthViewModelModule to be available only across Auth Scope, then you can add
 * @AuthScope
 */
@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);
}
