package com.starproductions.starmotion.starmotion.PlayerInput;

import android.view.KeyEvent;

import com.starproductions.starmotion.starmotion.GameConstants;

import java.util.Observable;

public class KeyController extends Observable {

    private InputManager inputManager;
    private float velocity = 0;

    KeyController(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public boolean onKey(int keyCode, KeyEvent keyEvent) {
        switch (keyCode) {
            /*
             * Move Left
             * -activate on Down
             * -deactivate on Up
             */
            case KeyEvent.KEYCODE_DPAD_LEFT:
            case KeyEvent.KEYCODE_A:
                switch (keyEvent.getAction()) {
                    case KeyEvent.ACTION_DOWN:
                        velocity = -GameConstants.KEYBOARD_SPEED_CHANGE;
                        notifyCurrentSpeed();
                    case KeyEvent.ACTION_UP:
                }
                return true;
            /*
             * Move Right
             * -activate on Down
             * -deactivate on Up
             */
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            case KeyEvent.KEYCODE_D:
                switch (keyEvent.getAction()) {
                    case KeyEvent.ACTION_DOWN:
                        velocity = GameConstants.KEYBOARD_SPEED_CHANGE;
                        notifyCurrentSpeed();
                    case KeyEvent.ACTION_UP:
                }
                return true;
            /*
             * Shoot
             * -activate on Down
             * -deactivate on Up
             */
            case KeyEvent.KEYCODE_DPAD_DOWN:
            case KeyEvent.KEYCODE_S:
            case KeyEvent.KEYCODE_SPACE:
                return inputManager.onTouchEmulation(keyEvent);
            /*
             *Orientation Controls
             * -reactivate on Down
             */
            case KeyEvent.KEYCODE_ENTER:
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    inputManager.switchKeyboardActive();
                }
                return true;

            default:
                return false;
        }
    }

    private void notifyCurrentSpeed() {
        if (countObservers() > 0) {
            setChanged();//Todo, What does this do?
            notifyObservers(velocity);
        }
    }
}
