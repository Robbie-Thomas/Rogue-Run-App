package com.example.roguerun2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {


    private TextView user;
    private Button signout, missions, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        signout = findViewById(R.id.SignOut);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendHome();

            }
        });
        missions = findViewById(R.id.Missions);
        missions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMissions();
            }
        });
        profile = findViewById(R.id.profileBtn);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendProfile();
            }
        });
    }

    public void sendHome(){

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void sendProfile(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);

    }
    public void sendMissions(){
        Intent intent = new Intent(this, Mission.class);
        startActivity(intent);

    }
}
