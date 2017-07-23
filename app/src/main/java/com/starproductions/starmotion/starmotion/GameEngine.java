package com.starproductions.starmotion.starmotion;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakob on 18.07.2017.
 */

public class GameEngine {

    private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    private Resources resources;

    public GameEngine(Resources resources){
        this.resources = resources;
        new EnemyShip(this, 100, 100);
    }

    public void update(){
        //Process Inputs
        //Check Collision
        for (GameObject gameObject: gameObjects){
            gameObject.update();
        }
    }

    public void draw(Canvas canvas, double extrapolation){
        canvas.drawColor(getResources().getColor(R.color.black));
        for (GameObject gameObject: gameObjects){
            gameObject.draw(canvas, extrapolation);
        }
    }

    public void registerGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
    }

    public Resources getResources(){
        return resources;
    }
}
