package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Rect;

/**
 * Created by Shoggomo on 11.07.2017.
 */

public interface Collidable {
    void onCollide(Collidable obstacle);
    Rect getHitBox();

    boolean isPlayer();
}

