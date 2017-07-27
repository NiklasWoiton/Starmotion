package com.starproductions.starmotion.starmotion;

import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;

public class ScoreDisplay extends HudObject {

    public ScoreDisplay(GameEngine gameEngine) {
        super(gameEngine);
        x = GameConstants.SIZE.x * 0.80;
        y = GameConstants.SIZE.y * 0.05;
    }

    @Override
    protected void setAsset() {

    }

    @Override
    public void draw(Canvas c, double extrapolation) {
        String score = String.valueOf(gameEngine.getScoreHolder().getScore());
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(16 * gameEngine.getResources().getDisplayMetrics().density);

        c.drawText(score, (float) x - textPaint.measureText(score), (float) y, textPaint);
    }

    @Override
    public void update() {

    }
}
