package com.parse.starter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by kuenj on 1/23/2016.
 */
public class AssassinActivity extends Activity {

    ImageView arrowPointer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assassin_activity);

        arrowPointer = (ImageView) findViewById(R.id.arrow_picture);

        final Animation arrowRotate = AnimationUtils.loadAnimation(this, R.anim.arrow_animation);

        Button btnRotate = (Button) findViewById(R.id.rotate);

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrowPointer.startAnimation(arrowRotate);
            }
        });
    };
}
