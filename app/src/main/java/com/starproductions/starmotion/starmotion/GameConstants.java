package com.starproductions.starmotion.starmotion;

import android.graphics.Color;
import android.graphics.Point;

import com.starproductions.starmotion.starmotion.Powerups.PowerupTypes;

/**
 * Created by jakob on 20.07.2017.
 */

public final class GameConstants {

    //Performance values
    public static final double MULTIPLICATOR = 1;
    public static final double MS_PER_UPDATE_BASE = 4;
    public static final double MS_PER_UPDATE = MS_PER_UPDATE_BASE * MULTIPLICATOR;

    //General graphical values
    public static Point SIZE = new Point();
    public static final double DESPAWN_BORDER_FACTOR = 0.3;
    public static final double START_ENEMY_SHIPS_Y_FACTOR = -0.2;

    //Values for Lasers
    public static final double LASER_SCALE_FACTOR_STRAIGHT = 0.0125;
    public static final double LASER_SCALE_FACTOR_ROUND = 0.025;
    public static final double LASER_SPEED_FIGHTER = 1.5;
    public static final double LASER_SPEED_DESTROYER = 1;

    //Values for Enemy Ships
    public static final int MS_BETWEEN_ENEMY_SHOTS_MIN = 1000;
    public static final int MS_BETWEEN_ENEMY_SHOTS_MAX = 3000;

    //Values for Fighter
    public static final double FIGHTER_SCALE_FACTOR = 0.06;
    public static final int FIGHTER_HEALTH = 1;
    public static final int FIGHTER_SCORE = 100;
    public static final double FIGHTER_SPEED_Y = 0.6;
    public static final double FIGHTER_SPEED_X = 0.3;
    public static final int FIGHTER_FRAMES_TILL_TURN = 500;
    public static final double FIGHTER_POWERUP_DROPCHANCE = 0.125;
    public static final double FIGHTER_INTERVAL_MOD = 1.5;
    public static final int MS_BETWEEN_FIGHTER_SHOTS_MIN = (int) (MS_BETWEEN_ENEMY_SHOTS_MIN / FIGHTER_INTERVAL_MOD);
    public static final int MS_BETWEEN_FIGHTER_SHOTS_MAX = (int) (MS_BETWEEN_ENEMY_SHOTS_MAX / FIGHTER_INTERVAL_MOD);

    //Values for Destroyer
    public static final double DESTROYER_SCALE_FACTOR = 0.1;
    public static final int DESTROYER_HEALTH = 3;
    public static final int DESTROYER_SCORE = 250;
    public static final double DESTROYER_SPEED_Y = 0.4;
    public static final double DESTROYER_SPEED_X = 0.2;
    public static final int DESTROYER_FRAMES_TILL_TURN = 500;
    public static final double DESTROYER_POWERUP_DROPCHANCE = 0.25;
    public static final double DESTROYER_INTERVAL_MOD = 1;
    public static final int MS_BETWEEN_DESTROYER_SHOTS_MIN = (int) (MS_BETWEEN_ENEMY_SHOTS_MIN / DESTROYER_INTERVAL_MOD);
    public static final int MS_BETWEEN_DESTROYER_SHOTS_MAX = (int) (MS_BETWEEN_ENEMY_SHOTS_MAX / DESTROYER_INTERVAL_MOD);

    //Values for PowerUps
    public static final int POWERUP_TYPES = PowerupTypes.values().length;
    public static final double POWERUP_SCALE_FACTOR = 0.04;
    public static final double FIREUP_FACTOR = 0.2;

    //Values for Player
    public static final int MS_BETWEEN_PLAYER_SHOOTS = 1000;
    public static final double PLAYER_SHIP_SCALE_FACTOR = 0.1;
    public static final int PLAYER_START_LIFE = 5;
    public static final float PLAYER_SPEED_X = 700;

    //Values for ObjectSpawner
    public static final int MS_BETWEEN_SHIPS_MAX = 2000;
    public static final int MS_BETWEEN_SHIPS_MIN = 1000;
    public static final int SCORE_WITH_MAX_SHIPS = 10000;
    public static final double GAP_BETWEEN_ENEMY_SHIPS_X_FACTOR = 0.2;

    //Values for Collisions
    public static final int GRID_CONSTANT = 10;

    //Values for GameOverText
    public static final String GAME_OVER_TEXT = "Game Over";
    public static final int GAME_OVER_COLOR = Color.RED;
    public static final double GAME_OVER_SIZE_Factor = 0.15;
    public static final double GAME_OVER_SIZE_X_FACTOR = 0;
    public static final double GAME_OVER_SIZE_Y_FACTOR = 0;
    public static final int GAME_OVER_SHOW_TIME_MS = 3000;

    //Background values
    public static final int STAR_COUNT = 200;


}
