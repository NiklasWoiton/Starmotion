package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.R;

/**
 * Created by Admin on 21.09.2017.
 */

class DiskLaser extends Laser {

    DiskLaser(GameEngine gameEngine, double posX, double posY, double speedX, double speedY) {
        super(gameEngine, posX, posY, speedX, speedY);
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources(), R.drawable.bullet_round);
        int newWidth = (int) (GameConstants.SIZE.x * GameConstants.LASER_SCALE_FACTOR_ROUND);
        int newHeight = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeight, true);
    }

    @Override
    public void update() {
        x += speedX;
        y += speedY;
        if (x <= 0) {
            speedX = GameConstants.LASER_SPEED_DISK;
        } else if (x >= GameConstants.SIZE.x - getHitBox().width())
            speedX = -GameConstants.LASER_SPEED_DISK;
    }
}
