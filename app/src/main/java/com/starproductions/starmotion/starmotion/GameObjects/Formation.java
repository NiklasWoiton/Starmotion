package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Canvas;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;

public class Formation extends GameObject {

    private Fighter[] squad;

    public Formation(GameEngine gameEngine, Fighter[] squad) {
        super(gameEngine);
        this.squad = squad;
    }

    @Override
    public void update() {
        if (squad.length == 0) destroy();
        for (int squadID = 0; squadID < squad.length; squadID++) {
            //Turn if left of Screen
            if (squad[squadID].x <= 0 && squad[squadID].speedX < 0) {
                turnSquad();
                break;
            }
            //Turn if right of Screen
            else if (squad[squadID].x >= GameConstants.SIZE.x - squad[squadID].getHitBox().width() && squad[squadID].speedX > 0) {
                turnSquad();
                break;
            }
        }
    }

    private void turnSquad() {
        for (int squadID = 0; squadID < squad.length; squadID++) {
            squad[squadID].turn();
        }
    }

    //Todo, find a better implementation, without empty methods
    public void draw(Canvas canvas, double extrapolation) {
    }

    public void setAsset() {
    }
}