package com.starproductions.starmotion.starmotion.GameObjects;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.R;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffects;

public class Fighter extends EnemyShip {

    /**
     * @param gameEngine: the GameEngine
     * @param x:          the initial x position of the ship
     * @param y:          the initial y position of the Ship
     */
    public Fighter(GameEngine gameEngine, double x, double y) {
        super(gameEngine, x, y, R.drawable.spaceship_fighter, GameConstants.FIGHTER_SCALE_FACTOR);
        this.speedX = GameConstants.FIGHTER_SPEED_X;
        this.speedY = GameConstants.FIGHTER_SPEED_Y;
        this.health = GameConstants.FIGHTER_HEALTH;
        this.score = GameConstants.FIGHTER_SCORE;
        this.framesTillTurn = GameConstants.FIGHTER_FRAMES_TILL_TURN;
        this.shootingCooldown = GameConstants.MS_BETWEEN_FIGHTER_SHOTS;
    }

    @Override
    public void shoot() {
        laser();
        gameEngine.playSound(SoundEffects.LaserShoot);
        calcShootingInterval();
    }

    @Override
    public void move() {
        x += speedX;
        y += speedY;
        framesTillTurn--;
        if (framesTillTurn <= 0) {
            turn();
        }
    }

    public void turn() {
        speedX *= -1;
        framesTillTurn = GameConstants.FIGHTER_FRAMES_TILL_TURN;
    }

    private void laser() {
        new Laser(gameEngine, x + asset.getWidth() / 2, y + asset.getHeight(), 0, GameConstants.LASER_SPEED_FIGHTER, R.drawable.laser_violet, GameConstants.LASER_SCALE_FACTOR_STRAIGHT, false);
    }
}
