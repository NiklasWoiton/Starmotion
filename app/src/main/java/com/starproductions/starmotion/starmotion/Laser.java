package com.starproductions.starmotion.starmotion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by jakob on 24.07.2017.
 */

public class Laser extends Actor {

    private double speedX = 0;
    private double speedY = 2;

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
        int newWidth = GameConstants.SIZE.x / 60;
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
