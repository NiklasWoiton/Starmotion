package com.starproductions.starmotion.starmotion.GameObjects;

/**
 * Created by Admin on 19.09.2017.
 */

interface EnemyShip {
    void calcShootingInterval();
    void onDeath();
    void updateSpeed();
    void updateShooting();
}
