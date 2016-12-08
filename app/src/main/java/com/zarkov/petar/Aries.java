package com.zarkov.petar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zarkov.petar.R;

/**
 * Created by Petar Zarkov on 30/09/2016.
 */
//**zodiac class for aries**//
public class Aries extends Activity {
    //** declare variables**//
    TextView tv,tv1,tv2;
    ImageView iv;
    Boolean connected;
    private String finalUrl="http://feeds.feedburner.com/AstroSageAries?format=xml";
    private HandleXML obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view to common
        setContentView(R.layout.common);
        //initiate method for views
        init();
        //set text of tv param to zodiacs' name
        tv.setText(R.string.Aries);
        //set image of zodiac
        iv.setBackgroundResource(R.drawable.aries);
        //exec fetch
        fetch();
    }
    //assign variables to views
    private void init() {
        tv=(TextView)findViewById(R.id.textView);
        tv1=(TextView)findViewById(R.id.textView1);
        tv2=(TextView)findViewById(R.id.textView2);
        iv=(ImageView)findViewById(R.id.imageView);
    }
    //populate feed
    public void fetch(){
        obj = new HandleXML(finalUrl);
        obj.fetchXML();
        while(obj.parsingComplete);
        tv1.setText(obj.getTitle());
        tv2.setText(obj.getDescription());
    }
}
