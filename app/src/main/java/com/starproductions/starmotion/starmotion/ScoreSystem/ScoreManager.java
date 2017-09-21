package com.starproductions.starmotion.starmotion.ScoreSystem;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Manages Scores
 * Needs to be started and stopped (normally in onResume() and onPause() etc.)
 */
public class ScoreManager {
    // Constants
    private static final int MAX_SCORES = 5;

    private final ScoreDbHelper db;

    /**
     *
     * @param context The calling activity
     */
    public ScoreManager(Context context){
        db = new ScoreDbHelper(context);
    }

    /**
     * Starts the Database.
     */
    public void start(){
        db.openDatabase();
    }

    /**
     * Stops the Database to save power.
     */
    public void stop(){
        db.closeDatabase();
    }

    /**
     * Gets all scores as score objects
     * @return List of scores
     */
    public ArrayList<Score> getAllScores(){
        Cursor cursor = db.readAllScores();
        ArrayList<Score> scoreList = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(StarmotionContract.ScoreEntry._ID));
            int score = cursor.getInt(cursor.getColumnIndexOrThrow(StarmotionContract.ScoreEntry.COLUMN_NAME_SCORE));
            String playername = cursor.getString(cursor.getColumnIndexOrThrow(StarmotionContract.ScoreEntry.COLUMN_NAME_PLAYERNAME));
            Score newScoreEntry = new Score(id, score, playername);
            scoreList.add(newScoreEntry);
        }
        cursor.close();
        return scoreList;
    }

    private Score getMinScore(ArrayList<Score> scoreList){
        Score min = null;
        for(Score score : scoreList)
            if (min == null)
                min = score;
            else
                min = min.getScore() < score.getScore() ? min : score;
        return min;
    }

    /**
     * Returns if the given score is a highscore
     * @param score The score to check
     * @return If it's a new highscore
     */
    public boolean isHighscore(int score){
        return (getMinScore(getAllScores()) != null) && (getMinScore(getAllScores()).getScore() < score);
    }

    /**
     * Adds a new score for a player
     * @param playername Name of the player
     * @param score Score to add
     */
    public void addScore(String playername, int score){
        db.insertScore(playername, score);
        ArrayList<Score> allScores = getAllScores();
        while(allScores.size() > MAX_SCORES) {
            Score min = getMinScore(allScores);
            db.deleteScores(min.getId());
        }
    }

}
