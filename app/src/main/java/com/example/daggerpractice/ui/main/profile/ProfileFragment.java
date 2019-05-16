package com.example.daggerpractice.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.daggerpractice.R;
import com.example.daggerpractice.models.User;
import com.example.daggerpractice.ui.auth.AuthResource;
import com.example.daggerpractice.viewmodels.VMProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private static final String TAG = "ProfileFragment";
    @Inject
    VMProviderFactory vmProviderFactory;
    private ProfileViewModel profileViewModel;
    private TextView emailTV, usernameTV, websiteTV;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), TAG, Toast.LENGTH_LONG).show();

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: Profile Fragment View Created");
        emailTV = view.findViewById(R.id.email);
        usernameTV = view.findViewById(R.id.username);
        websiteTV = view.findViewById(R.id.website);
        profileViewModel = ViewModelProviders.of(this, vmProviderFactory)
                .get(ProfileViewModel.class);
        subscribeObservers();
        
    }

    /**
     * Since, Fragment have their own lifecycle different to than of activity, it is advisable to
     * remove the previous owner and observe again.
     */
    private void subscribeObservers(){
        profileViewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        profileViewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch (userAuthResource.status){
                        case AUTHENTICATED:
                            setUserDetails(userAuthResource.data);
                            break;
                        case ERROR:
                            setErrorDetails(userAuthResource.message);
                            break;
                    }
                }

            }
        });
    }

    private void setErrorDetails(String message) {
        emailTV.setText(message);
        usernameTV.setText("Error");
        websiteTV.setText("Error");
    }

    private void setUserDetails(User data) {
        emailTV.setText(data.getEmail());
        usernameTV.setText(data.getUsername());
        websiteTV.setText(data.getWebsite());
    }
}
