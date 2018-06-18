package com.starproductions.starmotion.starmotion.Powerups;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;

public class PowerupFactory {
    private GameEngine gameEngine;
    private PlayerShip player;

    public PowerupFactory(GameEngine gameEngine, PlayerShip player) {
        this.gameEngine = gameEngine;
        this.player = player;
    }


    public void createPowerup(double x, double y, int score) {
        double dropChance = score / GameConstants.POWERUP_DROPCHANCE_PER_SCORE;
        double drop = Math.random();
        if (drop < dropChance) {
            if (drop < GameConstants.POWERUP_DROPCHANCE_MULTISHOOT / (1 + 0.5 * player.getShootMultiplikator()))
                new MultiShot(gameEngine, player, x, y);
            else if (drop < GameConstants.POWERUP_DROPCHANCE_FIREUP / player.getFireRate())
                new FireUp(gameEngine, player, x, y);
            else new LifeUp(gameEngine, player, x, y);
        }
    }
}
