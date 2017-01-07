package com.vivifile.handgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.vivifile.handgame.Game.Game;
import com.vivifile.handgame.Gui.InGameMenu;
import com.vivifile.handgame.Gui.Menu;
import com.vivifile.handgame.Gui.MainMenu;

import java.util.Random;
import java.util.Stack;

/**
 * Created by alex on 1/1/17.
 */

public class GameLoop extends Thread {

    private volatile boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private int fps;
    public Stack<Menu> menus;
    private Game game;
    private Context context;
    private MediaPlayer mediaPlayer;

    public GameLoop(SurfaceHolder surfaceHolder, Context context) {
        this.surfaceHolder = surfaceHolder;
        this.context = context;
        menus = new Stack<Menu>();
        menus.push(new MainMenu(this));
    }

    public void startNewGame(){
        menus.push(new InGameMenu(this).setOverlay(false));
        game = new Game(this);
    }

    public void endGame(){
        game = null;
    }

    @Override
    public void run(){

        long currentTime, prevTime = System.currentTimeMillis();

        while(isRunning) {
            currentTime = System.currentTimeMillis();
            float secondsPerFrame = (currentTime - prevTime) / 1000.0f;
            prevTime = currentTime;

            fps = (int)(1 / secondsPerFrame);

            canvas = surfaceHolder.lockCanvas();
            if(canvas == null) return;
            draw(canvas);
            surfaceHolder.unlockCanvasAndPost(canvas);
            if(game != null) {
                game.update(secondsPerFrame);
            }
        }
    }


    private void draw(Canvas can) {
        can.drawColor(Render.COLOR_BLUE);

        if(game != null) game.draw(can);

        if(menus.peek() != null) menus.peek().draw(can);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(!menus.empty()) menus.peek().handleInputs(event);
        if(game != null) game.onTouchEvent(event);
        return true;
    }

    public Game getGame(){
        return game;
    }

    public Context getContext(){
        return context;
    }

    public void startMusic(){
        if(mediaPlayer != null) return;

        mediaPlayer = MediaPlayer.create(context, R.raw.handgame_song2);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void stopMusic(){
        if(mediaPlayer == null) return;
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public void toggleMusic(){
        if(mediaPlayer == null) startMusic();
        else stopMusic();
    }

    public void doStart(){
        isRunning = true;
        startMusic();
        this.start();
    }

    public void doStop(){
        isRunning = false;
        stopMusic();
    }

}
