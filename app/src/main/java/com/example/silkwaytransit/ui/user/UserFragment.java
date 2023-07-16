package com.example.silkwaytransit.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.silkwaytransit.LoginActivity;
import com.example.silkwaytransit.databinding.FragmentUserBinding;
import com.example.silkwaytransit.network.user.Users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    ArrayList<Users> dataModalArrayList = new ArrayList<>();
    FirebaseFirestore db;
    TextView FullName, Email, Role;
    CardView btnBack;
    Button logOut;

    public UserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        FullName = binding.FullName;
        Email = binding.email;
        logOut = binding.Logout;
        Role = binding.Role;
        db = FirebaseFirestore.getInstance();
        userProfile();
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    private void userProfile() {
        db.collection("Users").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            Users dataModal = d.toObject(Users.class);
                            if (Objects.equals(dataModal.getId(), FirebaseAuth.getInstance().getCurrentUser().getUid())) {
//                                Toast.makeText(getContext(), dataModal.toString(), Toast.LENGTH_SHORT).show();
                                FullName.setText(dataModal.getFullName());
                                Email.setText(dataModal.getEmail());
                                Role.setText(dataModal.getRole());
                            }
                        }
                    } else {
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