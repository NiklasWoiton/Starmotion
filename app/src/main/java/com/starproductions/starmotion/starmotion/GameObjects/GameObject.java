package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.starproductions.starmotion.starmotion.GameEngine;

/**
 * Created by Shoggomo on 11.07.2017.
 */

public abstract class GameObject {

    protected double x, y;
    protected GameEngine gameEngine;
    protected Bitmap asset;

    GameObject(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        setAsset();
        gameEngine.registerGameObject(this);
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //Call this and the GameObject will destroy itself.
    //Override to add further actions
    public void destroy() {
        gameEngine.deregisterGameObject(this);
    }

    abstract protected void setAsset();

    abstract public void draw(Canvas c, double extrapolation);

    abstract public void update();
}
