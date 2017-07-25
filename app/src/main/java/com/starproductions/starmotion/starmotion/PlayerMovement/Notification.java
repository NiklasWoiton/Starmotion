package com.starproductions.starmotion.starmotion.PlayerMovement;

/**
 * Created by Shoggomo on 25.07.2017.
 */

/**
 * A object containing input information for the player
 */
public class Notification {
    private final float x;
    private final boolean touching;

    public Notification(float x, boolean touching) {
        this.x = x;
        this.touching = touching;
    }

    public float getX() {
        return x;
    }

    public boolean isTouching() {
        return touching;
    }

}
