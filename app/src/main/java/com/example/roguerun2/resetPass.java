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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class resetPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            final Button resetPass = findViewById(R.id.resetPassword);
            resetPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    resetPassword(user);
                }
            });

        }

        Button back = findViewById(R.id.backButton1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapToProfile();
            }
        });

    }

    public void resetPassword(FirebaseUser user){
        EditText passIn = findViewById(R.id.resetpassin);
        String newPass = passIn.getText().toString().trim();
        if (newPass.isEmpty()) {
            passIn.setError("Password is required");
            passIn.requestFocus();
            return;

        }
        if (passIn.length() < 6) {
            passIn.setError("Password must be longer than 6 characters.");
            passIn.requestFocus();
            return;
        }
        user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    swapToProfile();
                }
                Context context = getApplicationContext();
                CharSequence text = "Error resetting password please try again";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }

    public void swapToProfile(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
        finish();
    }


}
