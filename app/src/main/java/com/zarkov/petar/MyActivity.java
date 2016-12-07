package com.zarkov.petar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;


public class MyActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton a,b,c,d,e,f,g,h,i,j,k,l;
    boolean connected,online;
    ProgressDialog pro;

    mcSaveData mcSDPrefs; // Lab 3 Shared Preferences
    SharedPreferences mySharedPrefs; // Lab 3 Shared Preferences
    FragmentManager fmAboutDialogue; // Lab 3 Dialogue fragment
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        check chk=new check();
        chk.execute();
        /*try {
			c.execute().get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

        // Lab 7 code to set orientation goes here
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //Lab 7
        // Action Bar
        android.support.v7.app.ActionBar ccActionBar = getSupportActionBar();
        if (ccActionBar != null)
        {
            ccActionBar.setDisplayShowHomeEnabled(true);
            ccActionBar.setLogo(R.drawable.ic_launcher);
            ccActionBar.setDisplayUseLogoEnabled(true);
        }
        
        init();

        // Lab 3 Dialogue fragment
        fmAboutDialogue = this.getFragmentManager();
        // Lab 3 Shared Preferences
        mySharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mcSDPrefs = new mcSaveData(mySharedPrefs);
        mcSDPrefs.setDefaultPrefs();
        
    }
    
    public class check extends AsyncTask<Void, Void, Void>{
    	
    	ProgressDialog dialog = new ProgressDialog(MyActivity.this);
    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		dialog.setMessage("Please Wait");
    		dialog.setIndeterminate(false);
    		dialog.setCancelable(false);
    		dialog.setCanceledOnTouchOutside(false);
    		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.show();
    		super.onPreExecute();
    		
    	}

		@Override
		protected Void doInBackground(Void... params) {

			if(isOnline())
	        	connected=true;
			else
				connected=false;
			return null;	
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			if(dialog != null && dialog.isShowing()){
			       dialog.dismiss();
			}
			if(!connected){
	        	new AlertDialog.Builder(MyActivity.this)
	        	.setTitle("Error")
	        	.setMessage("Check Connection")
	        	.setCancelable(false)
				.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						finish();
					}
				}).show();
	        }
			super.onPostExecute(result);
			
		}
    	
    }

    private boolean isOnline() {
    	if (isConnected()) {
    		 try {
    	            URL url = new URL("http://www.google.com");  // or any valid link.
    	            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
    	            urlc.setConnectTimeout(3000);
    	            urlc.connect();
    	            if (urlc.getResponseCode() == 200) {
    	                return true;
    	            }
    	            else
    	            	return false;
    	        } catch (MalformedURLException e1) {
    	            // TODO Auto-generated catch block
    	            e1.printStackTrace();
    	        } catch (IOException e) {
    	            // TODO Auto-generated catch block
    	            e.printStackTrace();
    	        }
    	}
        return false;
	}

	public boolean isConnected() {
		// TODO Auto-generated method stub
    	ConnectivityManager cm =
    	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
    	        return true;
    	    }
    	    return false;
		
	}
	private void init() {
        a=(ImageButton)findViewById(R.id.aries);
        b=(ImageButton)findViewById(R.id.taurus);
        c=(ImageButton)findViewById(R.id.gemini);
        d=(ImageButton)findViewById(R.id.cancer);
        e=(ImageButton)findViewById(R.id.leo);
        f=(ImageButton)findViewById(R.id.virgo);
        g=(ImageButton)findViewById(R.id.libra);
        h=(ImageButton)findViewById(R.id.scorpio);
        i=(ImageButton)findViewById(R.id.sagittarius);
        j=(ImageButton)findViewById(R.id.capricorn);
        k=(ImageButton)findViewById(R.id.aquarius);
        l=(ImageButton)findViewById(R.id.pisces);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);
        e.setOnClickListener(this);
        f.setOnClickListener(this);
        g.setOnClickListener(this);
        h.setOnClickListener(this);
        i.setOnClickListener(this);
        j.setOnClickListener(this);
        k.setOnClickListener(this);
        l.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.help:
                finish();
                return true;
            // Lab 7 code goes here
            case R.id.mBio: // Lab 7
                Intent mcBioDraw = new Intent(this, mcBioActivity.class);
                this.startActivity(mcBioDraw);
                return true;
            // Lab 8 code goes here
            case R.id.mMap: // Lab 8
                Intent Map = new Intent(this, MapActivity.class);
                this.startActivity(Map);
                return true;
            // Lab 9 code goes here
            case R.id.mAccel:
                Intent mcCB = new Intent(this, GridViewActivity.class);
                this.startActivity(mcCB);
                return true;
            case R.id.about:
                // About Dialogue;
                DialogFragment mcAboutDlg = new AboutDialogue();
                mcAboutDlg.show(fmAboutDialogue, "menu");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId()){
            case R.id.aries:
                i=new Intent(MyActivity.this,Aries.class);
                startActivity(i);
                break;
            case R.id.taurus:
                i=new Intent(MyActivity.this,Taurus.class);
                startActivity(i);
                break;
            case R.id.gemini:
                i=new Intent(MyActivity.this,Gemini.class);
                startActivity(i);
                break;
            case R.id.cancer:
                i=new Intent(MyActivity.this,Cancer.class);
                startActivity(i);
                break;
            case R.id.leo:
                i=new Intent(MyActivity.this,Leo.class);
                startActivity(i);
                break;
            case R.id.virgo:
                i=new Intent(MyActivity.this,Virgo.class);
                startActivity(i);
                break;
            case R.id.libra:
                i=new Intent(MyActivity.this,Libra.class);
                startActivity(i);
                break;
            case R.id.scorpio:
                i=new Intent(MyActivity.this,Scorpio.class);
                startActivity(i);
                break;
            case R.id.sagittarius:
                i=new Intent(MyActivity.this,Sagittarius.class);
                startActivity(i);
                break;
            case R.id.capricorn:
                i=new Intent(MyActivity.this,Capricorn.class);
                startActivity(i);
                break;
            case R.id.aquarius:
                i=new Intent(MyActivity.this,Aquarius.class);
                startActivity(i);
                break;
            case R.id.pisces:
                i=new Intent(MyActivity.this,Pisces.class);
                startActivity(i);
                break;
        }
    }





}
