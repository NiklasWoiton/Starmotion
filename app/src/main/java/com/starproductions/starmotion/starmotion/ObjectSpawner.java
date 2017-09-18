package com.starproductions.starmotion.starmotion;

import android.util.Log;

import com.starproductions.starmotion.starmotion.GameObjects.Destroyer;
import com.starproductions.starmotion.starmotion.GameObjects.Fighter;

import java.util.Random;

import static java.lang.Math.random;

/**
 * Created by jakob on 23.07.2017.
 */

public class ObjectSpawner {

    private GameEngine gameEngine;
    private ScoreHolder scoreHolder;
    private int millisToNextShip = 0;
    private int lastShipXPos = -1000;
    private Random randomSpawn = new Random();

    public ObjectSpawner(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        scoreHolder = gameEngine.getScoreHolder();
    }

    public  void update(){
        millisToNextShip -= GameConstants.MS_PER_UPDATE;
        if (millisToNextShip <= 0){
            spawnShip();
            millisToNextShip = (scoreHolder.getScore() >= GameConstants.SCORE_WITH_MAX_SHIPS) ? GameConstants.MS_BETWEEN_SHIPS_MIN : calcMillisToNextShip();
            Log.d("Test", "update: " + millisToNextShip);
        }
    }

    private int calcMillisToNextShip(){
        return GameConstants.MS_BETWEEN_SHIPS_MAX - (int)((GameConstants.MS_BETWEEN_SHIPS_MAX - GameConstants.MS_BETWEEN_SHIPS_MIN) *
                                                    (((double)scoreHolder.getScore()+1) / (double)GameConstants.SCORE_WITH_MAX_SHIPS));
    }

    private void spawnShip(){
        switch(randomSpawn.nextInt(2)){
            case 0:
                new Fighter(gameEngine, calcShipXPos(), GameConstants.START_ENEMY_SHIPS_Y_Factor*GameConstants.SIZE.y, calcShootingInterval());
                break;
            case 1:
                new Destroyer(gameEngine, calcShipXPos(), GameConstants.START_ENEMY_SHIPS_Y_Factor*GameConstants.SIZE.y, calcShootingInterval());
                break;
        }
    }

    private int calcShootingInterval(){
        return GameConstants.MS_BETWEEN_ENEMY_SHOTS_MAX - (int) (random() *
                (GameConstants.MS_BETWEEN_ENEMY_SHOTS_MAX - GameConstants.MS_BETWEEN_ENEMY_SHOTS_MIN));
    }

    private int calcShipXPos(){
        int posX;
        do {
            posX = (int) (random()*GameConstants.SIZE.x);
        } while (Math.abs(posX-lastShipXPos) <= (GameConstants.GAP_BETWEEN_ENEMY_SHIPS_X_FACTOR * GameConstants.SIZE.x));
        lastShipXPos = posX;
        return posX;
    }

}
