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
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseAnalytics;
import com.parse.ParseObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static android.graphics.Color.RED;

/**
 * Created by Austin on 2016-01-23.
 */
public class WelcomePage extends ActionBarActivity{
    Button login, signup, cancel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome_page);

        login = (Button) findViewById(R.id.loginButton);
        signup = (Button) findViewById(R.id.signUpButton);
        cancel = (Button) findViewById(R.id.cancelButton);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    //opens new Activity
                    startActivity(new Intent("android.intent.action.LoginPage"));
            }
        });

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //opens new Activity
                startActivity(new Intent("android.intent.action.SignUpActivity"));
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
