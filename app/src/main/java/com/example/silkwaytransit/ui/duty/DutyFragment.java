package com.example.silkwaytransit.ui.duty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.silkwaytransit.databinding.FragmentDutyBinding;
import com.example.silkwaytransit.network.duty.Drivers;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DutyFragment extends Fragment {

    private FragmentDutyBinding binding;
    ListView listView;
    ArrayList<Drivers> dataModalArrayList;
    FirebaseFirestore db;
    Toolbar toolbar;
    TextView name;
//    ProgressBar progressBar;
    EditText editText;
    public static List<Drivers> DataCache =new ArrayList<>();
    private DatabaseReference databaseReference;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDutyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = binding.listview;
        name = binding.name;
        dataModalArrayList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        toolbar = binding.toolbar;
        databaseReference = FirebaseDatabase.getInstance().getReference();
//        progressBar = binding.progressBar;
        editText = binding.editText;
        listOfDriver();
//        search(databaseReference, progressBar, editText);
        return root;
    }

    private void listOfDriver() {
        db.collection("Drivers").get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            Drivers dataModal = d.toObject(Drivers.class);

                            dataModalArrayList.add(dataModal);
                        }
                        DutyLVAdapter adapter = new DutyLVAdapter(getActivity(), dataModalArrayList);

                        listView.setAdapter(adapter);
                    } else {
                        Toast.makeText(getActivity(), "No data found in Database", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Fail to load data..", Toast.LENGTH_SHORT).show();
                }
            });
    }

//    private void searchByName(String name){
//        databaseReference = FirebaseDatabase.getInstance().getReference("Drivers");
//        databaseReference.orderByChild("_FullName")
//                .startAt(name)
//                .endAt(name+"\uf8ff");
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}