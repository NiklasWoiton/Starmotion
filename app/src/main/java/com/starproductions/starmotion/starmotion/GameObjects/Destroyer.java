package com.starproductions.starmotion.starmotion.GameObjects;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.R;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffects;

public class Destroyer extends EnemyShip {

    /**
     * @param gameEngine: the GameEngine
     * @param x:          the initial x position of the ship
     * @param y:          the initial y position of the Ship
     */
    public Destroyer(GameEngine gameEngine, double x, double y) {
        super(gameEngine, x, y, R.drawable.spaceship_destroyer, GameConstants.DESTROYER_SCALE_FACTOR);
        this.speedX = GameConstants.DESTROYER_SPEED_X;
        this.speedY = GameConstants.DESTROYER_SPEED_Y;
        this.health = GameConstants.DESTROYER_HEALTH;
        this.score = GameConstants.DESTROYER_SCORE;
        this.framesTillTurn = GameConstants.DESTROYER_FRAMES_TILL_TURN;
        this.shootingCooldown = GameConstants.MS_BETWEEN_DESTROYER_SHOTS;
    }

    @Override
    public void shoot() {
        laser();
        gameEngine.playSound(SoundEffects.LaserShoot);
    }

    @Override
    public void move() {
        x += speedX;
        y += speedY;
        framesTillTurn--;
        if (x <= 0) {
            speedX = GameConstants.DESTROYER_SPEED_X;
            framesTillTurn = GameConstants.DESTROYER_FRAMES_TILL_TURN;
        } else if (x >= GameConstants.SIZE.x - getHitBox().width()) {
            speedX = -GameConstants.DESTROYER_SPEED_X;
            framesTillTurn = GameConstants.DESTROYER_FRAMES_TILL_TURN;
        }
        else if (framesTillTurn <= 0) {
            speedX *= -1;
            framesTillTurn = GameConstants.DESTROYER_FRAMES_TILL_TURN;
        }
    }

    private void laser() {
        destroyerLaser(0.25);
        destroyerLaser(0.75);
    }

    private void destroyerLaser(double horizontalOffset) {
        new Laser(gameEngine, x + asset.getWidth() * horizontalOffset, y + asset.getHeight(), 0, GameConstants.LASER_SPEED_DESTROYER, R.drawable.laser_red, GameConstants.LASER_SCALE_FACTOR_STRAIGHT, false);
    }
}
