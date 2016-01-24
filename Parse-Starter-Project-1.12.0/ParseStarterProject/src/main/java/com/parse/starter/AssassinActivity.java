package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by kuenj on 1/23/2016.
 */
public class AssassinActivity extends Activity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final String TAG = "CompassActivity";

    private LocationPointer locationPointer;
    private BackgroundChange changeBackGroundColour;
    private TimerTask game;
    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    TextView mLatitudeText;
    TextView mLongitudeText;
    TextView mLastUpdateText;
    String mLastUpdateTime;
    LocationRequest mLocationRequest;
    ParseUser user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assassin_activity);
        user = ParseUser.getCurrentUser();
        user.put("availability", true);
        user.saveInBackground();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        createLocationRequest();

        locationPointer = new LocationPointer(this);
        locationPointer.arrowPointer = (ImageView) findViewById(R.id.arrow_picture);

//       changeBackGroundColour = new BackgroundChange();
//       changeBackGroundColour.root = (LinearLayout) findViewById(R.id.assassin_background);
//       changeBackGroundColour.changeBackground(100);
        };

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void Refresh(int numOfUsers) {

    }


    public void updateUI(double direction){

    }

    public void updateServer() {

    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
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
        mGoogleApiClient.disconnect();
        super.onStop();
        Log.d(TAG, "stop compass");
        locationPointer.stop();
    }

    public void onConnected(Bundle bundle) throws SecurityException{
        try  {
            // TODO: https://developer.android.com/training/location/receive-location-updates.html
            // check for requesting location update is on.
            startLocationUpdates();

            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mCurrentLocation != null) {
                mLatitudeText = (TextView)findViewById(R.id.mLatitudeText);
                mLongitudeText = (TextView)findViewById(R.id.mLongitudeText);
                mLastUpdateText = (TextView)findViewById(R.id.mLastUpdateText);
                mLatitudeText.setText(String.valueOf(mCurrentLocation.getLatitude()));
                mLongitudeText.setText(String.valueOf(mCurrentLocation.getLongitude()));
                mLastUpdateText.setText(DateFormat.getTimeInstance().format(new Date()));
            }
        }
        catch (SecurityException e) {

        }
    }


    protected void startLocationUpdates() throws SecurityException{
        try{
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
        }
        catch(SecurityException e){
            //TODO: Jeffery;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
        updateDatabase();
    }

    private void updateDatabase(){
        ParseGeoPoint gp = new ParseGeoPoint(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        user.put("location", gp);
        user.saveInBackground();
    }

    private void updateUI() {
        mLatitudeText.setText(String.valueOf(mCurrentLocation.getLatitude()));
        mLongitudeText.setText(String.valueOf(mCurrentLocation.getLongitude()));
        mLastUpdateText.setText(mLastUpdateTime);
    }
};

