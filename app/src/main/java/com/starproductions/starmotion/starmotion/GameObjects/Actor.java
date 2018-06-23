package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;

public abstract class Actor extends GameObject implements Collidable {

    protected int drawable;
    protected double drawableScale;

    double speedX;
    double speedY;

    public Actor(GameEngine gameEngine, int drawable, double drawableScale) {
        super(gameEngine);
        this.drawable = drawable;
        this.drawableScale = drawableScale;
        setAsset();
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources(), drawable);
        int newWidth = (int) (GameConstants.SIZE.x * drawableScale);
        int newHeight = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeight, true);
    }

    @Override
    public void draw(Canvas c, double extrapolation) {
        c.drawBitmap(asset, (float) (x + speedX * extrapolation), (float) (y + speedY * extrapolation), null);
    }

    @Override
    public Rect getHitBox() {
        return new Rect((int) x, (int) y, (int) (x + asset.getWidth()), (int) (y + asset.getHeight()));
    }
}
