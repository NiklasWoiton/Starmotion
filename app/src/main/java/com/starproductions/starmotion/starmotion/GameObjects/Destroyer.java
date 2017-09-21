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

import static java.lang.Math.random;

/**
 * Created by Admin on 18.09.2017.
 */

public class Destroyer extends SpaceShip implements EnemyShip {

    private double speedX = GameConstants.DESTROYER_SPEED_X;
    private double speedY = GameConstants.DESTROYER_SPEED_Y;
    private int health = GameConstants.DESTROYER_HEALTH;

    private int framesTillShooting;
    private int framesTillTurn = GameConstants.DESTROYER_FRAMES_TILL_TURN;

    /**
     * @param gameEngine: the GameEngine
     * @param x: the initial x position of the ship
     * @param y: the initial y position of the Ship
     */
    public Destroyer(GameEngine gameEngine, double x, double y){
        super(gameEngine);
        this.x = x;
        this.y = y;
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources() , R.drawable.spaceship_destroyer);
        int newWidth = (int) (GameConstants.SIZE.x * GameConstants.DESTROYER_SCALE_FACTOR);
        int newHeight = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeight, true);
    }

    @Override
    void shoot() {
        new SlowLaser(gameEngine, x + asset.getWidth() * 0.25, y + asset.getHeight(), 0, GameConstants.LASER_SPEED_DESTROYER);
        new SlowLaser(gameEngine, x + asset.getWidth() * 0.75, y + asset.getHeight(), 0, GameConstants.LASER_SPEED_DESTROYER);
        gameEngine.playSound(SoundEffects.LaserShoot);
    }

    @Override
    public void onCollide(Collidable obstacle) {
        health--;
        if(health <= 0){
            onDeath();
        }
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

    public void updateSpeed(){
        x += speedX;
        y += speedY;
        framesTillTurn--;
        if(x <= 0) speedX = GameConstants.DESTROYER_SPEED_X;
        else if(x >= GameConstants.SIZE.x - getHitBox().width()) speedX = -GameConstants.DESTROYER_SPEED_X;
        else if(framesTillTurn <= 0){
            speedX *= -1;
            framesTillTurn = GameConstants.DESTROYER_FRAMES_TILL_TURN;
        }
    }

    public void updateShooting(){
        framesTillShooting--;

        if (framesTillShooting <= 0){
            shoot();
            calcShootingInterval();
        }
    }

    public void calcShootingInterval(){
        framesTillShooting = GameConstants.MS_BETWEEN_DESTROYER_SHOTS_MAX - (int) (random() *
                (GameConstants.MS_BETWEEN_DESTROYER_SHOTS_MAX - GameConstants.MS_BETWEEN_DESTROYER_SHOTS_MIN));
    }

    public void onDeath(){
        this.destroy();
        gameEngine.getScoreHolder().addScore(GameConstants.DESTROYER_SCORE);
        gameEngine.playSound(SoundEffects.Explosion);
        gameEngine.getPowerupFactory().createPowerup(x, y, GameConstants.DESTROYER_POWERUP_DROPCHANCE);
    }
}
