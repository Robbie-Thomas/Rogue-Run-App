package com.example.roguerun2;

import com.orangegangsters.github.lib.BootCompletedReceiver;
import com.orangegangsters.github.lib.SensorStepServiceManager;

public class BootCompletedReceiverImpl extends BootCompletedReceiver {

    private SensorStepServiceManager mSensorManager;

    @Override
    public SensorStepServiceManager getSensorManagerImpl() {
        if(mSensorManager == null) {
            mSensorManager = new SensorStepServiceManagerImpl();
        }
        return mSensorManager;
    }
}
