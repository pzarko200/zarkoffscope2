package com.zarkov.petar;

/**
 * Created by ZARKOVPETARBOZHIDARO on 11/7/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

// This adapter is strictly to interface with the GridView and doesn't
// particular show much interesting Realm functionality.




public class CityAdapter extends BaseAdapter {
    //declare vars and adapter
    private LayoutInflater inflater;

    private List<City> cities = null;
    //inflate it
    public CityAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<City> details) {
        this.cities = details;
    }
    //get count of cities
    @Override
    public int getCount() {
        if (cities == null) {
            return 0;
        }
        return cities.size();
    }
    //get position
    @Override
    public Object getItem(int position) {
        if (cities == null || cities.get(position) == null) {
            return null;
        }
        return cities.get(position);
    }
    //get item id
    @Override
    public long getItemId(int i) {
        return i;
    }
    //get position, view, and parent of city
    //if view null ->inflate
    //if city populated set name and format votes
    @Override
    public View getView(int position, View currentView, ViewGroup parent) {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.city_listitem, parent, false);
        }

        City city = cities.get(position);

        if (city != null) {
            ((TextView) currentView.findViewById(R.id.name)).setText(city.getName());
            ((TextView) currentView.findViewById(R.id.votes)).setText(String.format(Locale.US, "%d",city.getVotes()));
        }

        return currentView;
    }
}