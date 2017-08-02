package com.starproductions.starmotion.starmotion;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.starproductions.starmotion.starmotion.GameObjects.Actor;
import com.starproductions.starmotion.starmotion.GameObjects.GameObject;
import com.starproductions.starmotion.starmotion.GameObjects.HudObject;
import com.starproductions.starmotion.starmotion.GameObjects.Lifebar;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;
import com.starproductions.starmotion.starmotion.GameObjects.ScoreDisplay;
import com.starproductions.starmotion.starmotion.PlayerInput.InputManager;
import com.starproductions.starmotion.starmotion.Powerups.PowerupFactory;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffectManager;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffects;

import java.util.ArrayList;

/**
 * Created by jakob on 18.07.2017.
 */

public class GameEngine {

    private ArrayList<Actor> gameActors = new ArrayList<>();
    private ArrayList<HudObject> hudObjects = new ArrayList<>();
    private ArrayList<GameObject> toAdd = new ArrayList<>();
    private ArrayList<GameObject> toRemove = new ArrayList<>();

    private ObjectSpawner objectSpawner;
    private Resources resources;
    private Rect activeSpace;
    private ScoreHolder scoreHolder = new ScoreHolder();
    private PowerupFactory powerupFactory;
    private SoundEffectManager soundEffectManager;

    public GameEngine(Resources resources, InputManager inputManager, SoundEffectManager soundEffectManager){
        this.resources = resources;
        this.soundEffectManager = soundEffectManager;
        activeSpace = new Rect( (int) (GameConstants.SIZE.x*-GameConstants.DESPAWN_BORDER_Factor),
                                (int) (GameConstants.SIZE.y*-GameConstants.DESPAWN_BORDER_Factor),
                                (int) (GameConstants.SIZE.x*(GameConstants.DESPAWN_BORDER_Factor + 1)),
                                (int) (GameConstants.SIZE.y*(GameConstants.DESPAWN_BORDER_Factor + 1)));

        objectSpawner = new ObjectSpawner(this);
        PlayerShip playerShip = new PlayerShip(this, inputManager);
        new Lifebar(this, playerShip);
        new ScoreDisplay(this);
        powerupFactory = new PowerupFactory(this, playerShip);
    }

    public PowerupFactory getPowerupFactory(){
        return powerupFactory;
    }

    public ScoreHolder getScoreHolder() {
        return scoreHolder;
    }

    public void playSound(SoundEffects soundEffect){
        soundEffectManager.playSound(soundEffect);
    }

    public void update(){
        //Process Inputs
        //Check Collision
        checkOutOfScreen();
        objectSpawner.update();
        for (GameObject gameObject: gameActors){
            gameObject.update();
        }
        refreshGameObjectsList();
    }

    public void draw(Canvas canvas, double extrapolation){
        canvas.drawColor(getResources().getColor(R.color.black));
        for (Actor actor: gameActors){
            actor.draw(canvas, extrapolation);
        }
        for(HudObject hudObject: hudObjects){
            hudObject.draw(canvas, extrapolation);
        }
    }

    private void checkOutOfScreen() {
        for (Actor actor : gameActors) {
            try {
                if (!(actor.getHitBox().intersect(activeSpace))) {
                    actor.destroy();
                }
            } catch (NullPointerException e) {
                Log.e("GameEngine", "checkOutOfScreen: Keine Boundingbox zurückgegeben", e);
            }
        }
    }

    private void refreshGameObjectsList(){
        for (GameObject gameObject: toAdd){
            if(gameObject instanceof Actor)
                gameActors.add((Actor) gameObject);
            else if(gameObject instanceof HudObject)
                hudObjects.add((HudObject) gameObject);
        }
        toAdd.clear();
        for (GameObject gameObject: toRemove){
            if(gameObject instanceof Actor)
                gameActors.remove(gameObject);
            else if(gameObject instanceof HudObject)
                hudObjects.remove(gameObject);
        }
        toRemove.clear();
    }

    public void registerGameObject(GameObject gameObject){
        toAdd.add(gameObject);
        Log.d("GameObject Count", "registeredGameObjects: " + gameActors.size());
    }

    public void deregisterGameObject(GameObject gameObject){
        toRemove.add(gameObject);
    }

    public Resources getResources(){
        return resources;
    }
}
