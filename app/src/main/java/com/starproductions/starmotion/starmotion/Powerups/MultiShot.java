package com.starproductions.starmotion.starmotion.Powerups;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;
import com.starproductions.starmotion.starmotion.R;

/**
 * Created by Admin on 17.09.2017.
 */

class MultiShot extends PowerupObject {

    MultiShot(GameEngine gameEngine, PlayerShip player, double x, double y) {
        super(gameEngine, player);
        this.x = x;
        this.y = y;
    }

    @Override
    protected void applyEffect() {
        int shootMultiplikator = player.getShootMultiplikator() + 1;
        player.setShootMultiplikator(shootMultiplikator);
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources() , R.drawable.powerup_multishoot);
        int newWidth = (int) (GameConstants.SIZE.x * GameConstants.POWERUP_SCALE_FACTOR);
        int newHeight = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeight, true);
    }

    @Override
    public void move() {

    }
}
