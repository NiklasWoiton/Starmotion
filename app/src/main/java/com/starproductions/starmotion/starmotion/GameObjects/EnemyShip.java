package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.R;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffects;

/**
 * Created by jakob on 23.07.2017.
 */

public class EnemyShip extends SpaceShip {

    private double speedX = 0;
    private double speedY = 1;

    public EnemyShip(GameEngine gameEngine, double x, double y){
        super(gameEngine);
        this.x = x;
        this.y = y;
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources() , R.drawable.spaceship_tut);
        int newWidth = (int) (GameConstants.SIZE.x * GameConstants.ENEMY_SHIP_SCALE_FACTOR);
        int newHeight = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeight, true);
    }

    @Override
    void shoot() {
        new Laser(gameEngine, x + asset.getWidth()/2, y + asset.getHeight(), 0, 2);
            gameEngine.playSound(SoundEffects.LaserShoot);
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
    public void draw(Canvas c, double extrapolation) {
        c.drawBitmap(asset, (float)(x + speedX*extrapolation), (float)(y + speedY*extrapolation), null);
    }

    @Override
    public void update() {
        x += speedX;
        y += speedY;
        if (Math.random() > 0.999) shoot();
    }
}
