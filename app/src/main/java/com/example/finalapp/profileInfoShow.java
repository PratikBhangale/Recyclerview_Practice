package com.example.finalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class profileInfoShow extends AppCompatActivity {

    FirebaseAuth mAuth;

    private static final String KEY_NAME = "name";
    private static final String KEY_COLLEGE = "college";
    private static final String KEY_NUMBER = "number";
    TextView name1,college1,number1,editprofile;
    String userid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //DocumentReference noteRef = db.document("users/userid");
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info_show);
        mAuth = FirebaseAuth.getInstance();

        name1 = (TextView) findViewById(R.id.text1);
        number1 = (TextView) findViewById(R.id.texts2);
        college1 = (TextView) findViewById(R.id.text3);
        userid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        editprofile = (TextView) findViewById(R.id.textView11);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });

    }

    private void editProfile() {
        Intent y = new Intent(this,profileInfo.class);
        startActivity(y);
    }

    public void loadNote() {
        Task<DocumentSnapshot> noteRef = db.collection("users").document(userid).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                        String name2 = documentSnapshot.getString(KEY_NAME);
                        String number2 = documentSnapshot.getString(KEY_NUMBER);
                        String college2 = documentSnapshot.getString(KEY_COLLEGE);

                        name1.setText(name2);
                        number1.setText(number2);
                        college1.setText(college2);
                        Toast.makeText(profileInfoShow.this, "Refresh successful.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(profileInfoShow.this, "This document does not exist.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(profileInfoShow.this, "Refresh unsuccessful.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNote();
    }
}