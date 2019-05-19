package com.example.roguerun2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.orangegangsters.github.lib.SensorStepService;

public class Mission extends AppCompatActivity  {
    ListView listView;
    private SensorStepService mSensorService;

    String Missions[] = new String[]{"Step Counter", "Mission One", "Mission Two", "Mission Three", "Mission Four"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
        createMissionList();
    }

    public void createMissionList(){
        listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Missions);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(Mission.this.Missions[position]) {
                    case "Step Counter":
                       toStep();
                        break;
                    case "Mission One":
                        toMission1();
                        break;
                    case "Mission Two":
                        toMission2();
                        break;
                    case "Mission Three":
                        toMission3();
                        break;
                    case "Mission Four":
                        toMission4();
                        break;


                }
            }
        });

    }

    public void toStep(){
        Intent intent = new Intent(this, StepCounter.class);
        startActivity(intent);
    }
    public void toMission1(){
        Intent intent = new Intent(this, MissionOne.class);
        startActivity(intent);
    }
    public void toMission2(){
        Intent intent = new Intent(this, MissionTwo.class);
        startActivity(intent);
    }
    public void toMission3(){
        Intent intent = new Intent(this, MissionThree.class);
        startActivity(intent);
    }
    public void toMission4(){
        Intent intent = new Intent(this, MissionFour.class);
        startActivity(intent);
    }


}



