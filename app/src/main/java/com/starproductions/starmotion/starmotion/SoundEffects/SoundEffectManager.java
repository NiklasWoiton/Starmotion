package com.starproductions.starmotion.starmotion.SoundEffects;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;

import com.starproductions.starmotion.starmotion.R;

import java.util.HashMap;


public class SoundEffectManager {
    private SoundPool sp;
    private Activity activity;
    private HashMap<SoundEffects, Integer> effectsMap = new HashMap<>();

    public SoundEffectManager(Activity activity) {
        this.activity = activity;
        sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initSounds();
    }

    /**
     * All soundeffects are registered here
     */
    private void initSounds() {
        //put all sounds here
        registerNewSound(SoundEffects.LaserShoot, R.raw.lasershot);
        registerNewSound(SoundEffects.Explosion, R.raw.explosion);
        registerNewSound(SoundEffects.Powerup, R.raw.powerup);
        registerNewSound(SoundEffects.PlayerHit, R.raw.hit);
    }

    /**
     * Registers one soundeffect
     *
     * @param soundEffect The enum value which the sound is played later with.
     * @param soundId     The android sound id, needs to be in a form like R.raw.Lasershoot
     */
    private void registerNewSound(SoundEffects soundEffect, int soundId) {
        effectsMap.put(soundEffect, loadSound(soundId));
    }

    /**
     * Plays a registered sound
     *
     * @param soundEffect The sounds enum value, set in the private initSounds method.
     */
    public void playSound(SoundEffects soundEffect) {
        int soundId = effectsMap.get(soundEffect);
        sp.play(soundId, 1, 1, 1, 0, 1.0f);
    }

    private int loadSound(int id) {
        return sp.load(activity, id, 1);
    }

    /**
     * Releases the soundpool, needs to be called in onDestroy of the activity
     */
    public void destroy() {
        sp.release();
    }
}
