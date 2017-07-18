package com.starproductions.starmotion.starmotion;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jakob on 18.07.2017.
 */

public class GameEngine {

    private Rect rect = new Rect(100, 100, 200, 200);
    private Paint paint = new Paint();

    public GameEngine(){
        paint.setColor(Color.YELLOW);
    }

    public void update(){
        //Update GameObjects
        //Check Collision
        rect.offset(1, 1);
    }

    public void draw(Canvas canvas){
        //Draw Gameobjects
        canvas.drawColor(Color.BLACK);
        canvas.drawRect(rect, paint);
    }

}
