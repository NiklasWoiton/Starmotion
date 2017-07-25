package com.starproductions.starmotion.starmotion.PlayerInput;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Observable;

/**
 * Observable to get the latest orientation vector of the device
 */
class OrientationController extends Observable implements SensorEventListener {

    private final SensorManager sensorManager;

    OrientationController(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    void start() {
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), SensorManager.SENSOR_DELAY_GAME);
    }

    void stop() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            float[] orientation = calcOrientation(event.values);
            notify(orientation);
        }
    }

    /**
     * Calculates orientation from a rotation vector
     * @param rotationVector rotationVector from a vector sensor
     * @return float[3]:
     * [0]: rotation around x axis
     * [1]: rotation around y axis
     * [2]: rotation around z axis
     */
    private float[] calcOrientation(float[] rotationVector) {
        float[] rotationMatrix = new float[16];
        float[] orientation = new float[3];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, rotationVector);
        SensorManager.getOrientation(rotationMatrix, orientation);
            /*
             * [0] : azi, rotation around z axis
             * [1] : pitch, rotation around x axis
             * [2] : roll, rotation around y axis
             * */
        float azi = orientation[0];
        float pitch = orientation[1];
        float roll = orientation[2];

        return new float[] {pitch, roll, azi};
    }

    private void notify(float[] orientation) {
        if (countObservers() > 0) {
            setChanged();
            notifyObservers(orientation);
        }
    }

}
