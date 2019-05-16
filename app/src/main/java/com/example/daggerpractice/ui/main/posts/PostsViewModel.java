package com.example.daggerpractice.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.daggerpractice.SessionManager;
import com.example.daggerpractice.network.main.MainAPI;

import java.util.jar.Manifest;

import javax.inject.Inject;

public class PostsViewModel extends ViewModel {

    private static final String TAG = "PostsViewModel";

    private final SessionManager sessionManager;
    private final MainAPI mainAPI;


    @Inject
    public PostsViewModel(SessionManager sessionManager, MainAPI mainAPI) {
        Log.d(TAG, "PostsViewModel: View Model is working");
        this.sessionManager = sessionManager;
        this.mainAPI = mainAPI;
    }

}
