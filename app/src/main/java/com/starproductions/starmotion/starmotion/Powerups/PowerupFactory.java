package com.starproductions.starmotion.starmotion.Powerups;

import android.util.Log;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.GameObjects.PlayerShip;

import java.util.ArrayList;

public class PowerupFactory {
    private GameEngine gameEngine;
    private PlayerShip player;
    private ArrayList<PowerupTypes> powerupArray = new ArrayList<>();
    private ArrayList<Double> powerupDropModArray = new ArrayList<>();
    private ArrayList<Double> powerupDropChanceArray = new ArrayList<>();


    public PowerupFactory(GameEngine gameEngine, PlayerShip player) {
        this.gameEngine = gameEngine;
        this.player = player;
        initPowerups();
        powerupDistribution();//Todo, Muss bei jeder Veränderung der Playerwerte angepasst werden
    }


    public void createPowerup(double x, double y, int score) {
        double dropChance = score / GameConstants.POWERUP_DROPCHANCE_PER_SCORE;
        double drop = Math.random();
        if (drop < dropChance) {
            drop = Math.random();
            for (int i = 0; i < powerupArray.size(); i++) {
                if (powerupDropChanceArray.get(i) < drop) {
                    dropPowerup(powerupArray.get(i), x, y);
                }
            }
        }
    }

    private void powerupDistribution() {
        double powerupDropModSum = 0;
        for (int i = 0; i < powerupArray.size(); i++) {
            powerupDropModSum += powerupDropModArray.get(i);
        }
        for (int i = 0; i < powerupArray.size(); i++) {
            if (i != 0) {
                powerupDropChanceArray.add(powerupDropChanceArray.get(i - 1) + powerupDropModArray.get(i) / powerupDropModSum);
            } else powerupDropChanceArray.add(powerupDropModArray.get(i) / powerupDropModSum);
        }
    }

    private void initPowerups() {
        addPowerup(PowerupTypes.Multishoot, GameConstants.POWERUP_DROPMOD_MULTISHOOT);
        addPowerup(PowerupTypes.Fireup, GameConstants.POWERUP_DROPMOD_FIREUP);
        addPowerup(PowerupTypes.Healthup, GameConstants.POWERUP_DROPMOD_HEALTHUP);
    }

    private void addPowerup(PowerupTypes powerup, double powerupDropMod) {
        powerupArray.add(powerup);
        powerupDropModArray.add(powerupDropMod);
    }

    private void dropPowerup(PowerupTypes powerupType, double x, double y) {
        switch (powerupType) {
            case Multishoot:
                new MultiShot(gameEngine, player, x, y);
                break;
            case Fireup:
                new FireUp(gameEngine, player, x, y);
                break;
            case Healthup:
                new HealthUp(gameEngine, player, x, y);
                break;
            default:
                Log.d("Invalid PowerupType", "dropPowerup: " + powerupType);
        }
    }
}
