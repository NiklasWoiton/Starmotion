package com.starproductions.starmotion.starmotion;

import android.util.Log;

import com.starproductions.starmotion.starmotion.GameObjects.EnemyShip;

/**
 * Created by jakob on 23.07.2017.
 */

public class ObjectSpawner {

    private GameEngine gameEngine;
    private ScoreHolder scoreHolder;
    private int millisToNextShip = 0;

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
        //Todo: Remove score increase
        scoreHolder.addScore(1);
    }

    private int calcMillisToNextShip(){
        return GameConstants.MS_BETWEEN_SHIPS_MAX - (int)((GameConstants.MS_BETWEEN_SHIPS_MAX - GameConstants.MS_BETWEEN_SHIPS_MIN) *
                                                    (((double)scoreHolder.getScore()+1) / (double)GameConstants.SCORE_WITH_MAX_SHIPS));
    }

    private void spawnShip(){
        new EnemyShip(gameEngine, Math.random()*GameConstants.SIZE.x, GameConstants.START_ENEMY_SHIPS_Y_Factor*GameConstants.SIZE.y, calcShootingInterval());
    }

    private int calcShootingInterval(){
        return GameConstants.MS_BETWEEN_ENEMY_SHOTS_MAX - (int) (Math.random() *
                (GameConstants.MS_BETWEEN_ENEMY_SHOTS_MAX - GameConstants.MS_BETWEEN_ENEMY_SHOTS_MIN));
    }

}
