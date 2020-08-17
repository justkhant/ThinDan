package com.example.thindanAndroid.components.verification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thindanAndroid.R;
import com.example.thindanAndroid.components.signup.SignupActivity;

public class VerificationMethodActivity extends AppCompatActivity {

    private static final int BACK_TO_SIGNUP_ACTIVITY_ID = 0;
    private static final int VERIFICATION_CODE_ACTIVITY_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_method);
    }

    //action for the back button
    public void onBackClick(View v) {
        Intent i = new Intent(this, SignupActivity.class);
        startActivityForResult(i, BACK_TO_SIGNUP_ACTIVITY_ID);
    }

    //action for continue button
    public void onContinueClick(View v) {
        Intent i = new Intent (this, VerificationCodeActivity.class);
        startActivityForResult(i, VERIFICATION_CODE_ACTIVITY_ID);
    }

}
