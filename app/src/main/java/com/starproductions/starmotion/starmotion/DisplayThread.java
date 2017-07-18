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
    private GameEngine gameEngine;
    private int delay = 20;


    public DisplayThread(SurfaceHolder surfaceHolder, GameEngine gameEngine){
        this.surfaceHolder = surfaceHolder;
        this.gameEngine = gameEngine;
        isRunning = true;
    }

    @Override
    public void run(){
        while (isRunning){
            gameEngine.update();

            Canvas canvas = surfaceHolder.lockCanvas();

            if (canvas != null){
                synchronized (surfaceHolder){
                    gameEngine.draw(canvas);
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
