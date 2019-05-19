package com.example.roguerun2;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MissionThree extends AppCompatActivity {
    TextView timerTextView;
    long startTime = 0;
    private User UserStats, newUser;
    private DatabaseReference databaseReference, db2;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_three);
        button();
        timerTextView = findViewById(R.id.timer4);
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

    }


    public void button(){
        final Button button = findViewById(R.id.StartWorkout3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference();
                newUser = new User(UserStats.getID(),UserStats.getAge(),UserStats.getHeight(),UserStats.getBmi(),UserStats.getWeight(),UserStats.getBodyFat(), UserStats.getWorkOut1(),UserStats.getWorkOut2(),true,UserStats.getWorkOut4());
                databaseReference.child(UserStats.getID()).setValue(newUser);
                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                new CountDownTimer(1200000, 1000) {

                    public void onTick(long millisUntilFinished) {

                        timerTextView.setText("Seconds remaining: " + millisUntilFinished / 1000);
                        if(millisUntilFinished == 500000){
                            Context context = getApplicationContext();
                            CharSequence text = "One min down";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                        }
                    }

                    public void onFinish() {
                        timerTextView.setText("Done");
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
