package com.parse.starter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseQuery;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Austin on 2016-01-23.
 */
public class MatchActivity extends Activity{
    ParseUser assassin, target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.making_match);

        assassin = ParseUser.getCurrentUser();
        assassin.put("availability", false);

        Bundle b = getIntent().getExtras();
        String targetID;
        //Target must be added during matchmaking
        targetID = b.getString("target");
        Log.d("", targetID);

        ParseQuery<ParseUser> query = ParseQuery.getQuery("ParseUser");
        target = query.getInBackground(targetID).getResult();
        if (target.equals(null)) finish();
        if (target.getBoolean("availability")) {
            makeMatch();
        }
        else {
            Toast.makeText(MatchActivity.this, "Target Unavailable", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        startActivity(new Intent("android.content.WaitingRoom"));
    }

    private void makeMatch() {
        target.put("availability", false);
        try{
            target.fetch();
            Toast.makeText(MatchActivity.this, "Match Made!", Toast.LENGTH_LONG).show();
        }
        catch(ParseException e) {
            Toast.makeText(MatchActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
