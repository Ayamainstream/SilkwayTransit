package com.example.silkwaytransit.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.silkwaytransit.R;
import com.example.silkwaytransit.network.stations.StationTime;

import java.util.ArrayList;

public class DriverLVAdapter extends ArrayAdapter<StationTime> {

    public DriverLVAdapter(@NonNull Context context, ArrayList<StationTime> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.route_listview_layout, parent, false);
        }

        StationTime dataModal = getItem(position);

        TextView stationName = listitemView.findViewById(R.id.station_name);
        TextView stationDepartureTime = listitemView.findViewById(R.id.depatrureTime);
        TextView stationArrivalTime = listitemView.findViewById(R.id.arrivalTime);

        stationName.setText(dataModal.getStationNames());
        stationDepartureTime.setText(dataModal.getDepartureTime());
        stationArrivalTime.setText(dataModal.getArrivalTime());
        return listitemView;
    }
}
