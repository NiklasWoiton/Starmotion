package com.starproductions.starmotion.starmotion;

import com.starproductions.starmotion.starmotion.GameObjects.Destroyer;
import com.starproductions.starmotion.starmotion.GameObjects.Disk;
import com.starproductions.starmotion.starmotion.GameObjects.Fighter;

import static java.lang.Math.random;

class ObjectSpawner {

    private GameEngine gameEngine;
    private ScoreHolder scoreHolder;
    private int millisToNextShip = 0;
    private int lastShipXPos = -1000;

    ObjectSpawner(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        scoreHolder = gameEngine.getScoreHolder();
    }

    public void update() {
        millisToNextShip -= GameConstants.MS_PER_UPDATE;
        if (millisToNextShip <= 0) {
            spawnShip();
            millisToNextShip = (scoreHolder.getScore() >= GameConstants.SCORE_WITH_MAX_SHIPS) ? GameConstants.MS_BETWEEN_SHIPS_MIN : calcMillisToNextShip();
        }
    }

    private int calcMillisToNextShip() {
        return GameConstants.MS_BETWEEN_SHIPS_MAX - (int) ((GameConstants.MS_BETWEEN_SHIPS_MAX - GameConstants.MS_BETWEEN_SHIPS_MIN) *
                (((double) scoreHolder.getScore() + 1) / (double) GameConstants.SCORE_WITH_MAX_SHIPS));
    }

    private void spawnShip() {
        double random = Math.random();
        if (random < GameConstants.SPAWN_CHANCE_FIGHTER_SQUADRON) {
            spawnFighterSquadron();
        } else {
            random -= GameConstants.SPAWN_CHANCE_FIGHTER_SQUADRON;
            if (random < GameConstants.SPAWN_CHANCE_DESTROYER) {
                new Destroyer(gameEngine, calcShipXPos(), GameConstants.START_ENEMY_SHIPS_Y_FACTOR * GameConstants.SIZE.y);
            }else{
                random -= GameConstants.SPAWN_CHANCE_DESTROYER;
                if (random < GameConstants.SPAWN_CHANCE_DISK){
                    new Disk(gameEngine, calcShipXPos(), GameConstants.START_ENEMY_SHIPS_Y_FACTOR * GameConstants.SIZE.y);
                }
            }
        }
    }

    private void spawnFighterSquadron() {
        Fighter lead = new Fighter(gameEngine, calcShipXPos(), GameConstants.START_ENEMY_SHIPS_Y_FACTOR * GameConstants.SIZE.y);
        new Fighter(gameEngine, lead.getX() + lead.getHitBox().width(), lead.getY() - lead.getHitBox().height());
        new Fighter(gameEngine, lead.getX() - lead.getHitBox().width(), lead.getY() - lead.getHitBox().height());
    }

    private int calcShipXPos() {
        int posX;
        do {
            posX = (int) (random() * GameConstants.SIZE.x);
        }
        while (Math.abs(posX - lastShipXPos) <= (GameConstants.GAP_BETWEEN_ENEMY_SHIPS_X_FACTOR * GameConstants.SIZE.x));
        lastShipXPos = posX;
        return posX;
    }

}
