package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.R;

/**
 * Created by jakob on 24.07.2017.
 */

public class Laser extends Actor {

    private double speedX;
    private double speedY;

    public Laser(GameEngine gameEngine, double posX, double posY, double speedX, double speedY){
        super(gameEngine);

        this.x = posX - asset.getWidth()/2;
        if (speedY > 0){
            this.y = posY;
        } else {
            this.y = posY - asset.getHeight();
        }
        this.speedX = speedX;
        this.speedY = speedY;
    }

    @Override
    public void onCollide(Collidable obstacle) {

    }

    @Override
    public Rect getHitBox() {
        return new Rect((int) x, (int) y, (int) (x + asset.getWidth()), (int) (y + asset.getHeight()));
    }

    @Override
    public void move() {

    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources() , R.drawable.bullet);
        int newWidth = (int) (GameConstants.SIZE.x * GameConstants.LASER_SCALE_FACTOR);
        int newHeight = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeight, true);
    }

    @Override
    public void draw(Canvas c, double extrapolation) {
        c.drawBitmap(asset, (float)(x + speedX*extrapolation), (float)(y + speedY*extrapolation), null);
    }

    @Override
    public void update() {
        x += speedX;
        y += speedY;
    }
}
