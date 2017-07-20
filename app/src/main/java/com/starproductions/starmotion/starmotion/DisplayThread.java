package com.starproductions.starmotion.starmotion;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.starproductions.starmotion.starmotion.PlayerMovement.GameConstants;

import java.util.Calendar;

/**
 * Created by jakob on 17.07.2017.
 */

//Parts of this are created with the help of:
// -https://www.codeproject.com/Articles/827608/Android-Basic-Game-Loop
// -http://gameprogrammingpatterns.com/game-loop.html
public class DisplayThread extends Thread {

    private boolean isRunning;
    private SurfaceHolder surfaceHolder;
    private GameEngine gameEngine;

    private double previous = System.currentTimeMillis();
    private double lag = 0.0;

    public DisplayThread(SurfaceHolder surfaceHolder, GameEngine gameEngine){
        this.surfaceHolder = surfaceHolder;
        this.gameEngine = gameEngine;
        isRunning = true;
    }

    @Override
    public void run(){
        previous = System.currentTimeMillis();
        while (isRunning){
            double current = System.currentTimeMillis();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            while (lag >= GameConstants.MS_PER_UPDATE){
                gameEngine.update();
                lag -= GameConstants.MS_PER_UPDATE;
            }

            Canvas canvas = surfaceHolder.lockCanvas();

            if (canvas != null){
                synchronized (surfaceHolder){
                    gameEngine.draw(canvas, lag/GameConstants.MS_PER_UPDATE);
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
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
