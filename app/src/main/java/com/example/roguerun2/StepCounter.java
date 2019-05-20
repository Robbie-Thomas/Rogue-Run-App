package com.example.roguerun2;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.orangegangsters.github.lib.SensorStepCallback;
import com.orangegangsters.github.lib.SensorStepService;
import com.orangegangsters.github.lib.SensorStepServiceManager;


public class StepCounter extends AppCompatActivity implements SensorEventListener, SensorStepCallback {
    String Missions[] = new String[]{"Push-ups 5 reps \n", "Mission two", "Mission three"};
    private Button viewMap;
    private ListView listView;
    private Boolean running = false;
    private EditText stepDisplay;
    private SensorManager sensorManager;
    boolean activityRunning;
    private float steps, stepsInSensor;
    private float OnOpen;
    private SensorStepService mSensorService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        /*listView = findViewById(R.id.listview1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Missions);*/
        createShareButton();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepDisplay = findViewById(R.id.stepsDisplay);
        activityRunning = false;
        mSensorService = new SensorStepServiceImpl(this);
        SensorStepServiceManager.startAutoUpdate(this);
        final Button back = findViewById(R.id.BackBtn3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });


    }



    public void goMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void goHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }



    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor !=null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_FASTEST);


        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }
    }
    protected void onPause() {
        super.onPause();
        running = false;

    }


    public void onSensorChanged(SensorEvent event){

       if(activityRunning){
            stepsInSensor = event.values[0];
           stepDisplay.setText(String.valueOf(stepsInSensor));
        }
        else {
            steps = event.values[0];

            stepDisplay.setText(String.valueOf(steps));
            steps = event.values[0];
            float stepsSinceReset = steps - stepsInSensor;
            //stepDisplay.setText(String.valueOf(stepsSinceReset));
        }





        /*while (steps <= 200 && steps >= 210){
            Context context = getApplicationContext();
            CharSequence text = "No user with this email or password";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(VibrationEffect.createOneShot(500,100));
            }
        }*/
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    @Override
    public void onUpdateSteps(int steps) {
        //SensorStepService.setCallback(this);

        //stepDisplay.setText(String.valueOf(mSensorService.getSteps()));

    }
    public void createShareButton(){
        ImageButton share = findViewById(R.id.shareButton8);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "I have just done " + steps  + " steps since last restart";
                String shareSub = "My step count";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share using"));
            }
        });

    }
}
