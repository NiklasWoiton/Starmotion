package com.starproductions.starmotion.starmotion;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import com.starproductions.starmotion.starmotion.PlayerMovement.InputManager;

public class GameActivity extends Activity implements View.OnTouchListener {
private InputManager inputManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        onWindowFocusChanged(true);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        GameConstants.SIZE = size;
        int width = size.x;
        int height = size.y;
        SurfaceView view = new GameView(this, inputManager);
        setContentView(view);
        view.setOnTouchListener(this);
        inputManager = new InputManager(this, GameConstants.SIZE.x);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        inputManager.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        inputManager.stop();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return inputManager.onTouch(view, motionEvent);
    }
}
