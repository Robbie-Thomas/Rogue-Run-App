package com.example.roguerun2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.stream.Stream;

public class missionSummary extends AppCompatActivity {
    private User UserStats;
    private EditText missionsSum;
    private DatabaseReference databaseReference, db2;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    long numberOfTrues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //createShareButton();
        setContentView(R.layout.activity_mission_summary);
        missionsSum = findViewById(R.id.missionText);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        db2 = FirebaseDatabase.getInstance().getReference().child(id);

        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserStats = dataSnapshot.getValue(User.class);

                numberOfTrues = Stream.of(UserStats.getWorkOut1(), UserStats.getWorkOut2(), UserStats.getWorkOut3(), UserStats.getWorkOut4())
                        .filter(w->w)
                        .count();
                missionsSum.setText(String.valueOf(numberOfTrues) + " Missions");

                //int counter = (UserStats.getWorkOut1() ? 1 : 0) + (UserStats.getWorkOut2() ? 1 : 0) + (UserStats.getWorkOut3() ? 1 : 0) + (UserStats.getWorkOut4() ? 1 : 0);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final Button back = findViewById(R.id.BackBtn4);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        createShareButton();
    }

    public void goBack(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }


    public void createShareButton() {
        ImageButton share = findViewById(R.id.shareButton9);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "I have just completed " + numberOfTrues + " Missions";
                String shareSub = "Rouge Run Missions Complete";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share using"));
            }
        });
    }
}


