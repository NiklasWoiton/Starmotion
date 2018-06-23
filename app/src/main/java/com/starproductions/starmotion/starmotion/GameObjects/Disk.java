package com.starproductions.starmotion.starmotion.GameObjects;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.R;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffects;

public class Disk extends EnemyShip {

    private boolean nextShootLeft = true;

    /**
     * @param gameEngine: the GameEngine
     * @param x:          the initial x position of the ship
     * @param y:          the initial y position of the Ship
     */
    public Disk(GameEngine gameEngine, double x, double y) {
        super(gameEngine, x, y, R.drawable.spaceship_disk, GameConstants.DISK_SCALE_FACTOR);
        this.speedX = GameConstants.DISK_SPEED_X;
        this.speedY = GameConstants.DISK_SPEED_Y;
        this.health = GameConstants.DISK_HEALTH;
        this.score = GameConstants.DISK_SCORE;
        this.framesTillTurn = GameConstants.DISK_FRAMES_TILL_TURN;
        this.shootingCooldown = GameConstants.MS_BETWEEN_DISK_SHOTS;
    }

    @Override
    public void shoot() {
        if (nextShootLeft)
            new DiskLaser(gameEngine, x + asset.getWidth() * 0.25, y + asset.getHeight(), -GameConstants.LASER_SPEED_DISK / 2, GameConstants.LASER_SPEED_DISK, R.drawable.laser_round, GameConstants.LASER_SCALE_FACTOR_ROUND, false);
        else
            new DiskLaser(gameEngine, x + asset.getWidth() * 0.75, y + asset.getHeight(), GameConstants.LASER_SPEED_DISK / 2, GameConstants.LASER_SPEED_DISK, R.drawable.laser_round, GameConstants.LASER_SCALE_FACTOR_ROUND, false);
        nextShootLeft = !nextShootLeft;
        gameEngine.playSound(SoundEffects.LaserShoot);
    }

    @Override
    public void move() {
        x += speedX;
        y += speedY;
        framesTillTurn--;
        if (x <= 0) {
            speedX = GameConstants.DISK_SPEED_X;
            framesTillTurn = GameConstants.DISK_FRAMES_TILL_TURN;
        } else if (x >= GameConstants.SIZE.x - getHitBox().width()) {
            speedX = -GameConstants.DISK_SPEED_X;
            framesTillTurn = GameConstants.DISK_FRAMES_TILL_TURN;
        }
        else if (framesTillTurn <= 0) {
            speedX *= -1;
            framesTillTurn = GameConstants.DISK_FRAMES_TILL_TURN;
        }
    }
}
