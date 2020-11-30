package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class homeActivity extends AppCompatActivity {

    TextView log1;
    private Button profile,map,complaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profile = (Button) findViewById(R.id.button8);
        log1 = (TextView) findViewById(R.id.logout);
        map = (Button) findViewById(R.id.button10);
        complaint = (Button) findViewById(R.id.button11);

        log1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signout();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editprofile();
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this,ShowMap.class));
            }
        });

        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this,RecyclerViewActivity.class));
            }
        });

    }


    private void editprofile() {
        Intent p = new Intent(this,profileInfoShow.class);
        startActivity(p);
    }

    private void signout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,MainActivity.class));
    }
}