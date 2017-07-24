package com.starproductions.starmotion.starmotion;

import java.util.Random;

/**
 * Created by jakob on 23.07.2017.
 */

public class ObjectSpawner {

    private GameEngine gameEngine;
    private int counter;

    public ObjectSpawner(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    public  void update(){
        counter += GameConstants.MS_PER_UPDATE;
        if (counter >= GameConstants.MS_BETWEEN_ENEMY_SHIPS){
            new EnemyShip(gameEngine, Math.random()*GameConstants.SIZE.x, GameConstants.START_ENEMY_SHIPS_Y_Factor*GameConstants.SIZE.y);
            counter -= GameConstants.MS_BETWEEN_ENEMY_SHIPS;
        }
    }

}
