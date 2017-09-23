package com.starproductions.starmotion.starmotion.ScoreSystem;

/**
 * Stores score data like the playername and the score
 */
public class Score {
    private final int id, score;
    private final String playername;

    public Score(int id, int score, String playername) {
        this.id = id;
        this.score = score;
        this.playername = playername;
    }

    public int getScore() {
        return score;
    }

    public String getPlayername() {
        return playername;
    }

    public int getId() {
        return id;
    }
}
