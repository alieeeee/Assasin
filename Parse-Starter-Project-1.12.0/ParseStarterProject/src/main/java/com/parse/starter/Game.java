package com.parse.starter;

import com.parse.FindCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by Austin on 2016-01-24.
 */

public class Game extends TimerTask {
    double distance;
    double angle;
    AssassinActivity assassin;
    TargetedActivity target;
    String assassinId;
    String targetId;


    public Game(AssassinActivity assassin) {
        this.assassin = assassin;
    }

    public Game(String assassinId, String targetId) {
        this.assassinId = assassinId;
        this.targetId = targetId;
    }

    public void run() {
        calculate();
        updateUI();
        updateServer();
        if (distance < 10) {
            cancel();
        }
    }

    public void setAssassin(AssassinActivity assassin) {
        this.assassin = assassin;
    }

    public void setTarget(TargetedActivity target) {
        this.target = target;

    }

    public void updateUI(){
        assassin.updateUI(angle);
        target.updateUI(distance);
    }

    public void updateServer(){
        assassin.updateServer();
        target.updateServer();
    }

    public void calculate(){

        String idToUse = targetId;
        if(idToUse == null){
            idToUse = assassinId;
        }
        ParseQuery<ParseUser> users = ParseUser.getQuery();
        ParseQuery<ParseUser> realDistance = users.whereEqualTo("objectId", idToUse);
        realDistance.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> d, ParseException e) {
                if (e == null) {
                    if (d.size() == 1) {
                        ParseUser rival = d.get(0);
                        ParseGeoPoint g = rival.getParseGeoPoint("location");
                        ParseUser user = ParseUser.getCurrentUser();
                        ParseGeoPoint currentLoation = user.getParseGeoPoint("location");
                        distance = g.distanceInKilometersTo(currentLoation);
                        angle = g.distanceInRadiansTo(currentLoation);
                    }
                    else{
                        // log fail to load
                    }
                }
            }
        });
    }

}
