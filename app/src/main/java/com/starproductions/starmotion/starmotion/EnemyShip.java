package com.starproductions.starmotion.starmotion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by jakob on 23.07.2017.
 */

public class EnemyShip extends SpaceShip {

    private double speedX = 0.2;
    private double speedY = 1;

    public EnemyShip(GameEngine gameEngine, double x, double y){
        super(gameEngine);
        this.x = x;
        this.y = y;
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources() , R.drawable.spaceship_tut);
        int newWidth = GameConstants.SIZE.x / 10;
        int newHeigth = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeigth, true);
    }

    @Override
    void shoot() {

    }

    @Override
    public void onCollide(Collidable obstacle) {

    }

    @Override
    public Rect getHitBox() {
        return null;
    }

    @Override
    void move() {

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
