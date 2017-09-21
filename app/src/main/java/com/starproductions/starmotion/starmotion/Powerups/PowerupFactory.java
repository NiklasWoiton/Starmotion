package com.starproductions.starmotion.starmotion.Powerups;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;

import java.util.ArrayList;
import java.util.Random;

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

    public void createPowerup(double x, double y, double dropChance){
        if(Math.random() < dropChance){
            double random = Math.random();
            if(random < GameConstants.POWERUP_DROPCHANCE_LIFEUP){
                new LifeUp(gameEngine, player, x, y);
            }
            else{
                random -= GameConstants.POWERUP_DROPCHANCE_LIFEUP;
                if(random < GameConstants.POWERUP_DROPCHANCE_FIREUP){
                    new FireUp(gameEngine, player, x, y);
                }
                else {
                    random -= GameConstants.POWERUP_DROPCHANCE_FIREUP;
                    if (random < GameConstants.POWERUP_DROPCHANCE_MULTISHOOT){
                        new MultiShot(gameEngine, player, x, y);
                    }
                }
            }
        }
    }
}
