package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * Created by kuenj on 1/24/2016.
 */
public class VictimActivity extends Activity{

    private BackgroundChange changeBackGroundColour;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_room);

        changeBackGroundColour = new BackgroundChange();
        changeBackGroundColour.root = (LinearLayout) findViewById(R.id.vicitim_background);
        changeBackGroundColour.changeBackground(100);
    }
}
