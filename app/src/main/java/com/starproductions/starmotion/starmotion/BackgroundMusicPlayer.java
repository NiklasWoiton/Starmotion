package com.starproductions.starmotion.starmotion;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * MusicPlayer for the music playing in the background.
 *
 * Credits for the music
 * Author: Jan125
 * Source: "https://opengameart.org/content/stereotypical-90s-space-shooter-music"
 * License: OGA-BY 3.0
 */
public class BackgroundMusicPlayer {
    private MediaPlayer mp;

    public BackgroundMusicPlayer(Context context){
        mp = MediaPlayer.create(context, R.raw.bg_music);
        mp.setLooping(true);
    }

    public void start(){
        if(mp != null)
            mp.start();
    }

    public void stop(){
        if(mp != null)
            mp.stop();
    }

    public void pause(){
        if(mp != null)
            mp.pause();
    }

    public void release(){
        mp.release();
        mp = null;
    }
}
