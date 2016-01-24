package com.parse.starter;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by kuenj on 1/23/2016.
 */
public class TurnPointer {
    private static final String TAG = "Compass";
    public ImageView arrowPointer = null;

    public void adjustArrow(){
        if(arrowPointer == null){
            Log.i(TAG, "arrow view is not set");
            return;
        }
        float angle1 = 22;
        float angle2 = 64;
        float position1 = 0;
        float position2 = 0;

        Animation an = new RotateAnimation(angle1, angle2,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        an.setDuration(100);
        an.setRepeatCount(0);
        an.setFillAfter(true);

        arrowPointer.startAnimation(an);
    }
}
