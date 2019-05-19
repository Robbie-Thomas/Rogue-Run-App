package com.example.roguerun2;

import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MissionOne extends AppCompatActivity {
    private TextView timer;
    private User UserStats, newUser;
    private DatabaseReference databaseReference, db2;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_one);
        timer = findViewById(R.id.timer2);


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        db2 = FirebaseDatabase.getInstance().getReference().child(id);

        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserStats = dataSnapshot.getValue(User.class);
               }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        buttons();


    }

    public void swapToMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void buttons(){

        Button test = findViewById(R.id.startworkout4);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference();
                newUser = new User(UserStats.getID(),UserStats.getAge(),UserStats.getHeight(),UserStats.getBmi(),UserStats.getWeight(),UserStats.getBodyFat(), true,UserStats.getWorkOut2(),UserStats.getWorkOut3(),UserStats.getWorkOut4());
                databaseReference.child(UserStats.getID()).setValue(newUser);
                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                new CountDownTimer(600000, 1000) {
                    public void onTick(long millisUntilFinished) {


                        timer.setText("Seconds remaining: " + millisUntilFinished / 1000);

                    }

                    public void onFinish() {
                        timer.setText("Done");

                    }

                }.start();
                new CountDownTimer(100000, 1000) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                    }
                }.start();
                new CountDownTimer(200000, 1000) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                    }
                }.start();
                new CountDownTimer(300000, 1000) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                    }
                }.start();
                new CountDownTimer(400000, 1000) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                    }
                }.start();
                new CountDownTimer(500000, 1000) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                    }
                }.start();
            }
        });

    }
}
