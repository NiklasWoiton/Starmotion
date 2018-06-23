package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Rect;

public interface Collidable {
    void onCollide(Collidable obstacle);

    Rect getHitBox();

    boolean isPlayer();

    void move();
}

