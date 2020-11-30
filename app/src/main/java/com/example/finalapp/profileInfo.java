package com.example.finalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class profileInfo extends AppCompatActivity {

    private EditText name, number, college;
    FirebaseFirestore db;
    private String userId;
    FirebaseAuth kAuth;
    String names, colleges, numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);
        db = FirebaseFirestore.getInstance();
        kAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.editname);
        number = findViewById(R.id.editphone);
        college = findViewById(R.id.editcollege);
        Button save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                names = name.getText().toString().trim();
                numbers = number.getText().toString().trim();
                colleges = college.getText().toString().trim();
                userId = Objects.requireNonNull(kAuth.getCurrentUser()).
                        getUid();
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("name", names);
                user.put("number", numbers);
                user.put("college", colleges);

                // Add a new document with a generated ID
                db.collection("users").document(kAuth.getUid()).set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(profileInfo.this, "Profile updated", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });



    }
}

