package com.example.thindanAndroid.components.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thindanAndroid.R;
import com.example.thindanAndroid.components.login.LoginActivity;
import com.example.thindanAndroid.utils.AccessWebTask;
import com.example.thindanAndroid.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private static final int BACK_TO_LOGIN_ACTIVITY_ID = 0;
    private static final int VERIFICATION_METHOD_ACTIVITY_ID = 1;

    private TextInputEditText username, password, confirmPassword;
    private TextInputLayout usernameLayout, passwordLayout, confirmPasswordLayout;
    private Map<String, String> valuesMap = new HashMap<>();

    private boolean validUsername = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameLayout = findViewById(R.id.username_layout);
        usernameLayout.setEndIconVisible(false);
        passwordLayout = findViewById(R.id.password_layout);
        confirmPasswordLayout = findViewById(R.id.confirm_password_layout);

        username = findViewById(R.id.username);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {

                //check dynamically whether the username is okay!
                boolean exists = checkForTutor(s.toString());
                //set check icon if the username doesn't already exist and is > 3
                if (!exists && s.length() >= 3) {
                    usernameLayout.setEndIconVisible(true);
                    validUsername = true;
                } else {
                    usernameLayout.setEndIconVisible(false);
                    validUsername = false;
                }
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
        confirmPassword = findViewById(R.id.confirm_password);
        //to reset error messages when you type
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                confirmPasswordLayout.setErrorEnabled(false);
            }
        });


    }

    //action for back button
    public void onBackClick(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivityForResult(i, BACK_TO_LOGIN_ACTIVITY_ID);
    }

    //action of signup button
    public void onSignUpClick(View v) throws JSONException {
        //clear errors
        usernameLayout.setErrorEnabled(false);
        passwordLayout.setErrorEnabled(false);
        confirmPasswordLayout.setErrorEnabled(false);

        //get text in TextViews
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();
        String confirmInput = confirmPassword.getText().toString();

        //error checking
        boolean hasError = false;
        if (usernameInput.length() < 3) {
            displayErrorMessage(usernameLayout, "username must be at least 3 characters long!");
            hasError = true;
        } else if (!validUsername){
            displayErrorMessage(usernameLayout, "username already exists!");
            hasError = true;
        }

        if (passwordInput.length() == 0) {
            displayErrorMessage(passwordLayout, "please enter a password!");
            hasError = true;
        } else if (!confirmInput.equals(passwordInput)) {
            displayErrorMessage(confirmPasswordLayout, "passwords don't match!");
            hasError = true;
        }

        //if no errors, create new tutor
        if (!hasError) {
            valuesMap.put("username", usernameInput);
            valuesMap.put("password", passwordInput);
            String jsonString = Utils.convertToJsonString(valuesMap);
            createNewTutor(jsonString);
            Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_LONG);
            Intent i = new Intent(this, LoginActivity.class);
            startActivityForResult(i, BACK_TO_LOGIN_ACTIVITY_ID);
        }

        /*
        Intent i = new Intent(this, VerificationMethodActivity.class);
        startActivityForResult(i, VERIFICATION_METHOD_ACTIVITY_ID);
         */

    }

    //display error message on text fields
    private void displayErrorMessage(TextInputLayout til, String message) {
        til.setErrorEnabled(true);
        til.setError(message);
    }

    //Method to connect to node express and make new tutor
    private void createNewTutor(String jsonInputString) {
        try {
            // 10.0.2.2 is the host machine as represented by Android Virtual Device
            URL url = new URL("http://10.0.2.2:3000/createNewTutor");
            AccessWebTask task = new AccessWebTask("POST", jsonInputString);
            task.execute(url);
            return;
        } catch (Exception e) {
            // empty return upon encountering an exception
            e.printStackTrace();
            return;
        }
    }

    // This helper method checks if a username already exists in the database
    private boolean checkForTutor(String username) {
        try {
            // 10.0.2.2 is the host machine as represented by Android Virtual Device
            URL url = new URL("http://10.0.2.2:3000/checkForTutor?username=" + username);
            AccessWebTask task = new AccessWebTask("GET");
            task.execute(url);
            // waits for doInBackground to finish, then gets the return value
            JSONObject check = task.get();
            return check.getBoolean("exists");

        } catch (Exception e) {
            e.printStackTrace();
            return false; // return empty JSON Object upon encountering an exception
        }
    }


}