package com.example.silkwaytransit.ui;

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
import androidx.fragment.app.Fragment;

import com.example.silkwaytransit.databinding.FragmentDriverBinding;
import com.example.silkwaytransit.network.stations.StationTime;
import com.example.silkwaytransit.ui.duty.DriversPlanLVAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DriverFragment extends Fragment {
    private FragmentDriverBinding binding;
    ListView listView;
    ArrayList<StationTime> dataModalArrayList;
    FirebaseFirestore db;
    TextView stationName, arrivalTime, depatrureTime;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDriverBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = binding.routeListview;
        dataModalArrayList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        SharedPreferences prefs = getActivity().getSharedPreferences("DeviceToken", MODE_PRIVATE);
        String driverId = prefs.getString("driverId", null);
        listOfDriver(driverId);
//        search(databaseReference, progressBar, editText);


        return root;
    }

    private void listOfDriver(String driverId) {
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

