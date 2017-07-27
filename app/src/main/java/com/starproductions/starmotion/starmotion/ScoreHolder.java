package com.starproductions.starmotion.starmotion;

/**
 * Created by Shoggomo on 27.07.2017.
 */

public class ScoreHolder {
    private int score = 0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score){
        this.score += score;
    }
}
