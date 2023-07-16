package com.example.silkwaytransit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.silkwaytransit.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends Activity {

    private FirebaseAuth firebaseAuth;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();

//                Toast.makeText(LoginActivity.this, email + password, Toast.LENGTH_SHORT).show();

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser mUSer = firebaseAuth.getCurrentUser();
                            String uid = mUSer.getUid();

                            FirebaseFirestore db = FirebaseFirestore.getInstance();

                            db.collection("Users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        String role = (String) documentSnapshot.get("Role");
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        // now by putExtra method put the value in key, value pair key is
                                        // message_key by this key we will receive the value, and put the string
                                        intent.putExtra("role", role);
                                        // start the Intent
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}