package com.starproductions.starmotion.starmotion;

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

    public GameEngine(){
    }

    public void update(){
        //Process Inputs
        //Check Collision
        for (GameObject gameObject: gameObjects){
            gameObject.update();
        }
    }

    public void draw(Canvas canvas, double extrapolation){
        for (GameObject gameObject: gameObjects){
            gameObject.draw(canvas, extrapolation);
        }
    }

    public void registerGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
    }

}
