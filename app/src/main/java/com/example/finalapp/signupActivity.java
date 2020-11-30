package com.example.finalapp;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.PatternMatcher;
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

public class signupActivity extends AppCompatActivity {

    private Button signup;
    private EditText email2,password2;
    private TextView backttologin;
    private FirebaseAuth aAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        aAuth = FirebaseAuth.getInstance();

        backttologin= findViewById(R.id.textView);
        email2= findViewById(R.id.email1);
        password2= findViewById(R.id.password1);
        signup= findViewById(R.id.button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();

            }
        });

    }

    private void register() {
        String username,password;

        username=email2.getText().toString().trim();
        password=password2.getText().toString().trim();

        if(username.isEmpty()){
            email2.setError("Please enter an Email.");
            email2.requestFocus();
            return;
        }

        if(password.isEmpty()){
            password2.setError("Please enter a Password");
            password2.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
            email2.setError("Please enter a valid Email address.");
            email2.requestFocus();
            return;
        }

        aAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signupActivity.this, "The user has successfully been registered.", Toast.LENGTH_SHORT).show();
                    tohome();
                }
                else {
                    Toast.makeText(signupActivity.this, "The user is already registered, or please enter valid email/password,", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void tohome() {
        Intent h = new Intent(this,homeActivity.class);
        startActivity(h);
    }


}