package com.zarkov.petar;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;


public class mcBioActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set view to app main UI screen
        setContentView(R.layout.mc_bio_draw_screen);
        // set orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // set new view
        setContentView(new mcBiorhythmsSurfaceView(this));
    }

}