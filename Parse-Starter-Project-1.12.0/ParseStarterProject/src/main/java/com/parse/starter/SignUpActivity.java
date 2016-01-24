package com.parse.starter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Activity which displays a login screen to the user.
 */
public class SignUpActivity extends Activity {
    // UI references.
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordAgainEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_up_page);

        // Set up the signup form.
        usernameEditText = (EditText) findViewById(R.id.usernameText);
        passwordEditText = (EditText) findViewById(R.id.passwordText);
        passwordAgainEditText = (EditText) findViewById(R.id.confirmText);

        // Set up the submit button click handler
        Button signUpButton = (Button) findViewById(R.id.createButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                signup();
            }
        });

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void signup() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String passwordAgain = passwordAgainEditText.getText().toString().trim();

        // Validate the sign up data
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder();
        if (username.length() == 0) {
            validationError = true;
            validationErrorMessage.append("Blank Username");
        }
        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append("\n");
            }
            validationError = true;
            validationErrorMessage.append("Blank Password");
        }
        if (!password.equals(passwordAgain)) {
            if (validationError) {
                validationErrorMessage.append("\n");
            }
            validationError = true;
            validationErrorMessage.append("Mismatched Passwords");
        }

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(SignUpActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(SignUpActivity.this);
        dialog.setMessage("Signing up...");
        dialog.show();

        // Set up a new Parse user
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.put("score", 0);
        user.put("availability", false);

        // Call the Parse signup method
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                else {
                    // ParseUser
                    startActivity(new Intent("android.intent.action.LoginPage"));
                }
            }
        });

    }
}
