package com.github.slyro007.nuhack91;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MLH Admin on 3/25/2018.
 */

public class LocationList extends ArrayAdapter<Location> {

    private Activity context;
    private List<Location> locationList;

    public LocationList(Activity context, List<Location> locationList){
        super(context, R.layout.list_layout, locationList);
        this.context = context;
        this.locationList = locationList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewRoom = (TextView) listViewItem.findViewById(R.id.textViewRoom);
        TextView textViewBuilding = (TextView) listViewItem.findViewById(R.id.textViewBuilding);

        Location location = locationList.get(position);

        textViewRoom.setText(location.getLocationRoom());
        textViewBuilding.setText(location.getLocationBuilding());

        return listViewItem;

    }
}
