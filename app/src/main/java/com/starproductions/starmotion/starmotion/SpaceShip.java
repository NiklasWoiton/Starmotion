package com.starproductions.starmotion.starmotion;

/**
 * Created by Shoggomo on 11.07.2017.
 */

public abstract class SpaceShip extends Actor {
    public SpaceShip(GameEngine gameEngine) {
        super(gameEngine);
    }

    abstract void shoot();
}
