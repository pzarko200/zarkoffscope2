package com.zarkov.petar;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Petar Zarkov on 30/09/2016.
 */

public class mcSaveDataOutput extends AppCompatActivity implements View.OnClickListener{
    //declare variables
    SharedPreferences mcSharedPrefs;
    Button btnBack;
    TextView mcSDODOW;
    TextView mcSDOMonth;
    TextView mcSDODayBorn;
    FragmentManager fmAboutDialogue; // Lab 3
    // Lab 4 variable declared here
    TextView mcSDStarSign;  // Lab 4


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_display_shared_prefs); // app main UI screen

        // Action Bar
        android.support.v7.app.ActionBar ccActionBar = getSupportActionBar();
        if (ccActionBar != null)
        {
            ccActionBar.setDisplayShowHomeEnabled(true);
            ccActionBar.setLogo(R.drawable.ic_bl_mc);
            ccActionBar.setDisplayUseLogoEnabled(true);
        }

        // Lab 4 TextView association
        mcSDStarSign = (TextView) findViewById(R.id.tvStarSign);

        // Setup, access and listen for the pick date button
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        // create text view for output
        mcSDODOW = (TextView) findViewById(R.id.tvDOW);
        mcSDOMonth = (TextView) findViewById(R.id.tvMonth);
        mcSDODayBorn = (TextView) findViewById(R.id.tvDayBorn);
        // Load any saved preferences
        mcSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        loadSavedPreferences();

        // Lab 3 Dialogue fragment
        fmAboutDialogue = this.getFragmentManager();

        Log.e("n","SDOutput msg");

    }

    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Lab 3
    private void loadSavedPreferences() {
        mcSDODOW.setText(mcSDODOW.getText() + String.valueOf(mcSharedPrefs.getInt("mc_DOW", 1)));
        mcSDOMonth.setText(mcSDOMonth.getText() + String.valueOf(mcSharedPrefs.getInt("mc_Month", 1)));
        mcSDODayBorn.setText(mcSDODayBorn.getText() + mcSharedPrefs.getString("mc_DayBorn", "Sunday"));
        // Lab 4 code goes here
        mcSDStarSign.setText(mcSDStarSign.getText() + mcSharedPrefs.getString("mc_StarSign", "January"));  // Lab 4
    }

    public void onClick(View view) {
        setResult(Activity.RESULT_OK);
        finish();
    }


}
