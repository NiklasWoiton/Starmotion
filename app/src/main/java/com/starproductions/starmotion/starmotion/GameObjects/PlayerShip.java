package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Canvas;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.PlayerInput.InputManager;
import com.starproductions.starmotion.starmotion.PlayerInput.Notification;
import com.starproductions.starmotion.starmotion.R;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffects;

import java.util.Observable;
import java.util.Observer;

public class PlayerShip extends Actor implements Observer, SpaceShip {

    private long lastShot = 0;
    private double fireRate = GameConstants.PLAYER_FIRERATE_MIN;

    private int shootMultiplikator = 1;
    private int health = GameConstants.PLAYER_START_HEALTH;

    public PlayerShip(GameEngine gameEngine, InputManager inputManager) {
        super(gameEngine, R.drawable.spaceship_player, GameConstants.PLAYER_SHIP_SCALE_FACTOR);
        this.x = GameConstants.SIZE.x;
        this.y = GameConstants.SIZE.y * 0.9;

        inputManager.setSpeed(GameConstants.PLAYER_SPEED_X);
        inputManager.setMaxX(GameConstants.SIZE.x - asset.getWidth());
        inputManager.addObserver(this);
        inputManager.start();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        if (fireRate <= GameConstants.PLAYER_FIRERATE_MAX && fireRate >= GameConstants.PLAYER_FIRERATE_MIN) {
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
    public void shoot() {
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
                default:
                    frontLaser();
                    twinLasers();
                    sideLasers();
                    break;
            }
            if (health > 0) gameEngine.playSound(SoundEffects.LaserShoot);
        }
    }

    @Override
    public void onCollide(Collidable obstacle) {
        if (obstacle instanceof Laser || obstacle instanceof EnemyShip) {
            onDamage();
        }
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public void move() {

    }

    /*
     *Unique draw method, as PlayerShip has no fixed speed for extrapolation
     */
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
        if (shootMultiplikator > 1) {
            shootMultiplikator--;
        }
        setFireRate(fireRate - GameConstants.FIREUP_FACTOR);
        //health--;Todo, Bugtest
        gameEngine.playSound(SoundEffects.PlayerHit);
        if (health <= 0) {
            this.destroy();
            gameEngine.playSound(SoundEffects.Explosion);
            gameEngine.gameOver();
        }
    }

    private void frontLaser() {
        playerLaser(0.5, 0, 0, -2);
    }

    private void twinLasers() {
        playerLaser(0.7, 0.1, 0, -2);
        playerLaser(0.3, 0.1, 0, -2);
    }

    private void sideLasers() {
        playerLaser(0.85, 0.2, 0.2, -2);
        playerLaser(0.15, 0.2, -0.2, -2);
    }

    private void playerLaser(double horizontalOffset, double verticalOffset, double horizontalSpeed, double verticalSpeed) {
        new Laser(gameEngine, x + asset.getWidth() * horizontalOffset, y + asset.getHeight() * verticalOffset, horizontalSpeed, verticalSpeed, R.drawable.laser_blue, GameConstants.LASER_SCALE_FACTOR_STRAIGHT, true);
    }
}
