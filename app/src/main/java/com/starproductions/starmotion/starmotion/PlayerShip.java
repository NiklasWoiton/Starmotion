package com.starproductions.starmotion.starmotion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.starproductions.starmotion.starmotion.PlayerInput.InputManager;
import com.starproductions.starmotion.starmotion.PlayerInput.Notification;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jakob on 23.07.2017.
 */

public class PlayerShip extends SpaceShip implements Observer{

    private double speedX = 500;
    private double speedY = 0;
    private long lastShot = 0;

    public PlayerShip(GameEngine gameEngine, InputManager inputManager){
        super(gameEngine);
        this.x = GameConstants.SIZE.x;
        this.y = GameConstants.SIZE.y * 0.8;

        inputManager.setSpeed((float) speedX);
        inputManager.addObserver(this);
        inputManager.start();
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources() , R.drawable.spaceship_player);
        int newWidth = GameConstants.SIZE.x / 10;
        int newHeigth = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeigth, true);
    }

    @Override
    void shoot() {
        if( System.currentTimeMillis() - lastShot >= GameConstants.MS_BETWEEN_PLAYER_SHOOTS){
            lastShot = System.currentTimeMillis();
            new Laser(gameEngine, x, y);
        }
    }

    @Override
    public void onCollide(Collidable obstacle) {

    }

    @Override
    public Rect getHitBox() {
        return new Rect((int) x, (int) y, (int) (x + asset.getWidth()), (int) (y + asset.getHeight()));
    }

    @Override
    void move() {

    }

    @Override
    public void draw(Canvas c, double extrapolation) {
        c.drawBitmap(asset, (float) x , (float) y, null);
    }

    @Override
    public void update() {
    }

    // Input update
    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof InputManager){
            Notification n = (Notification) o;
            x = n.getX();
            if( n.isTouching() ) shoot();
        }
    }
}
