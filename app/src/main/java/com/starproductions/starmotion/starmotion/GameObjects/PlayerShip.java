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

public class PlayerShip extends SpaceShip implements Observer {

    private long lastShot = 0;
    private double fireRate = 1;

    private int shootMultiplikator = 1;
    private int life = GameConstants.PLAYER_START_LIFE;

    public PlayerShip(GameEngine gameEngine, InputManager inputManager) {
        super(gameEngine);
        this.x = GameConstants.SIZE.x;
        this.y = GameConstants.SIZE.y * 0.9;

        inputManager.setSpeed((float) GameConstants.PLAYER_SPEED_X);
        inputManager.setMaxX(GameConstants.SIZE.x - asset.getWidth());
        inputManager.addObserver(this);
        inputManager.start();
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        if (fireRate <= 3 && fireRate >= 1) {
            this.fireRate = fireRate;
        }
    }

    public int getShootMultiplikator() {
        return shootMultiplikator;
    }

    public void setShootMultiplikator(int shootMultiplikator) {
        this.shootMultiplikator = shootMultiplikator;
    }

    @Override
    protected void setAsset() {
        Bitmap srcAsset = BitmapFactory.decodeResource(gameEngine.getResources(), R.drawable.spaceship_player);
        int newWidth = (int) (GameConstants.SIZE.x * GameConstants.PLAYER_SHIP_SCALE_FACTOR);
        int newHeigth = (int) ((double) srcAsset.getHeight() * ((double) newWidth / (double) srcAsset.getWidth()));
        asset = Bitmap.createScaledBitmap(srcAsset, newWidth, newHeigth, true);
    }

    @Override
    void shoot() {
        if (System.currentTimeMillis() - lastShot >= GameConstants.MS_BETWEEN_PLAYER_SHOOTS / fireRate) {
            lastShot = System.currentTimeMillis();
            switch (shootMultiplikator) {
                case 1:
                    frontLaser();
                    break;
                case 2:
                    twinLasers();
                    break;
                case 3:
                    frontLaser();
                    twinLasers();
                    break;
                case 4:
                    twinLasers();
                    sideLasers();
                    break;
                case 5:
                    frontLaser();
                    twinLasers();
                    sideLasers();
                    break;
                case 6:
                    twinLasers();
                    sideLasers();
                    farSideLasers();
                    break;
                default:
                    frontLaser();
                    twinLasers();
                    sideLasers();
                    farSideLasers();
                    break;
            }
            if (life > 0) gameEngine.playSound(SoundEffects.LaserShoot);
        }
    }

    @Override
    public void onCollide(Collidable obstacle) {
        if (obstacle instanceof Laser || obstacle instanceof Fighter) {
            onDamage();
        }
    }

    @Override
    public boolean isPlayer() {
        return true;
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
        c.drawBitmap(asset, (float) x, (float) y, null);
    }

    @Override
    public void update() {
    }

    // Input update
    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof Notification) {
            Notification n = (Notification) o;
            x = n.getX();
            if (n.isTouching()) shoot();
        }
    }

    private void onDamage() {
        shootMultiplikator = (int) Math.ceil(shootMultiplikator / 2.0);
        setFireRate(fireRate - GameConstants.FIREUP_FACTOR);
        life--;
        gameEngine.playSound(SoundEffects.PlayerHit);
        if (life <= 0) {
            this.destroy();
            gameEngine.playSound(SoundEffects.Explosion);
            gameEngine.gameOver();
        }
    }

    private void frontLaser(){
        new PlayerLaser(gameEngine, x + asset.getWidth()/2, y, 0, -2);
    }

    private void twinLasers(){
        new PlayerLaser(gameEngine, x + asset.getWidth()*0.7, y + asset.getHeight() * 0.1, 0, -2);
        new PlayerLaser(gameEngine, x + asset.getWidth()*0.3, y + asset.getHeight() * 0.1, 0, -2);
    }

    private void sideLasers(){
        new PlayerLaser(gameEngine, x + asset.getWidth()*0.85, y + asset.getHeight() * 0.2, 0.2, -2);
        new PlayerLaser(gameEngine, x + asset.getWidth()*0.15, y + asset.getHeight() * 0.2, -0.2, -2);
    }

    private void farSideLasers(){
        new PlayerLaser(gameEngine, x + asset.getWidth(), y + asset.getHeight() * 0.3, 0.4, -2);
        new PlayerLaser(gameEngine, x, y + asset.getHeight() * 0.3, -0.4, -2);
    }
}
