package com.starproductions.starmotion.starmotion.GameObjects;

import com.starproductions.starmotion.starmotion.GameEngine;

/**
 * Created by Shoggomo on 11.07.2017.
 */

public abstract class SpaceShip extends Actor {
    SpaceShip(GameEngine gameEngine) {
        super(gameEngine);
    }

    abstract void shoot();
}
