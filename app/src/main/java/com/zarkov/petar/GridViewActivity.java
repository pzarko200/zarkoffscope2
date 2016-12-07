package com.zarkov.petar;

/**
 * Created by ZARKOVPETARBOZHIDARO on 11/7/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class GridViewActivity extends Activity implements AdapterView.OnItemClickListener {

    private GridView mGridView;
    private CityAdapter mAdapter;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();

        // Clear the realm from last time
        Realm.deleteRealm(realmConfiguration);

        // Create a new empty instance of Realm
        realm = Realm.getInstance(realmConfiguration);


    }

    @Override
    public void onResume() {
        super.onResume();

        // Load from file "cities.json" first time
        if(mAdapter == null) {
            List<City> cities = loadCities();

            //This is the GridView adapter
            mAdapter = new CityAdapter(this);
            mAdapter.setData(cities);

            //This is the GridView which will display the list of cities
            mGridView = (GridView) findViewById(R.id.cities_list);
            mGridView.setAdapter(mAdapter);
            mGridView.setOnItemClickListener(GridViewActivity.this);
            mAdapter.notifyDataSetChanged();
            mGridView.invalidate();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }

    private List<City> loadCities() {
        // In this case we're loading from local assets.
        // NOTE: could alternatively easily load from network
        InputStream stream;
        try {
            stream = getAssets().open("cities.json");
        } catch (IOException e) {
            return null;
        }

        Gson gson = new GsonBuilder().create();

        JsonElement json = new JsonParser().parse(new InputStreamReader(stream));
        List<City> cities = gson.fromJson(json, new TypeToken<List<City>>() {}.getType());

        // Open a transaction to store items into the realm
        // Use copyToRealm() to convert the objects into proper RealmObjects managed by Realm.
        realm.beginTransaction();
        Collection<City> realmCities = realm.copyToRealm(cities);
        realm.commitTransaction();

        return new ArrayList<City>(realmCities);
    }

    public void updateCities() {
        // Pull all the cities from the realm
        RealmResults<City> cities = realm.where(City.class).findAll();

        // Put these items in the Adapter
        mAdapter.setData(cities);
        mAdapter.notifyDataSetChanged();
        mGridView.invalidate();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        City modifiedCity = (City)mAdapter.getItem(position);

        // Acquire the RealmObject matching the name of the clicked City.
        final City city = realm.where(City.class).equalTo("name", modifiedCity.getName()).findFirst();

        // Create a transaction to increment the vote count for the selected City in the realm
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                city.setVotes(city.getVotes() + 1);
            }
        });

        updateCities();
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
