package com.starproductions.starmotion.starmotion;

/**
 * Created by Shoggomo on 11.07.2017.
 */

public abstract class Actor extends GameObject implements Collidable{
    public Actor(GameEngine gameEngine) {
        super(gameEngine);
    }

    abstract void move();

}
