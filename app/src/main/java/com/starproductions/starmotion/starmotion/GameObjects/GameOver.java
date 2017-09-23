package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Canvas;
import android.text.TextPaint;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;

/**
 * Created by Jakob on 21.09.2017.
 */

public class GameOver extends HudObject {

    private TextPaint textPaint;

    public GameOver(GameEngine gameEngine) {
        super(gameEngine);
        x = GameConstants.SIZE.x * GameConstants.GAME_OVER_SIZE_X_FACTOR;
        y = GameConstants.SIZE.y * GameConstants.GAME_OVER_SIZE_Y_FACTOR;
    }

    @Override
    protected void setAsset() {
        textPaint = new TextPaint();
        textPaint.setColor(GameConstants.GAME_OVER_COLOR);
        textPaint.setTextSize((float) (GameConstants.GAME_OVER_SIZE_Factor * GameConstants.SIZE.x));
    }

    @Override
    public void draw(Canvas c, double extrapolation) {
        c.drawText(GameConstants.GAME_OVER_TEXT, (float) (x + (0.5 * (GameConstants.SIZE.x - textPaint.measureText(GameConstants.GAME_OVER_TEXT)))),
                (float) (y + (0.5 * (GameConstants.SIZE.y - textPaint.getTextSize()))), textPaint);
    }

    @Override
    public void update() {

    }
}
