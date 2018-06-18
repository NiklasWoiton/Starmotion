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

public class Fighter extends SpaceShip implements EnemyShip {

    private double speedX = GameConstants.FIGHTER_SPEED_X;
    private double speedY = GameConstants.FIGHTER_SPEED_Y;

    private int framesTillShooting;
    private int framesTillTurn = GameConstants.FIGHTER_FRAMES_TILL_TURN;

    /**
     * @param gameEngine: the GameEngine
     * @param x:          the initial x position of the ship
     * @param y:          the initial y position of the Ship
     */
    public Fighter(GameEngine gameEngine, double x, double y) {
        super(gameEngine);
        this.x = x;
        this.y = y;
        calcShootingInterval();
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources(), R.drawable.spaceship_fighter);
        int newWidth = (int) (GameConstants.SIZE.x * GameConstants.FIGHTER_SCALE_FACTOR);
        int newHeight = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeight, true);
    }

    @Override
    void shoot() {
        new Laser(gameEngine, x + asset.getWidth() / 2, y + asset.getHeight(), 0, GameConstants.LASER_SPEED_FIGHTER);
        gameEngine.playSound(SoundEffects.LaserShoot);
        calcShootingInterval();
    }

    @Override
    public void onCollide(Collidable obstacle) {
        onDeath();
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
        if (x <= 0) speedX = GameConstants.FIGHTER_SPEED_X;
        else if (x >= GameConstants.SIZE.x - getHitBox().width())
            speedX = -GameConstants.FIGHTER_SPEED_X;
        else if (framesTillTurn <= 0) {
            speedX *= -1;
            framesTillTurn = GameConstants.FIGHTER_FRAMES_TILL_TURN;
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

    public void calcShootingInterval() {
        framesTillShooting = (int) (GameConstants.MS_BETWEEN_FIGHTER_SHOTS * (1 + 2 * random()));

    }

    public void updateShooting() {
        framesTillShooting--;
        if (framesTillShooting <= 0) {
            shoot();
            calcShootingInterval();
        }
    }

    public void onDeath() {
        this.destroy();
        gameEngine.getScoreHolder().addScore(GameConstants.FIGHTER_SCORE);
        gameEngine.playSound(SoundEffects.Explosion);
        gameEngine.getPowerupFactory().createPowerup(x, y, GameConstants.FIGHTER_SCORE);
    }
}
