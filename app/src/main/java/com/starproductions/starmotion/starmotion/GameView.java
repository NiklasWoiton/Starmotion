package com.starproductions.starmotion.starmotion;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.io.Console;

/**
 * Created by jakob on 17.07.2017.
 */

//Parts of this are created with the help of: https://www.codeproject.com/Articles/827608/Android-Basic-Game-Loop
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private DisplayThread displayThread;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        displayThread = new DisplayThread(getHolder());
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (!displayThread.getIsRunning()) {
            displayThread = new DisplayThread(getHolder());
            displayThread.start();
        }
        else {
            displayThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        displayThread.setIsRunning(false);

        boolean retry = true;
        while (retry)
            try {
                displayThread.join();
                retry = false;
            }
            catch (InterruptedException ignored) {}
    }
}
