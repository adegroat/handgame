package com.vivifile.handgame;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by alex on 1/7/17.
 */

public class Settings {

    public static final String PREF_NAME = "handgame_prefs";
    public static final String PREF_MUSIC = "music";
    public static final String PREF_HIGHSCORE = "highscore";

    private SharedPreferences prefs;

    public Settings(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isMusicPlaying(){
        return prefs.getBoolean(PREF_MUSIC, true);
    }

    public float getHighScore(){
        return prefs.getFloat(PREF_HIGHSCORE, 0.0f);
    }

    public void updateHighScore(float newHighScore){
        if(newHighScore > getHighScore()) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putFloat(PREF_HIGHSCORE, newHighScore);
            editor.commit();
        }
    }

    public void setMusicStatus(boolean musicStatus){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PREF_MUSIC, musicStatus);
        editor.commit();
    }

    public boolean getMusicStatus(){
        return prefs.getBoolean(PREF_MUSIC, true);
    }
}
