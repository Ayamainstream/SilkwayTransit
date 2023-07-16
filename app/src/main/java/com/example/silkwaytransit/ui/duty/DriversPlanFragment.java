package com.example.silkwaytransit.ui.duty;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.silkwaytransit.databinding.FragmentDriversPlanBinding;
import com.example.silkwaytransit.network.stations.StationTime;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DriversPlanFragment extends Fragment {

    private FragmentDriversPlanBinding binding;
    ArrayList<StationTime> dataModalArrayList = new ArrayList<>();
    FirebaseFirestore db;
    ListView listView;
    TextView driverNameTv, driverIdTv;
    Toolbar btnBack;

    public DriversPlanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDriversPlanBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        driverIdTv = binding.driverId;
        driverNameTv = binding.driverName;
        listView = binding.routeListView;
        btnBack = binding.btnBackToolbar;
        btnBack.setNavigationOnClickListener(view -> getActivity().onBackPressed());
        db = FirebaseFirestore.getInstance();
        SharedPreferences prefs = getActivity().getSharedPreferences("DeviceToken", MODE_PRIVATE);
        String driverName = prefs.getString("driverName", null);
        String driverId = prefs.getString("driverId", null);
        driverNameTv.setText(driverName);
        driverIdTv.setText("Идентификационный номер: "+driverId);
        listOfRoute(driverId);
        return root;
    }

    private void listOfRoute(String driverId) {
        db.collection("StationTime").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            StationTime dataModal = d.toObject(StationTime.class);
                            if (Objects.equals(dataModal.getDriverId(), driverId)) {
//                                Toast.makeText(getContext(), dataModal.toString(), Toast.LENGTH_SHORT).show();
                                dataModalArrayList.add(dataModal);
                            }
                        }
                    DriversPlanLVAdapter adapter = new DriversPlanLVAdapter(getActivity(), dataModalArrayList);
                    listView.setAdapter(adapter);
                }else {
                        Toast.makeText(getActivity(), "No data found in Database", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Fail to load data..", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}