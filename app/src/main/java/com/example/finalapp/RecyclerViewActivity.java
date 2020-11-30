package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class RecyclerViewActivity extends AppCompatActivity {
    FloatingActionButton buttonAddNote;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference complaintref = db.collection("Complaints");
    private ComplaintAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        buttonAddNote = (FloatingActionButton) findViewById(R.id.action_button);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecyclerViewActivity.this,AddComplaint.class));
            }
        });


        setupRecyclerview();
    }

    private void setupRecyclerview() {
        Query query = complaintref.orderBy("priority",Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Complaint> options = new FirestoreRecyclerOptions.Builder<Complaint>().setQuery(query,Complaint.class).build();

        adapter = new ComplaintAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}