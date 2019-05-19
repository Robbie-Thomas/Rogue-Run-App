package com.example.roguerun2;
import com.orangegangsters.github.lib.SensorStepReceiver;

public class SensorStepReceiverImpl extends SensorStepReceiver {
    @Override
    public Class getServiceClass() {
        return SensorStepServiceImpl.class;
    }
}
