package com.parse.starter;

import com.parse.ParseCloud;

import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.TimerTask;

/**
 * Created by Austin on 2016-01-24.
 */

public class Game extends TimerTask {
    double distance;
    double angle;
    AssassinActivity assassin;
    TargetedActivity target;

    public Game(AssassinActivity assassin) {
        this.assassin = assassin;
    }

    public void run() {
        distance = calculateDistance();
        angle = calculateAngle();
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

    public double calculateDistance(){
        return distance;
    }

    public double calculateAngle() {
        return angle;
    }
}
