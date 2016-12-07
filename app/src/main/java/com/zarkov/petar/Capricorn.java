package com.zarkov.petar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zarkov.petar.R;

/**
 * Created by Petar Zarkov on 30/09/2016.
 */
public class Capricorn extends Activity {
    TextView tv,tv1,tv2,tv3;
    ImageView iv;
    Boolean connected;
    private String finalUrl="http://feeds.feedburner.com/AstroSageCapricorn?format=xml";
    private HandleXML obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common);
        init();
        tv.setText(R.string.Capricorn);
        iv.setBackgroundResource(R.drawable.capricorn);
        fetch();
    }

    private void init() {
        tv=(TextView)findViewById(R.id.textView);
        tv1=(TextView)findViewById(R.id.textView1);
        tv2=(TextView)findViewById(R.id.textView2);
        iv=(ImageView)findViewById(R.id.imageView);
    }

    public void fetch(){
        obj = new HandleXML(finalUrl);
        obj.fetchXML();
        while(obj.parsingComplete);
        tv1.setText(obj.getTitle());
        tv2.setText(obj.getDescription());
    }
}
