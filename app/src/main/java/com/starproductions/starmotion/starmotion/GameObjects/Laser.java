package com.starproductions.starmotion.starmotion.GameObjects;

import com.starproductions.starmotion.starmotion.GameEngine;

class Laser extends Actor {

    protected boolean isPlayer;

    Laser(GameEngine gameEngine, double posX, double posY, double speedX, double speedY, int drawable, double drawableScale, boolean isPlayer) {
        super(gameEngine, drawable, drawableScale);

        this.x = posX - asset.getWidth() / 2;
        if (speedY > 0) {
            this.y = posY;
        } else {
            this.y = posY - asset.getHeight();
        }
        this.speedX = speedX;
        this.speedY = speedY;
        this.isPlayer = isPlayer;
    }

    @Override
    public void onCollide(Collidable obstacle) {
        this.destroy();
    }

    @Override
    public boolean isPlayer() {
        return isPlayer;
    }


    @Override
    public void move() {
        x += speedX;
        y += speedY;
    }

    @Override
    public void update() {
        move();
    }
}