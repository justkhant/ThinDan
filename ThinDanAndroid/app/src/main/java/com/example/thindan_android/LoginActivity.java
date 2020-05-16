package com.example.thindan_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;


import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    private TextView info, title1, title2;
    private ImageView profile;
    private LoginButton facebookLogin;
    private TextInputLayout username_layout;

    Animation rightAnim;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        info = findViewById(R.id.info);
        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);
        username_layout = findViewById(R.id.username);

        //Animations
        rightAnim = AnimationUtils.loadAnimation(this, R.anim.right_animation);
        title1.setAnimation(rightAnim);
        title2.setAnimation(rightAnim);

        //profile = findViewById(R.id.profile);
        facebookLogin = findViewById(R.id.login);

        callbackManager = CallbackManager.Factory.create();

        facebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override public void onSuccess(LoginResult loginResult) {
                info.setText("User Id : " + loginResult.getAccessToken().getUserId());
                String imageURL = "https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() + "/picture?return_ssl_resources=1";
                Picasso.get().load(imageURL).into(profile);
            }
            @Override public void onCancel() {
                info.setText("Login attempt canceled.");
            }
            @Override public void onError(FacebookException error) {
                info.setText("login attempt failed.");
            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void onSignUpClick(View v) {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }

    public void onLoginClick(View v) {
        username_layout.setErrorEnabled(true);
        username_layout.setError("Invalid username!");
    }
}
