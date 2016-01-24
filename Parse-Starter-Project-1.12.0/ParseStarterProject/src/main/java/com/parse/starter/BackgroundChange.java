package com.parse.starter;

import android.graphics.Color;
import android.widget.LinearLayout;

/**
 * Created by kuenj on 1/23/2016.
 */
public class BackgroundChange {
    LinearLayout root = null;

    public void changeBackground(double distance) {
        if (root != null) {
            //furthest green in meters
            if (distance >= 100) {
                root.setBackgroundColor(Color.parseColor("#64DD17"));
            }
            //medium green-yellow
            else if (75 <= distance && distance < 100) {
                root.setBackgroundColor(Color.parseColor("#AEEA00"));
            }
            //yellow
            else if (50 <= distance && distance < 75) {
                root.setBackgroundColor(Color.parseColor("#FFEB3B"));
            }
            //close orange
            else if (25 <= distance && distance < 50) {
                root.setBackgroundColor(Color.parseColor("#FF9800"));
            }
            //dead red
            else if (distance < 25) {
                root.setBackgroundColor(Color.parseColor("#FF3D00"));
            }
        }
    }
}
