package com.starproductions.starmotion.starmotion.PlayerMovement;

import android.content.Context;
import java.util.Observable;
import java.util.Observer;

/**
 * Observable to calculate the current X coordinate of the player.
 * Call addObserver() after creation.
 * Observer gets an
 */
public class MovementManager extends Observable implements Observer{
    private final OrientationController oc;
    private final int maxX;
    private float speed = 1;

    /**
     *
     * @param context The calling activity
     * @param maxX The maximum X coordinate (should be screen width - the player's width)
     */
    public MovementManager(Context context, int maxX){
        this.maxX = maxX;
        oc = new OrientationController(context);
        oc.addObserver(this);
    }

    /**
     * (Re)starts the movement calculation (should be in onResume()).
     */
    public void start(){
        oc.start();
    }

    /**
     * Stops the movement calculations to save power (should be in onPause()).
     */
    public void stop(){
        oc.stop();
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof OrientationController){
            float[] orientation = (float[]) o;
            float tilt = orientation[1];
            float x = calculateX(tilt);
            notify(x);
        }
    }

    public float getSpeed(){
        return speed;
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    private float calculateX(float tilt) {
        float x = maxX/2 + tilt * speed;
        x = capX(x);
        return x;
    }

    private float capX(float x){
        float result;
        result = x <= 0 ? 0 : x;
        result = x >= maxX ? maxX : result;
        return result;
    }

    private void notify(float x){
        if(countObservers() > 0) {
            setChanged();
            notifyObservers(x);
        }
    }
}
