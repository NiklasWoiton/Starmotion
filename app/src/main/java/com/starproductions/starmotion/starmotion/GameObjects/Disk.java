package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.R;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffects;

import static java.lang.Math.random;

/**
 * Created by Admin on 18.09.2017.
 */

public class Disk extends SpaceShip implements EnemyShip {

    private double speedX = GameConstants.DISK_SPEED_X;
    private double speedY = GameConstants.DISK_SPEED_Y;
    private int health = GameConstants.DISK_HEALTH;

    private int framesTillTurn = GameConstants.DISK_FRAMES_TILL_TURN;
    private int framesTillShooting;
    private boolean nextShootLeft = true;

    /**
     * @param gameEngine: the GameEngine
     * @param x:          the initial x position of the ship
     * @param y:          the initial y position of the Ship
     */
    public Disk(GameEngine gameEngine, double x, double y) {
        super(gameEngine);
        this.x = x;
        this.y = y;
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources(), R.drawable.spaceship_disk);
        int newWidth = (int) (GameConstants.SIZE.x * GameConstants.DISK_SCALE_FACTOR);
        int newHeight = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeight, true);
    }

    @Override
    void shoot() {
        if (nextShootLeft)
            new DiskLaser(gameEngine, x + asset.getWidth() * 0.25, y + asset.getHeight(), -GameConstants.LASER_SPEED_DISK / 2, GameConstants.LASER_SPEED_DISK);
        else
            new DiskLaser(gameEngine, x + asset.getWidth() * 0.75, y + asset.getHeight(), GameConstants.LASER_SPEED_DISK / 2, GameConstants.LASER_SPEED_DISK);
        nextShootLeft = !nextShootLeft;
        gameEngine.playSound(SoundEffects.LaserShoot);
    }

    @Override
    public void onCollide(Collidable obstacle) {
        health--;
        if (health <= 0) onDeath();
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public Rect getHitBox() {
        return new Rect((int) x, (int) y, (int) (x + asset.getWidth()), (int) (y + asset.getHeight()));
    }

    @Override
    public void move() {
        x += speedX;
        y += speedY;
        framesTillTurn--;
        if (x <= 0) speedX = GameConstants.DISK_SPEED_X;
        else if (x >= GameConstants.SIZE.x - getHitBox().width())
            speedX = -GameConstants.DISK_SPEED_X;
        else if (framesTillTurn <= 0) {
            speedX *= -1;
            framesTillTurn = GameConstants.DISK_FRAMES_TILL_TURN;
        }
    }

    @Override
    public void draw(Canvas c, double extrapolation) {
        c.drawBitmap(asset, (float) (x + speedX * extrapolation), (float) (y + speedY * extrapolation), null);
    }

    @Override
    public void update() {
        move();
        updateShooting();
    }

    public void updateShooting() {
        framesTillShooting--;
        if (framesTillShooting <= 0) {
            shoot();
            calcShootingInterval();
        }
    }

    public void calcShootingInterval() {
        framesTillShooting = GameConstants.MS_BETWEEN_DISK_SHOTS_MAX - (int) (random() *
                (GameConstants.MS_BETWEEN_DISK_SHOTS_MAX - GameConstants.MS_BETWEEN_DISK_SHOTS_MIN));
    }

    public void onDeath() {
        this.destroy();
        gameEngine.getScoreHolder().addScore(GameConstants.DISK_SCORE);
        gameEngine.playSound(SoundEffects.Explosion);
        gameEngine.getPowerupFactory().createPowerup(x, y, GameConstants.DISK_POWERUP_DROPCHANCE);
    }
}
