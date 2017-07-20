package com.starproductions.starmotion.starmotion.ScoreSystem;

import android.provider.BaseColumns;

/**
 * A contract class that represents the Database
 */
final class StarmotionContract{

    private StarmotionContract(){}

    static class ScoreEntry implements BaseColumns {
        static final String TABLE_NAME = "scores";
        static final String COLUMN_NAME_PLAYERNAME = "playername";
        static final String COLUMN_NAME_SCORE = "score";
    }
}
