package com.starproductions.starmotion.starmotion;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.ArrayList;

/**
 * Created by Shoggomo on 12.07.2017.
 */

public class RotationController implements SensorEventListener {
    private static final int SAMPLING_RATE = SensorManager.SENSOR_DELAY_GAME;

    private ArrayList<RotationListener> listeners = new ArrayList<>();

    private SensorManager mSensorManager;
    private Sensor mSensor;

    public RotationController(Context context){
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    }

    public void start(){
        mSensorManager.registerListener(this, mSensor, SAMPLING_RATE);
    }

    public void stop(){
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
                float x = sensorEvent.values[0],
                        y = sensorEvent.values[1],
                        z = sensorEvent.values[2];
                emitEvent(x, y, z);
            }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    //Observer methods
    public void registerListener(RotationListener listener){
        listeners.add(listener);
    }

    public void unregisterListener(RotationListener listener){
        listeners.remove(listener);
    }

    public void emitEvent(float x, float y, float z){
        for (RotationListener listener : listeners)
            listener.onRotation(x, y, z);
    }

}
