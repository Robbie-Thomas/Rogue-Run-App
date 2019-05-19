package com.example.roguerun2;

import com.orangegangsters.github.lib.SensorStepServiceManager;

public class SensorStepServiceManagerImpl extends SensorStepServiceManager {
    @Override
    public Class getReceiverClass() {
        return SensorStepReceiverImpl.class;
    }
}