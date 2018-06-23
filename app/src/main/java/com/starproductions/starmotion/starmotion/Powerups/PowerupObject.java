package com.starproductions.starmotion.starmotion.Powerups;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.GameObjects.Actor;
import com.starproductions.starmotion.starmotion.GameObjects.Collidable;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffects;

abstract class PowerupObject extends Actor {

    protected PlayerShip player;
    protected double speedY = GameConstants.POWERUP_SPEED_Y;

    PowerupObject(GameEngine gameEngine, PlayerShip player, int drawable, double drawableScale) {
        super(gameEngine, drawable, drawableScale);
        this.player = player;
    }

    @Override
    public void onCollide(Collidable obstacle) {
        gameEngine.playSound(SoundEffects.Powerup);
        if (obstacle instanceof PlayerShip) {
            applyEffect();
            destroy();
        }
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void move() {
        y += speedY;
    }

    protected abstract void applyEffect();

}
