package com.starproductions.starmotion.starmotion.PlayerInput;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

/**
 * Observable to calculate the current X coordinate of the player.
 * Call addObserver() after creation.
 * Observer gets an
 */
public class InputManager extends Observable implements Observer {
    private final OrientationController oc;
    private final KeyController kc;
    private boolean keyboardActive = false;
    private int maxX;
    private float currentX = maxX / 2;
    private float speed = 1;
    private Notification lastNotification = new Notification(0, false);

    /**
     * @param activity The calling activity
     * @param maxX     The maximum X coordinate (should be screen width - the player's width)
     */
    public InputManager(Activity activity, int maxX) {
        this.maxX = maxX;
        oc = new OrientationController(activity);
        oc.addObserver(this);
        kc = new KeyController(this);
        kc.addObserver(this);
    }

    /**
     * (Re)starts the movement calculation (should be in onResume()).
     */
    public void start() {
        if (!keyboardActive) oc.start();
    }

    /**
     * Stops the movement calculations to save power (should be in onPause()).
     */
    public void stop() {
        oc.stop();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof OrientationController) {
            float[] orientation = (float[]) o;
            float tilt = orientation[1];
            float x = calculateX(tilt);
            notifyPosition(x);
        }

        if (observable instanceof KeyController) {
            float velocity = (float) o;
            float x = calculateXKeyboard(velocity);
            notifyPosition(x);
        }
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    private float calculateX(float tilt) {
        float x = maxX / 2 + tilt * speed;
        x = capX(x);
        return x;
    }

    private float calculateXKeyboard(float velocity) {
        currentX += velocity;
        currentX = capX(currentX);
        return currentX;
    }

    private float capX(float x) {
        float result;
        result = x <= 0 ? 0 : x;
        result = x >= maxX ? maxX : result;
        return result;
    }

    private void notifyPosition(float x) {
        if (countObservers() > 0) {
            setChanged();
            lastNotification = new Notification(x, lastNotification.isTouching());
            notifyObservers(lastNotification);
        }
    }


    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                notifyTouch(true);
                return true;

            case MotionEvent.ACTION_UP:
                notifyTouch(false);
                return false;
        }
        return true;
    }

    public boolean onTouchEmulation(KeyEvent keyEvent) {
        switch (keyEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                notifyTouch(true);
                return true;

            case MotionEvent.ACTION_UP:
                notifyTouch(false);
                return false;
        }
        return true;
    }

    private void notifyTouch(boolean isTouching) {
        if (countObservers() > 0) {
            setChanged();
            lastNotification = new Notification(lastNotification.getX(), isTouching);
            notifyObservers(lastNotification);
        }
    }

    public boolean onKey(int keyCode, KeyEvent keyEvent) {
        if (!keyboardActive) {
            switchKeyboardActive();
        }
        return kc.onKey(keyCode, keyEvent);
    }

    public void switchKeyboardActive() {
        if (keyboardActive) {
            keyboardActive = false;
            start();
        } else {
            keyboardActive = true;
            stop();
        }

    }
}