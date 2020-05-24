package com.example.thindan_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.example.thindan_android.utils.AccessWebTask;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;


import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final int SIGN_UP_ACTIVITY_ID = 0;
    private static final int MAIN_ACTIVITY = 1;

    private TextView info, title1, title2;
    private LoginButton facebookLogin;
    private TextInputLayout usernameLayout, passwordLayout;
    private TextInputEditText username, password;

    private Animation rightAnim;
    CallbackManager callbackManager;
    private ProfileTracker mProfileTracker;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        info = findViewById(R.id.info);
        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);
        usernameLayout = findViewById(R.id.username_layout);
        passwordLayout = findViewById(R.id.password_layout);
        username = findViewById(R.id.username);
        //to reset error messages when you type
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                usernameLayout.setErrorEnabled(false);
            }
        });

        password = findViewById(R.id.password);
        //to reset error messages when you type
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                passwordLayout.setErrorEnabled(false);
            }
        });

        //Animations
        rightAnim = AnimationUtils.loadAnimation(this, R.anim.right_animation);
        title1.setAnimation(rightAnim);
        title2.setAnimation(rightAnim);

        //profile = findViewById(R.id.profile);
        facebookLogin = findViewById(R.id.login);
        callbackManager = CallbackManager.Factory.create();

        facebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            private boolean fbUser;
            private String userID;
            private String userAvatar;
            private LoginResult mloginResult;

            @Override
            public void onSuccess(LoginResult loginResult) {
                mloginResult = loginResult;
                fbUser = true;
                userID = loginResult.getAccessToken().getUserId();
                userAvatar = "https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() + "/picture?return_ssl_resources=1";

                GraphRequest request = GraphRequest.newMeRequest( AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                            @Override public void onCompleted(JSONObject object,GraphResponse response) {
                                try {
                                    String  name = object.getString("name"); // User's full name is acquired here.
                                    // Data to be passed from FB login.
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("fbUser?", true);
                                    intent.putExtra("userID", mloginResult.getAccessToken().getUserId());
                                    intent.putExtra("userAvatar", userAvatar);
                                    intent.putExtra("fullname", name);

                                    startActivity(intent);
                                    Log.e("User's Full Name ", name);
                                   // fullname = name;
                                    //userProfile[0] = name;
                                    //intent.putExtra("fullname", name);

                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                            }

                        });

                request.executeAsync();

            }

            @Override public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login attempt canceled.", Toast.LENGTH_SHORT);
            }
            @Override public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Login error.", Toast.LENGTH_SHORT);
            }

        });

    }

    // Private method to handle Facebook login and callback
    private void onFblogin() {
        callbackManager = CallbackManager.Factory.create();

        // Set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "user_photos", "public_profile"));

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        try {
                            if (Profile.getCurrentProfile() == null) {
                                mProfileTracker = new ProfileTracker() {
                                    @Override
                                    protected void onCurrentProfileChanged(Profile profile_old, Profile profile_new) {
                                        // profile2 is the new profile
                                        profile = profile_new;
                                        mProfileTracker.stopTracking();
                                    }
                                };
                                mProfileTracker.startTracking();
                            } else {
                                profile = Profile.getCurrentProfile();
                            }

                            GraphRequest request = GraphRequest.newMeRequest(
                                    loginResult.getAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(JSONObject object, GraphResponse response) {
                                            Log.v("FACEBOOK LOGIN", response.toString());
                                            // Application code
                                            try {
                                                String fb_id = object.getString("id");
                                                String fb_name = object.getString("name");
                                                String profilePicUrl = "https://graph.facebook.com/" + fb_id + "/picture?width=200&height=200";
                                                String fb_gender = object.getString("gender");
                                                String fb_email = object.getString("email");
                                                String fb_birthday = object.getString("birthday");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            //use shared preferences here
                                        }
                                    });
                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "id,name,email,gender,birthday,picture.type(small)");
                            request.setParameters(parameters);
                            request.executeAsync();

                            //go to Home page
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } catch (Exception e) {
                            Log.d("ERROR", e.toString());
                        }
                    }

                    @Override
                    public void onCancel() {
                        // Log.d(TAG_CANCEL, "On cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        //Log.d(TAG_ERROR, error.toString());
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if you don't add following block,
        // your registered `FacebookCallback` won't be called
        if (callbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }

    public void onSignUpClick(View v) {

        Intent i = new Intent(this, SignupActivity.class);
        startActivityForResult(i, SIGN_UP_ACTIVITY_ID);
    }

    public void onLoginClick(View v) throws JSONException {
        //clear errors
        usernameLayout.setErrorEnabled(false);
        passwordLayout.setErrorEnabled(false);

        //get text in TextViews
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();

        boolean hasError = false;
        if (usernameInput.length() == 0) {
            displayErrorMessage(usernameLayout, "please enter a username!");
            hasError = true;
        }
        if (passwordInput.length() == 0) {
            displayErrorMessage(passwordLayout, "please enter a password!");
            hasError = true;
        }

        if (!hasError) {
            JSONObject tutor = getTutor(usernameInput);
            //check if user exists and if password matches
            if (tutor.length() == 0 || !tutor.getString("password").equals(passwordInput)) {
                password.setText("");
                displayErrorMessage(passwordLayout, "Invalid user or password!");
            } else {
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("userID", usernameInput);
                i.putExtra("fullname", usernameInput);
                i.putExtra("avatar", "");
                startActivityForResult(i, MAIN_ACTIVITY);
            }
        }

    }

    //display error message on text fields
    private void displayErrorMessage(TextInputLayout til, String message) {
        til.setErrorEnabled(true);
        til.setError(message);
    }

    // This helper method checks if a username already exists in the database
    private JSONObject getTutor(String username) throws JSONException {
        try {
            // 10.0.2.2 is the host machine as represented by Android Virtual Device
            URL url = new URL("http://10.0.2.2:3000/getTutor?username=" + username);
            AccessWebTask task = new AccessWebTask("GET");
            task.execute(url);
            // waits for doInBackground to finish, then gets the return value
            return task.get();
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject(); // return empty JSON Object upon encountering an exception
        }
    }



}
