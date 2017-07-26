package com.starproductions.starmotion.starmotion;

import android.graphics.Point;
import android.view.Display;

/**
 * Created by jakob on 20.07.2017.
 */

public final class GameConstants {
    public static final double MULTIPLICATOR = 1;
    private static final double MS_PER_UPDATE_BASE = 4;
    public static final double MS_PER_UPDATE = MS_PER_UPDATE_BASE * MULTIPLICATOR;

    public static Point SIZE;

    public static final double DESPAWN_BORDER_Factor = 0.3;

    public static final double MS_BETWEEN_ENEMY_SHIPS = 1000;
    public static final double START_ENEMY_SHIPS_Y_Factor = -0.2;
    public static final int MS_BETWEEN_PLAYER_SHOOTS = 1000;
}
