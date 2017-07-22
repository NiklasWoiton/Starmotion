package com.starproductions.starmotion.starmotion;

import android.graphics.Canvas;

/**
 * Created by Shoggomo on 11.07.2017.
 */

public abstract class GameObject {
    protected int x, y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    abstract public void draw(Canvas c, double extrapolation);
    abstract public void update();
}
