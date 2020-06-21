package com.example.thindanAndroid.components.verification;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thindanAndroid.R;

public class VerificationCodeActivity extends AppCompatActivity implements TextWatcher {

    private static final int BACK_TO_VERIFICATION_METHOD_ACTIVITY_ID = 0;
    EditText editText_one, editText_two, editText_three, editText_four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);

        editText_one = findViewById(R.id.editTextone);
        editText_two = findViewById(R.id.editTexttwo);
        editText_three = findViewById(R.id.editTextthree);
        editText_four = findViewById(R.id.editTextfour);

        editText_one.addTextChangedListener(this);
        editText_two.addTextChangedListener(this);
        editText_three.addTextChangedListener(this);
        editText_four.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    //auto focus on the 4 digit input as you type
    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 1) {
            if (editText_one.length() == 1) {
                editText_two.requestFocus();
            }

            if (editText_two.length() == 1) {
                editText_three.requestFocus();
            }
            if (editText_three.length() == 1) {
                editText_four.requestFocus();
            }
        } else if (s.length() == 0) {
            if (editText_four.length() == 0) {
                editText_three.requestFocus();
            }
            if (editText_three.length() == 0) {
                editText_two.requestFocus();
            }
            if (editText_two.length() == 0) {
                editText_one.requestFocus();
            }
        }
    }

    //action for the back button
    public void onBackClick(View v) {
        Intent i = new Intent(this, VerificationMethodActivity.class);
        startActivityForResult(i, BACK_TO_VERIFICATION_METHOD_ACTIVITY_ID);
    }
}
