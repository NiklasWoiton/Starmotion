package com.starproductions.starmotion.starmotion.GameObjects;

import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffects;

import static java.lang.Math.random;

abstract class EnemyShip extends Actor implements SpaceShip {

    int health;
    int score;

    private int framesTillShooting;
    int framesTillTurn;
    int shootingCooldown;

    EnemyShip(GameEngine gameEngine, double x, double y, int drawable, double drawableScale) {
        super(gameEngine, drawable, drawableScale);
        this.x = x;
        this.y = y;
    }

    public void onCollide(Collidable obstacle) {
        health--;
        if (health <= 0) {
            onDeath();
        }
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public void update() {
        move();
        updateShooting();
    }

    void calcShootingInterval() {
        framesTillShooting = (int) (shootingCooldown * (1 + 2 * random()));

    }

    private void updateShooting() {
        framesTillShooting--;
        if (framesTillShooting <= 0) {
            shoot();
            calcShootingInterval();
        }
    }

    private void onDeath() {
        this.destroy();
        gameEngine.getScoreHolder().addScore(score);
        gameEngine.playSound(SoundEffects.Explosion);
        gameEngine.getPowerupFactory().createPowerup(x, y, score);
    }
}