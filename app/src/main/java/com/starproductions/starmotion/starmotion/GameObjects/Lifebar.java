package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.R;

public class Lifebar extends HudObject {
    private PlayerShip player;

    public Lifebar(GameEngine gameEngine, PlayerShip player) {
        super(gameEngine);
        this.player = player;
        x = GameConstants.SIZE.x * 0.05;
        y = GameConstants.SIZE.y * 0.05;
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources(), R.drawable.heart);
        int newWidth = GameConstants.SIZE.x / 20;
        int newHeight = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeight, true);
    }

    @Override
    public void draw(Canvas c, double extrapolation) {
        float space = asset.getWidth();
        for (int i = 0; i < player.getLife(); i++)
            c.drawBitmap(asset, (float) x + i * space, (float) y, null);
    }

    @Override
    public void update() {

    }
}
