package com.example.roguerun2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button login;
    private EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createButton();
    }



    public void login(){
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        String EmailIn = email.getText().toString().trim();
        String PassIn = password.getText().toString().trim();
        firebaseAuth = FirebaseAuth.getInstance();

        if (EmailIn.isEmpty()) {
            email.setError("Enter an Email");
            email.requestFocus();
            return;
        }
        if (PassIn.isEmpty()) {
            password.setError("Enter an Password");
            password.requestFocus();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(EmailIn, PassIn).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                SendToHome();

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                {
                    Context context = getApplicationContext();
                    CharSequence text = "No user with this email or password";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });





    }

    public void SendToHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);

    }

    public void sendToRegister(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void sendToStats(){
        Intent intent = new Intent(this, myStats.class);
        startActivity(intent);
    }

    private void updateUI(Object o) {
    }

    public void createButton(){
        login = findViewById(R.id.LoginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
        TextView register = findViewById(R.id.Registertext);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToRegister();

            }
        });

    }


}
