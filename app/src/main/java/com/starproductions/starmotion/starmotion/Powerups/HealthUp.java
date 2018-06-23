package com.starproductions.starmotion.starmotion.Powerups;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;
import com.starproductions.starmotion.starmotion.R;

class HealthUp extends PowerupObject {

    HealthUp(GameEngine gameEngine, PlayerShip player, double x, double y) {
        super(gameEngine, player, R.drawable.powerup_healthup, GameConstants.POWERUP_SCALE_FACTOR);
        this.x = x;
        this.y = y;
    }

    @Override
    protected void applyEffect() {
        int newHealth = player.getHealth() + 1;
        player.setHealth(newHealth);
    }
}
