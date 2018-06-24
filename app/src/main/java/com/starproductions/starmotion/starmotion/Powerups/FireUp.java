package com.starproductions.starmotion.starmotion.Powerups;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;
import com.starproductions.starmotion.starmotion.R;

class FireUp extends PowerupObject {

    FireUp(GameEngine gameEngine, PlayerShip player, double x, double y) {
        super(gameEngine, player, R.drawable.powerup_fireup, GameConstants.POWERUP_SCALE_FACTOR);
        this.x = x;
        this.y = y;
    }

    @Override
    protected void applyEffect() {
        double newFireRate = player.getFireRate() + GameConstants.POWERUP_FIREUP_INCREASE;
        player.setFireRate(newFireRate);
    }
}
