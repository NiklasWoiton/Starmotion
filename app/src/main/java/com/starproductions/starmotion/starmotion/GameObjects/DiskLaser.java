package com.starproductions.starmotion.starmotion.GameObjects;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;

class DiskLaser extends Laser {

    DiskLaser(GameEngine gameEngine, double posX, double posY, double speedX, double speedY, int drawable, double drawableScale, boolean isPlayer) {
        super(gameEngine, posX, posY, speedX, speedY, drawable, drawableScale, isPlayer);
    }

    @Override
    public void move() {
        x += speedX;
        y += speedY;
        if (x <= 0) {
            speedX = GameConstants.LASER_SPEED_DISK;
        } else if (x >= GameConstants.SIZE.x - getHitBox().width())
            speedX = -GameConstants.LASER_SPEED_DISK;
    }
}
