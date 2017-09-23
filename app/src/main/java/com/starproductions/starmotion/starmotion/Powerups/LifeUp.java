package com.starproductions.starmotion.starmotion.Powerups;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;
import com.starproductions.starmotion.starmotion.R;


class LifeUp extends PowerupObject {

    LifeUp(GameEngine gameEngine, PlayerShip player, double x, double y) {
        super(gameEngine, player);
        this.x = x;
        this.y = y;
    }

    @Override
    protected void applyEffect() {
        int newLife = player.getLife() + 1;
        player.setLife(newLife);
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources(), R.drawable.powerup_lifeup);
        int newWidth = (int) (GameConstants.SIZE.x * GameConstants.POWERUP_SCALE_FACTOR);
        int newHeight = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeight, true);
    }

    @Override
    public void move() {

    }
}
