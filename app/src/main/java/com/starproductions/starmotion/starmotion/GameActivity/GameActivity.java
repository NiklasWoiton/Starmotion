package com.starproductions.starmotion.starmotion.GameActivity;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;

import com.starproductions.starmotion.starmotion.BackgroundMusicPlayer;
import com.starproductions.starmotion.starmotion.GameConstants;
import com.starproductions.starmotion.starmotion.PlayerInput.InputManager;
import com.starproductions.starmotion.starmotion.ScoreSystem.ScoreManager;
import com.starproductions.starmotion.starmotion.SoundEffects.SoundEffectManager;

public class GameActivity extends Activity implements View.OnTouchListener {
    private InputManager inputManager;
    private SoundEffectManager soundEffectManager;
    private BackgroundMusicPlayer backgroundMusicPlayer;
    private EditText input;
    private ScoreManager scoreManager;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onWindowFocusChanged(true);
        getWindowManager().getDefaultDisplay().getSize(GameConstants.SIZE);
        inputManager = new InputManager(this, GameConstants.SIZE.x);
        soundEffectManager = new SoundEffectManager(this);
        scoreManager = new ScoreManager(this);
        backgroundMusicPlayer = new BackgroundMusicPlayer(this);
        SurfaceView view = new GameView(this, inputManager, soundEffectManager);
        setContentView(view);
        view.setOnTouchListener(this);
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
        backgroundMusicPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        inputManager.stop();
        backgroundMusicPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundEffectManager.destroy();
        backgroundMusicPlayer.release();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return inputManager.onTouch(view, motionEvent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        return inputManager.onKey(keyCode, keyEvent);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent keyEvent) {
        return inputManager.onKey(keyCode, keyEvent);
    }

    public void gameFinished(int score) {
        this.score = score;
        scoreManager.start();
        boolean isHighscore = scoreManager.isHighscore(score);
        scoreManager.stop();
        if (isHighscore) {
            DialogFragment getName = new HighscoreNameDialog();
            getName.show(getFragmentManager(), "get_name_for_highscore");
        } else {
            finish();
        }
    }

    public void saveScore(String name) {
        scoreManager.start();
        scoreManager.addScore(name, score);
        scoreManager.stop();
        finish();
    }
}
