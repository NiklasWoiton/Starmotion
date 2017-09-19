package com.starproductions.starmotion.starmotion.GameObjects;

/**
 * Created by Admin on 19.09.2017.
 */

interface EnemyShip {//Todo, look up if this is actually a good Idea, because of this a lot of private functions are public(Nik)
    void calcShootingInterval();
    void onDeath();
    void dropPowerUp();
    void updateSpeed();
    void updateShooting();
}
