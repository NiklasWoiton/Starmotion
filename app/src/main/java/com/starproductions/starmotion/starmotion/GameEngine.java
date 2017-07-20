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
    private Rect rect2 = new Rect(300, 100, 400, 200);
    private Paint paint = new Paint();
    private double offsetX = 2;
    private double offsetY = 4;

    public GameEngine(){
        paint.setColor(Color.YELLOW);
    }

    public void update(){
        //Process Inputs
        //Update GameObjects
        //Check Collision
        rect.offset((int)offsetX, (int)offsetY);
        rect2.offset((int)offsetX, (int)offsetY);


    }

    public void draw(Canvas canvas, double extrapolation){
        //Draw Gameobjects
        canvas.drawColor(Color.BLACK);
        Rect drawRect = new Rect(rect);
        drawRect.offset((int)(offsetX*extrapolation), (int) (offsetY*extrapolation));
        canvas.drawRect(drawRect, paint);
        canvas.drawRect(rect2, paint);
    }

}
