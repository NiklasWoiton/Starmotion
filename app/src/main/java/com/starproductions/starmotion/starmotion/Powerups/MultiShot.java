package com.starproductions.starmotion.starmotion.Powerups;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;
import com.starproductions.starmotion.starmotion.R;

class MultiShot extends PowerupObject {

    MultiShot(GameEngine gameEngine, PlayerShip player, double x, double y) {
        super(gameEngine, player, R.drawable.powerup_multishoot, GameConstants.POWERUP_SCALE_FACTOR);
        this.x = x;
        this.y = y;
    }

    @Override
    protected void applyEffect() {
        if (player.getShootMultiplikator() < GameConstants.POWERUP_MULTISHOOT_MAX) {
            int shootMultiplikator = player.getShootMultiplikator() + 1;
            player.setShootMultiplikator(shootMultiplikator);
        }
    }
}
