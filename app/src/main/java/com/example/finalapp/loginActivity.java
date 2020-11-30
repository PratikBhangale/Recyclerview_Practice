package com.example.finalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    Button login;
    TextView signup;
    EditText email,password;
    FirebaseAuth mAuth;
    String email5,password5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login= findViewById(R.id.login);
        signup= findViewById(R.id.movetosignup);
        email= findViewById(R.id.loginemail);
        password= findViewById(R.id.loginpassword);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensignup();
            }
        });

    }
    private void opensignup() {
        Intent i= new Intent(this,signupActivity.class);
        startActivity(i);
    }


    @Override
    protected void onStart() {
        super.onStart();

        mAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlogin();
            }
        });


    }

    private void openlogin() {
        email5=email.getText().toString();
        password5=password.getText().toString();

        if(email5.isEmpty()){
            email.setError("Please enter an Email.");
            email.requestFocus();
            return;
        }

        if(password5.isEmpty()){
            password.setError("Please enter a Password");
            password.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email5).matches()){
            email.setError("Please enter a valid Email address.");
            email.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(email5,password5).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    gohome();
                }
                else {
                    Toast.makeText(loginActivity.this, "Login Failed.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void gohome() {
        startActivity(new Intent(this,homeActivity.class));
    }

}