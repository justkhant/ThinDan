package com.example.thindanAndroid.components.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thindanAndroid.R;
import com.example.thindanAndroid.components.login.LoginActivity;
import com.example.thindanAndroid.components.main.MainActivity;
import com.facebook.AccessToken;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DURATION = 3000;

    private Animation topAnim, bottomAnim;
    private TextView name, description;

    public Boolean logged_in_at_startup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Views
        name = findViewById(R.id.splashscreen_name);
        description = findViewById(R.id.splashscreen_description);

        //set animations
        name.setAnimation(topAnim);
        description.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (loggedIn()){
                    logged_in_at_startup = true;
                    Intent go_to_main = new Intent(SplashScreenActivity.this, MainActivity.class);
                    go_to_main.putExtra("logged_in_at_startup",logged_in_at_startup);
                    startActivity(go_to_main);
                } else {
                    Intent go_to_login = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(go_to_login);
                }
                finish();
            }
        }, SPLASH_SCREEN_DURATION);
    }

    private boolean loggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
}
