package com.starproductions.starmotion.starmotion.Powerups;

import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;

/**
 * Created by Shoggomo on 28.07.2017.
 */

public class PowerupFactory {
    private GameEngine gameEngine;
    private PlayerShip player;

    public PowerupFactory(GameEngine gameEngine, PlayerShip player){
        this.gameEngine = gameEngine;
        this.player = player;
    }

    public void createPowerup(PowerupTypes type, double x, double y){
        switch (type){
            case Lifeup:
                new LifeUp(gameEngine, player, x, y);
                break;
        }
    }
}
