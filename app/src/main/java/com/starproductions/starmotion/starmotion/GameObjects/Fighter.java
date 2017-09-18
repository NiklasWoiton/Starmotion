package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.Powerups.PowerupTypes;
import com.starproductions.starmotion.starmotion.R;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffects;

import java.util.Random;

/**
 * Created by jakob on 23.07.2017.
 */

public class Fighter extends SpaceShip {

    private double speedX = 0;
    private double speedY = GameConstants.FIGTHER_SPEED;
    private int health = GameConstants.FIGHTER_HEALTH;

    private int shootingInterval;
    private int framesTillShooting;

    /**
     * @param gameEngine: the GameEngine
     * @param x: the initial x position of the ship
     * @param y: the initial y position of the Ship
     * @param shootingInterval: the interval between the shots in Milliseconds
     */
    public Fighter(GameEngine gameEngine, double x, double y, int shootingInterval){
        super(gameEngine);
        this.x = x;
        this.y = y;
        this.shootingInterval = (int) (shootingInterval * GameConstants.FIGHTER_INTERVAL_MOD);
        framesTillShooting = shootingInterval;
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
        new Laser(gameEngine, x + asset.getWidth()/2, y + asset.getHeight(), 0, 2, isPlayer());
            gameEngine.playSound(SoundEffects.LaserShoot);
    }

    @Override
    public void onCollide(Collidable obstacle) {
        health--;
        if(health <= 0){
            onDeath();
        }
        this.destroy();
    }

    @Override
    public boolean isPlayer(){return false;}

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
        updateSpeed();
        updateShooting();
    }

    private void updateSpeed(){
        x += speedX;
        y += speedY;
    }

    private void updateShooting(){
        framesTillShooting--;
        if (framesTillShooting <= 0){
            shoot();
            framesTillShooting = shootingInterval;
        }
    }

    private void onDeath(){
        this.destroy();
        gameEngine.getScoreHolder().addScore(GameConstants.FIGHTER_SCORE);
        dropPowerUp();
    }

    private void dropPowerUp(){
        int random = new Random().nextInt(10);
        switch (random){
            case 0:
                gameEngine.getPowerupFactory().createPowerup(PowerupTypes.Fireup,x,y);
                break;
            case 1:
                gameEngine.getPowerupFactory().createPowerup(PowerupTypes.Lifeup,x,y);
                break;
            case 2:
                gameEngine.getPowerupFactory().createPowerup(PowerupTypes.Multishoot,x,y);
                break;
        }
    }
}