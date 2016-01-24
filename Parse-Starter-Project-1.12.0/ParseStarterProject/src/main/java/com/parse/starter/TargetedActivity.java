package com.parse.starter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.DateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Austin on 2016-01-23.
 */
public class TargetedActivity extends Activity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private BackgroundChange changeBackGroundColour;
    private TimerTask game;private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    TextView mLatitudeText;
    TextView mLongitudeText;
    TextView mLastUpdateText;
    String mLastUpdateTime;
    LocationRequest mLocationRequest;
    ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        user = ParseUser.getCurrentUser();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        createLocationRequest();
        changeBackGroundColour = new BackgroundChange();
        changeBackGroundColour.root = (LinearLayout) findViewById(R.id.assassin_background);
        changeBackGroundColour.changeBackground(100);
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void updateUI(double distance) {
        changeBackGroundColour.changeBackground(distance);
    }

    public void updateServer() {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
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
}

