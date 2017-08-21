package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.PlayerInput.InputManager;
import com.starproductions.starmotion.starmotion.PlayerInput.Notification;
import com.starproductions.starmotion.starmotion.R;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffects;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jakob on 23.07.2017.
 */

public class PlayerShip extends SpaceShip implements Observer{

    private double speedX = 700;
    private double speedY = 0;
    private long lastShot = 0;
    private int life = GameConstants.PLAYER_START_LIFE;

    public PlayerShip(GameEngine gameEngine, InputManager inputManager){
        super(gameEngine);
        this.x = GameConstants.SIZE.x;
        this.y = GameConstants.SIZE.y * 0.8;

        inputManager.setSpeed((float) speedX);
        inputManager.setMaxX(GameConstants.SIZE.x - asset.getWidth());
        inputManager.addObserver(this);
        inputManager.start();
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life){
        this.life = life;
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources() , R.drawable.spaceship_player);
        int newWidth = (int) (GameConstants.SIZE.x * GameConstants.PLAYER_SHIP_SCALE_FACTOR);
        int newHeigth = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeigth, true);
    }

    @Override
    void shoot() {
        if( System.currentTimeMillis() - lastShot >= GameConstants.MS_BETWEEN_PLAYER_SHOOTS){
            lastShot = System.currentTimeMillis();
            new Laser(gameEngine, x + asset.getWidth()/2, y, 0, -2, true);
            gameEngine.playSound(SoundEffects.LaserShoot);
        }
    }

    @Override
    public void onCollide(Collidable obstacle) {

    }

    @Override
    public boolean isPlayer(){return true;}

    @Override
    public Rect getHitBox() {
        return new Rect((int) x, (int) y, (int) (x + asset.getWidth()), (int) (y + asset.getHeight()));
    }

    @Override
    public void move() {

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
        if(o instanceof Notification){
            Notification n = (Notification) o;
            x = n.getX();
            if( n.isTouching() ) shoot();
        }
    }
}
