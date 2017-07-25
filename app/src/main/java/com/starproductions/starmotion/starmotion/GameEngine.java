package com.starproductions.starmotion.starmotion;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.starproductions.starmotion.starmotion.PlayerInput.InputManager;

import java.util.ArrayList;

/**
 * Created by jakob on 18.07.2017.
 */

public class GameEngine {

    private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    private ObjectSpawner objectSpawner;
    private Resources resources;
    private Rect activeSpace;

    public GameEngine(Resources resources, InputManager inputManager){
        this.resources = resources;
        activeSpace = new Rect( (int) (GameConstants.SIZE.x*-GameConstants.DESPAWN_BORDER_Factor),
                                (int) (GameConstants.SIZE.y*-GameConstants.DESPAWN_BORDER_Factor),
                                (int) (GameConstants.SIZE.x*(GameConstants.DESPAWN_BORDER_Factor + 1)),
                                (int) (GameConstants.SIZE.y*(GameConstants.DESPAWN_BORDER_Factor + 1)));
        objectSpawner = new ObjectSpawner(this);
        new PlayerShip(this, inputManager);
    }

    public void update(){
        //Process Inputs
        //Check Collision
        checkOutOfScreen();
        objectSpawner.update();
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

    public void checkOutOfScreen(){
        GameObject[] toDestroy = new GameObject[gameObjects.size()];
        int iterator = 0;
        for (GameObject gameObject: gameObjects){
            if (Collidable.class.isInstance(gameObject)){
                try {
                    if (!(((Collidable) gameObject).getHitBox().intersect(activeSpace))){
                        toDestroy[iterator] = gameObject;
                        iterator++;
                    }
                }catch (NullPointerException e){
                    Log.e("GameEngine", "checkOutOfScreen: Keine Boundingbox zurückgegeben", e);
                }


            }
        }
        for (int i = 0; i < iterator; i++){
            toDestroy[i].destroy();
        }
    }

    public void registerGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
        Log.d("GameObject Count", "registeredGameObjects: " + gameObjects.size());
    }

    public void deregisterGameObject(GameObject gameObject){
        gameObjects.remove(gameObject);
    }

    public Resources getResources(){
        return resources;
    }
}
