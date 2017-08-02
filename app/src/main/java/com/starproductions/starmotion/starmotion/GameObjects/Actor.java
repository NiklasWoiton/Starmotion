package com.starproductions.starmotion.starmotion.GameObjects;

import com.starproductions.starmotion.starmotion.GameEngine;

/**
 * Created by Shoggomo on 11.07.2017.
 */

public abstract class Actor extends GameObject implements Collidable{
    public Actor(GameEngine gameEngine) {
        super(gameEngine);
    }

    abstract public void move();

}
