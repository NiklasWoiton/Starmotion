package com.starproductions.starmotion.starmotion;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Shoggomo on 11.07.2017.
 */

public abstract class GameObject {
    protected double x, y;
    protected GameEngine gameEngine;
    protected Bitmap asset;


    public GameObject(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        gameEngine.registerGameObject(this);
        setAsset();
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

    abstract protected void setAsset();
    abstract public void draw(Canvas c, double extrapolation);
    abstract public void update();
}
