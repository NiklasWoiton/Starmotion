package com.starproductions.starmotion.starmotion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by jakob on 17.07.2017.
 */

//Parts of this are created with the help of: https://www.codeproject.com/Articles/827608/Android-Basic-Game-Loop
public class DisplayThread extends Thread {

    private boolean isRunning;
    private SurfaceHolder surfaceHolder;
    private int delay = 20;

    private Rect rect = new Rect(100, 100, 200, 200);
    private Paint paint = new Paint();


    public DisplayThread(SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        isRunning = true;

        paint.setColor(Color.YELLOW);
    }

    @Override
    public void run(){
        while (isRunning){
            //Update Engine
            rect.offset(1, 1);

            Canvas canvas = surfaceHolder.lockCanvas();

            if (canvas != null){
                synchronized (surfaceHolder){
                    //Draw Objects on Screen
                    canvas.drawColor(Color.BLACK);
                    canvas.drawRect(rect, paint);
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
            try{
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getIsRunning(){
        return isRunning;
    }

    public void setIsRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

}
