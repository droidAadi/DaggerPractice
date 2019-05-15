package com.example.daggerpractice.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.daggerpractice.R;
import com.example.daggerpractice.models.User;
import com.example.daggerpractice.ui.main.MainActivity;
import com.example.daggerpractice.viewmodels.VMProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";

    /**
     * Since we need to use ViewModel inside this activity, so we are injecting it here.
     */
    @Inject
    VMProviderFactory vmProviderFactory;
    private AuthViewModel authViewModel;
    @Inject
    Drawable mLogo;
    @Inject
    RequestManager mRequestManager;

    private EditText userIdEditText;
    private Button loginBtn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        authViewModel = ViewModelProviders.of(this, vmProviderFactory).
                get(AuthViewModel.class);

        progressBar = findViewById(R.id.progress_bar);
        userIdEditText = findViewById(R.id.user_id_input);
        loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
        setLogo();
        subscribeObserver();
    }

    private void attemptLogin() {

        if (TextUtils.isEmpty(userIdEditText.getText().toString())) {
            return;
        }
        authViewModel.authenticateWithId(Integer.parseInt(userIdEditText.getText().toString()));
    }

    private void subscribeObserver() {
        authViewModel.observerAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING:
                            showProgressBar(true);
                            break;
                        case AUTHENTICATED:
                            showProgressBar(false);
                            onLoginSuccess();
                            Log.d(TAG, "onChanged: LOGIN SUCCESS " +
                                    userAuthResource.data.getEmail());
                            break;
                        case ERROR:
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: LOGIN ERROR " +
                                    userAuthResource.message + "\nDid you enter the number " +
                                    "between 1 to 10?");
                            break;
                        case NOT_AUTHENTICATED:
                            showProgressBar(false);
                            break;
                    }
                }
            }
        });
    }

    private void onLoginSuccess(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setLogo() {
        mRequestManager.load(mLogo).into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }
}
