package com.zarkov.petar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;



public class mcSaveData extends Activity {

    // *********************************************
    // Declare variables etc.
    // *********************************************

    SharedPreferences mcSharedPrefs;

    private int mcSDDOW;
    private int mcSDMonth;
    private String mcSDDayBorn;
    // Lab 4 Variable goes here
    private String mcSDStarSign; // Lab 4

    // *********************************************
    // Declare getters and setters etc.
    // *********************************************

    private void setmcSDDOW(int isDOW)
    {
        this.mcSDDOW = isDOW;
    }

    public int getmcSDDOW()
    {
        return mcSDDOW;
    }

    private void setmcSDMonth(int isMonth)
    {
        this.mcSDMonth = isMonth;
    }

    public int getmcSDMonth()
    {
        return mcSDMonth;
    }

    private void setmcSDDayBorn(String isDayBorn)
    {
        this.mcSDDayBorn = isDayBorn;
    }

    public String getmcSDDayBorn()
    {
        return mcSDDayBorn;
    }

    // Lab 4 Code goes here
    private void setmcSDStarSign(String smcSDStarSign)
    {
        this.mcSDStarSign = smcSDStarSign;
    }

    public String getmcSDStarSign()
    {
        return mcSDStarSign;
    }

// **************************************************
// Declare constructor and date manipulation methods.
// **************************************************

    public mcSaveData(SharedPreferences mcSDPrefs){
        setmcSDDOW(1);
        setmcSDMonth(1);
        setmcSDDayBorn("Sunday");
        // Lab 4 code goes here
        setmcSDStarSign("January");  // Lab 4
        try {
            this.mcSharedPrefs = mcSDPrefs;
        }
        catch (Exception e)
        {
            Log.e("n","Pref Manager is NULL" );
        }
        setDefaultPrefs();
    }

    public void savePreferences(String key, boolean value) {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void savePreferences(String key, String value) {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void savePreferences(String key, int value) {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void setDefaultPrefs(){
        savePreferences("mc_DOW", 1);
        savePreferences("mc_Month", 1);
        savePreferences("mc_DayBorn", "Empty");
        // Lab 4 Code Goes here
        savePreferences("mc_StarSign", "Empty"); // Lab 4
    }


}
