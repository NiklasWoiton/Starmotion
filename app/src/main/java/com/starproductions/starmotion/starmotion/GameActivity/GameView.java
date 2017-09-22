package com.starproductions.starmotion.starmotion.GameActivity;

import android.content.Context;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.starproductions.starmotion.starmotion.GameEngine;
import com.starproductions.starmotion.starmotion.PlayerInput.InputManager;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffectManager;

//Parts of this are created with the help of: https://www.codeproject.com/Articles/827608/Android-Basic-Game-Loop
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private DisplayThread displayThread;
    private GameEngine gameEngine;

    public GameView(Context context, InputManager inputManager, SoundEffectManager soundEffectManager) {
        super(context);

        getHolder().addCallback(this);

        gameEngine = new GameEngine(getResources(), inputManager, soundEffectManager, (GameActivity) context);

        displayThread = new DisplayThread(getHolder(), gameEngine);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (!displayThread.getIsRunning()) {
            displayThread = new DisplayThread(getHolder(), gameEngine);
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
