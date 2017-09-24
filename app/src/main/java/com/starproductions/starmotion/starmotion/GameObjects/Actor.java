package com.starproductions.starmotion.starmotion.GameObjects;

import com.starproductions.starmotion.starmotion.GameEngine;

public abstract class Actor extends GameObject implements Collidable {
    public Actor(GameEngine gameEngine) {
        super(gameEngine);
    }

    abstract public void move();

}
