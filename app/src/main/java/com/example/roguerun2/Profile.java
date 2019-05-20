package com.example.roguerun2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {


    private EditText displayEmail, bmi, bfi, weight, youare;
    private User UserStats;
    private DatabaseReference databaseReference, db2;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_profile);
        displayEmail = findViewById(R.id.EmailDisplay);
        weight = findViewById(R.id.DisplayWeight);
        bmi = findViewById(R.id.DisplayBMI);
        bfi = findViewById(R.id.DisplayBFP);
        youare = findViewById(R.id.Youare2);
        if (user != null) {
            String Email = user.getEmail();
            displayEmail.setText(Email);
        }
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        db2 = FirebaseDatabase.getInstance().getReference().child(id);
        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserStats = dataSnapshot.getValue(User.class);
                String BMI2 = String.valueOf(UserStats.getBmi()).substring(0, Math.min(String.valueOf(UserStats.getBmi()).length(), 4));
                String BFI2 = String.valueOf(UserStats.getBodyFat()).substring(0, Math.min(String.valueOf(UserStats.getBodyFat()).length(), 4));
                bmi.setText(BMI2);
                bfi.setText(BFI2);
                weight.setText(String.valueOf(UserStats.getWeight()));
                youare.setText(WeightBound(UserStats.getBmi()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final Button back = findViewById(R.id.BackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        final Button verify = findViewById(R.id.verify);
        if(user.isEmailVerified()){
            verify.setVisibility(View.INVISIBLE);
        }
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.sendEmailVerification();
                Context context = getApplicationContext();
                CharSequence text = "Sending verification email";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                verify.setEnabled(false);
            }
        });

        Button resetPas = findViewById(R.id.rstPassBtn);
        resetPas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goReset();
            }
        });

        Button updateStats = findViewById(R.id.updateStats);
        updateStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goStats();
            }
        });

        Button myMissions = findViewById(R.id.myMission);
        myMissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMission();
            }
        });


    }




    public void getUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and Profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }

    public void goBack(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void goStats(){
        Intent intent = new Intent(this, myStats.class);
        startActivity(intent);
    }

    public void goReset(){
        Intent intent = new Intent(this, resetPass.class);
        startActivity(intent);
    }

    public void goMission(){
        Intent intent = new Intent(this, missionSummary.class);
        startActivity(intent);
    }

    public String WeightBound (double bmi){
        String bound= "";
        if(bmi <18.5) bound="Under Weight";
        if(bmi >=18.5 && bmi < 24.9) bound="Healthy Weight";
        if(bmi >=30 && bmi < 34.9) bound="~Over Weight";
        if(bmi >=34.9) bound="Obese";
        return bound;
    }


}
