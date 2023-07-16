package com.example.silkwaytransit.ui.duty;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.silkwaytransit.R;
import com.example.silkwaytransit.network.duty.Drivers;

import java.util.ArrayList;

public class DutyLVAdapter extends ArrayAdapter<Drivers> {

    Fragment driversPlanFragment;

    public DutyLVAdapter(@NonNull Context context, ArrayList<Drivers> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.listview_layout, parent, false);
        }

        Drivers dataModal = getItem(position);

        TextView driverName = listitemView.findViewById(R.id.driverName);
        TextView status = listitemView.findViewById(R.id.status);

        driverName.setText(dataModal.getFullName());
        if (dataModal.getStatus() == true) {
            status.setText("Активный");
        }else {
            status.setText("Не активный");
        }

        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driversPlanFragment = new DriversPlanFragment();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                NavController navController = Navigation.findNavController((Activity) getContext(), R.id.nav_host_fragment_activity_main);
//                navController.navigateUp();
//                navController.navigate(R.id.navigation_driver_plan);
//                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                SharedPreferences.Editor editor = activity.getSharedPreferences("DeviceToken", MODE_PRIVATE).edit();
                editor.putString("driverName", dataModal.getFullName()); // or add toString() after if needed
                editor.putString("driverId", dataModal.getId());
                editor.apply();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, driversPlanFragment).addToBackStack(null).commit();
            }
        });
        return listitemView;
    }
}

