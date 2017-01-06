package com.vivifile.handgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.vivifile.handgame.Game.Game;
import com.vivifile.handgame.Gui.InGameMenu;
import com.vivifile.handgame.Gui.Menu;
import com.vivifile.handgame.Gui.MainMenu;

import java.util.Stack;

/**
 * Created by alex on 1/1/17.
 */

public class GameLoop extends Thread {

    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private int fps;
    public Stack<Menu> menus;
    private Game game;
    private Context context;

    public GameLoop(SurfaceHolder surfaceHolder, Context context) {
        this.surfaceHolder = surfaceHolder;
        this.context = context;
        menus = new Stack<Menu>();
        menus.push(new MainMenu(this));
    }

    public void startNewGame(){
        menus.clear();
        menus.push(new InGameMenu(this));
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
        can.drawColor(Color.BLACK);

        Paint p = new Paint();
        p.setColor(Color.RED);

        p.setTextSize(25);
        canvas.drawText(fps + "fps", 25, 25, p);


        if(game != null){
           game.draw(can);
        }

        menus.peek().draw(can);
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

    public void doStart(){
        isRunning = true;
        this.start();
    }

    public void doStop(){
        isRunning = false;

        while(true){
            try {
                this.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
