package com.starproductions.starmotion.starmotion;

import android.graphics.Point;
import android.view.Display;

/**
 * Created by jakob on 20.07.2017.
 */

public final class GameConstants {

    //Performance values
    public static final double MULTIPLICATOR = 1;
    private static final double MS_PER_UPDATE_BASE = 4;
    public static final double MS_PER_UPDATE = MS_PER_UPDATE_BASE * MULTIPLICATOR;

    //General graphical values
    public static Point SIZE = new Point();
    public static final double DESPAWN_BORDER_Factor = 0.3;
    public static final double START_ENEMY_SHIPS_Y_Factor = -0.2;

    //Values for Lasers
    public static final double LASER_SCALE_FACTOR = 0.017;

    //Values for EnemyShips
    public static final double ENEMY_SHIP_SCALE_FACTOR = 0.1;

    //Values for Player
    public static final int MS_BETWEEN_PLAYER_SHOOTS = 1000;
    public static final double PLAYER_SHIP_SCALE_FACTOR = 0.1;
    public static final int PLAYER_START_LIFE = 5;

    //Values for ObjectSpawner
    public static final int MS_BETWEEN_SHIPS_MAX = 2000;
    public static final int MS_BETWEEN_SHIPS_MIN = 500;
    public static final int SCORE_WITH_MAX_SHIPS = 10000;
    public static final int MS_BETWEEN_ENEMY_SHOTS_MIN = 200;
    public static final int MS_BETWEEN_ENEMY_SHOTS_MAX = 1500;
    public static final double GAP_BETWEEN_ENEMY_SHIPS_X_FACTOR = 0.2;

    //Values for Collisions
    public static final int GRID_CONSTANT = 10;
}
