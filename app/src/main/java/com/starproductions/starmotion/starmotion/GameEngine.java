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
    private ArrayList<GameObject> toAdd = new ArrayList<GameObject>();
    private ArrayList<GameObject> toRemove = new ArrayList<GameObject>();

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
        refreshGameObjectsList();
    }

    public void draw(Canvas canvas, double extrapolation){
        canvas.drawColor(getResources().getColor(R.color.black));
        for (GameObject gameObject: gameObjects){
            gameObject.draw(canvas, extrapolation);
        }
    }

    private void checkOutOfScreen(){
        for (GameObject gameObject: gameObjects){
            if (Collidable.class.isInstance(gameObject)){
                try {
                    if (!(((Collidable) gameObject).getHitBox().intersect(activeSpace))){
                        gameObject.destroy();
                    }
                }catch (NullPointerException e){
                    Log.e("GameEngine", "checkOutOfScreen: Keine Boundingbox zurückgegeben", e);
                }
            }
        }
    }

    private void refreshGameObjectsList(){
        for (GameObject gameObject: toAdd){
            gameObjects.add(gameObject);
        }
        toAdd.clear();
        for (GameObject gameObject: toRemove){
            gameObjects.remove(gameObject);
        }
        toRemove.clear();
    }

    public void registerGameObject(GameObject gameObject){
        toAdd.add(gameObject);
        Log.d("GameObject Count", "registeredGameObjects: " + gameObjects.size());
    }

    public void deregisterGameObject(GameObject gameObject){
        toRemove.add(gameObject);
    }

    public Resources getResources(){
        return resources;
    }
}
