package com.starproductions.starmotion.starmotion;

import com.starproductions.starmotion.starmotion.GameObjects.Destroyer;
import com.starproductions.starmotion.starmotion.GameObjects.Disk;
import com.starproductions.starmotion.starmotion.GameObjects.Fighter;
import com.starproductions.starmotion.starmotion.GameObjects.Formation;

import static java.lang.Math.log10;
import static java.lang.Math.random;

class ObjectSpawner {

    private GameEngine gameEngine;
    private ScoreHolder scoreHolder;
    private int millisToNextShip = 0;
    private int lastShipXPos = -1000;

    private double spawnChanceFighterSquadron;
    private double spawnChanceDestroyer;

    ObjectSpawner(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        scoreHolder = gameEngine.getScoreHolder();
    }

    public void update() {
        millisToNextShip -= GameConstants.MS_PER_UPDATE;
        if (millisToNextShip <= 0) {
            spawnShip();
            calcMillisToNextShip();
        }
    }

    private void spawnShip() {
        updateSpawnChance();
        double random = Math.random();
        if (random < spawnChanceFighterSquadron) {
            spawnFighterSquadron();
        } else {
            if (random < spawnChanceDestroyer + spawnChanceFighterSquadron) {
                new Destroyer(gameEngine, calcShipXPos(), GameConstants.START_ENEMY_SHIPS_Y_FACTOR * GameConstants.SIZE.y);
            } else
                new Disk(gameEngine, calcShipXPos(), GameConstants.START_ENEMY_SHIPS_Y_FACTOR * GameConstants.SIZE.y);
        }
    }

    private void spawnFighterSquadron() {
        Fighter lead = new Fighter(gameEngine, calcShipXPos(), GameConstants.START_ENEMY_SHIPS_Y_FACTOR * GameConstants.SIZE.y);
        Fighter right = new Fighter(gameEngine, lead.getX() + lead.getHitBox().width(), lead.getY() - lead.getHitBox().height());
        Fighter left = new Fighter(gameEngine, lead.getX() - lead.getHitBox().width(), lead.getY() - lead.getHitBox().height());
        Fighter[] squad = {left, lead, right};
        new Formation(gameEngine, squad);
    }

    private void updateSpawnChance() {
        spawnChanceFighterSquadron = GameConstants.SPAWN_CHANCE_FIGHTER_SQUADRON_START / calcSpawnTypeLogMod();
        spawnChanceDestroyer = GameConstants.SPAWN_CHANCE_DESTROYER_START +
                (GameConstants.SPAWN_CHANCE_FIGHTER_SQUADRON_START - spawnChanceFighterSquadron) * GameConstants.SPAWN_CHANCE_DESTROYER_INCREASE_MOD;
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

    private void calcMillisToNextShip() {
        millisToNextShip = (int) (GameConstants.MS_BETWEEN_SHIPS_START / calcSpawnLogMod());
    }

    private double calcSpawnLogMod() {
        return 1 + log10(1 + scoreHolder.getScore()) * GameConstants.SPAWN_LOG_MOD;
    }

    private double calcSpawnTypeLogMod() {
        return 1 + log10(1 + scoreHolder.getScore()) * GameConstants.SPAWN_TYPE_LOG_MOD;
    }
}
