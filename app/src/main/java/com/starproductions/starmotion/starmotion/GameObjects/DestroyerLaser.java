package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.R;

class DestroyerLaser extends Laser {

    DestroyerLaser(GameEngine gameEngine, double posX, double posY, double speedX, double speedY) {
        super(gameEngine, posX, posY, speedX, speedY);
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources(), R.drawable.bullet_violet);
        int newWidth = (int) (GameConstants.SIZE.x * GameConstants.LASER_SCALE_FACTOR_STRAIGHT);
        int newHeight = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeight, true);
    }
}
