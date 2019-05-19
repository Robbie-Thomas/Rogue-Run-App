package com.example.roguerun2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class myStats extends AppCompatActivity {

    private EditText ageIn, heightIn, weightIn, BDF, BMIin;
    private Button enterStatsBTN;
    private ToggleButton mFBtn;
    public User userStats;
    private DatabaseReference databaseReference, db2;

    double BodyFat;
    double age;
    double height;
    double weight;
    //Double bmi
    double bmi;



    //
    private FirebaseAuth firebaseAuth;
    private FirebaseUser userdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stats);
        getStats();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        db2 = FirebaseDatabase.getInstance().getReference().child(id);
    }


    public void saveUser(){
        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        userStats = new User(id,age,height,bmi,weight,BodyFat, false,false,false,false);
        databaseReference.child(userStats.getID()).setValue(userStats);

    }



    public void getStats(){
        ageIn = findViewById(R.id.AgeIn);
        heightIn = findViewById(R.id.HeightIn);
        weightIn = findViewById(R.id.WeightIn);
        enterStatsBTN = findViewById(R.id.enterstatsbtn);
        mFBtn = findViewById(R.id.toggleButton);
        BDF = findViewById(R.id.BDF);
        BMIin = findViewById(R.id.BMI2);
        enterStatsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              age =Double.parseDouble(ageIn.getText().toString());
              height = Double.parseDouble(heightIn.getText().toString());
              weight  = Double.parseDouble(weightIn.getText().toString());

                BDF.setText(String.valueOf(BodyFat));
                BMIin.setText(String.valueOf(bmi));
                EditText youAre = findViewById(R.id.Youare);
                youAre.setText(Weight(bmi));
                if (ageIn.getText().toString().isEmpty()) {
                    ageIn.setError("Enter an Age");
                    ageIn.requestFocus();
                    return;
                }
                if (heightIn.getText().toString().isEmpty()) {
                    heightIn.setError("Enter an Height");
                    heightIn.requestFocus();
                    return;
                }
                if (weightIn.getText().toString().isEmpty()) {
                    weightIn.setError("Enter an Weight");
                    weightIn.requestFocus();
                    return;
                }
                bmi = (weight /(Math.pow(height,2))*10000);
                if(mFBtn.isChecked()){
                    //(1.20 x BMI) + (0.23 x age) – 5.4
                    BodyFat = ((1.20 * bmi) + (0.23 * age) - 5.4);
                }else{
                    BodyFat = ((1.20 * bmi) + (0.23 * age) - 16.2);
                }
             saveUser();
                goBack();
            }
        });
    }

    public String Weight (double bmi){
        String bound= "";
        if(bmi <18.5) bound="Under Weight";
        if(bmi >=18.5 && bmi < 24.9) bound="Healthy Weight";
        if(bmi >=30 && bmi < 34.9) bound="~Over Weight";
        if(bmi >=34.9) bound="Obese";
        return bound;
    }

    public void goBack(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }

}
