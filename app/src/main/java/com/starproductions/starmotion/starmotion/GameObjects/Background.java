package com.starproductions.starmotion.starmotion.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.R;

import java.util.Random;

public class Background extends GameObject {
    private float[] pts;
    private Paint p;

    public Background(GameEngine gameEngine) {
        super(gameEngine);
        p = new Paint();
        p.setColor(Color.WHITE);
        p.setStrokeWidth(3);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void setAsset() {
        Random r = new Random();
        pts = new float[GameConstants.STAR_COUNT * 2];
        for (int i = 0; i < pts.length - 1; i += 2) {
            int x = r.nextInt(GameConstants.SIZE.x),
                    y = r.nextInt(GameConstants.SIZE.y);
            pts[i] = x;
            pts[i + 1] = y;
        }
    }

    @Override
    public void draw(Canvas c, double extrapolation) {
        c.drawColor(gameEngine.getResources().getColor(R.color.black));
        c.drawPoints(pts, p);
    }

    @Override
    public void update() {

    }
}
