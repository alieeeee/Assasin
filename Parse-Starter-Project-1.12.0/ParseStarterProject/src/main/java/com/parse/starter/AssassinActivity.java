package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by kuenj on 1/23/2016.
 */
public class AssassinActivity extends Activity {

    private static final String TAG = "CompassActivity";

    private LocationPointer locationPointer;
    private BackgroundChange changeBackGroundColour;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assassin_activity);

        locationPointer = new LocationPointer(this);
        locationPointer.arrowPointer = (ImageView) findViewById(R.id.arrow_picture);

        changeBackGroundColour = new BackgroundChange();
        changeBackGroundColour.root = (LinearLayout) findViewById(R.id.assassin_background);
        changeBackGroundColour.changeBackground(100);
        };

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "start compass");
        locationPointer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationPointer.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationPointer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop compass");
        locationPointer.stop();
    }
    };

