package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.R;

/**
 * Created by Admin on 21.09.2017.
 */

class PlayerLaser extends Laser {

    PlayerLaser(GameEngine gameEngine, double posX, double posY, double speedX, double speedY) {
        super(gameEngine, posX, posY, speedX, speedY);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources(), R.drawable.bullet_blue);
        int newWidth = (int) (GameConstants.SIZE.x * GameConstants.LASER_SCALE_FACTOR_STRAIGHT);
        int newHeight = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeight, true);
    }
}
