package com.parse.starter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Austin on 2016-01-23.
 */
public class TargetedActivity extends Activity {
    private BackgroundChange changeBackGroundColour;
    private ParseUser user;
    private TimerTask game;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        user = ParseUser.getCurrentUser();
        changeBackGroundColour = new BackgroundChange();
        changeBackGroundColour.root = (LinearLayout) findViewById(R.id.assassin_background);
        changeBackGroundColour.changeBackground(100);
    }

    public void updateUI(double distance) {
        changeBackGroundColour.changeBackground(distance);
    }

    public void updateServer() {

    }
}

