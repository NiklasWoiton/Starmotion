package com.starproductions.starmotion.starmotion.GameObjects;

import com.starproductions.starmotion.starmotion.GameEngine;

public abstract class SpaceShip extends Actor {
    SpaceShip(GameEngine gameEngine) {
        super(gameEngine);
    }

    abstract void shoot();
}
