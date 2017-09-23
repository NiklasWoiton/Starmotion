package com.starproductions.starmotion.starmotion.Powerups;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.GameObjects.Actor;
import com.starproductions.starmotion.starmotion.GameObjects.Collidable;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffects;

/**
 * Created by Shoggomo on 28.07.2017.
 */

abstract class PowerupObject extends Actor {

    protected PlayerShip player;
    private double speedX = 0;
    private double speedY = 1;

    PowerupObject(GameEngine gameEngine, PlayerShip player) {
        super(gameEngine);
        this.player = player;
    }


    @Override
    public void onCollide(Collidable obstacle) {
        gameEngine.playSound(SoundEffects.Powerup);
        if (obstacle instanceof PlayerShip) {
            applyEffect();
            destroy();
        }
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
    public void draw(Canvas c, double extrapolation) {
        c.drawBitmap(asset, (float) (x + speedX * extrapolation), (float) (y + speedY * extrapolation), null);
    }

    @Override
    public void update() {
        x += speedX;
        y += speedY;
    }

    protected abstract void applyEffect();
}
