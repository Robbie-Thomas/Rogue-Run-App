package com.example.roguerun2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button register;
    private EditText email, password;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.registerBtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        final Button back = findViewById(R.id.BackBtn2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

    }

   /* @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }*/



    public void signIn(){

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        String EmailIn = email.getText().toString().trim();
        String PassIn = password.getText().toString().trim();


        if (EmailIn.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(EmailIn).matches()) {
            email.setError("Please enter a valid Email Address");
        }

        if (PassIn.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if (password.length() < 6) {
            password.setError("Password must be longer than 6 characters.");
            password.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(EmailIn, PassIn).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();

                    Log.d(TAG, "createUserWithEmail:success");
                    //setContentView(R.layout.activity_main);
                    user.sendEmailVerification();
                    sendToStats();


                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    }

                }
            }
        });
      }

    public void SendToMain(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);

    }

    public void sendToStats(){
        Intent intent = new Intent(this, myStats.class);
        startActivity(intent);
    }

    public void goBack(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
