/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginMenu extends ActionBarActivity {

  Button loginButton, cancelButton;
  EditText userNameText, passwordText;

  TextView tx1;
  int counter = 3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //bottom line of code links this .java file to the activity_mail.xml file
    setContentView(R.layout.activity_main);

    loginButton = (Button)findViewById(R.id.button);
    userNameText = (EditText)findViewById(R.id.editText);
    passwordText = (EditText)findViewById(R.id.editText2);

    cancelButton = (Button)findViewById(R.id.button2);

    //username is admin, password is admin
    loginButton.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {

        ParseUser.logInInBackground(userNameText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {
          @Override
          public void done(ParseUser user, ParseException e) {
            Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();

            if (e != null) {
              Toast.makeText(LoginMenu.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            else{
              startActivity(new Intent("android.intent.action.WaitingRoom"));
            }
          }

        });
      }
    });

    cancelButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v){
        finish();
      }
    });

    //I don't know what this does just don't mess with it I guess
    ParseObject testObject = new ParseObject("TestObject");
    testObject.put("foo", "bar");
    testObject.saveInBackground();
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
