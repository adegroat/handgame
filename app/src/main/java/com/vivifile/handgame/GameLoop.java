package com.vivifile.handgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.vivifile.handgame.Gui.InGameMenu;
import com.vivifile.handgame.Gui.Menu;
import com.vivifile.handgame.Gui.MainMenu;
import com.vivifile.handgame.Gui.PauseMenu;

import java.util.Stack;

/**
 * Created by alex on 1/1/17.
 */

public class GameLoop extends Thread {

    private boolean isRunning = false;
    private boolean isPaused = true;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private int fps;
    public Stack<Menu> menus;
    private Menu inGameMenu;

    public GameLoop(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        menus = new Stack<Menu>();
        inGameMenu = new InGameMenu(this);
        menus.push(new MainMenu(this));
    }

    public void pauseGame(){
        isPaused = true;
        menus.push(new PauseMenu(this));
    }

    public void resumeGame(){
        isPaused = false;
        menus.clear();
        menus.push(inGameMenu);
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
            draw(canvas);
            surfaceHolder.unlockCanvasAndPost(canvas);
            update(secondsPerFrame);

        }

    }

    private void update(float delta){

    }

    private void draw(Canvas can) {
        can.drawColor(Color.BLACK);

        Paint p = new Paint();
        p.setColor(Color.RED);

        p.setTextSize(25);
        canvas.drawText(fps + "fps", 25, 25, p);


        if(menus.peek() == inGameMenu){
            p.setColor(Color.RED);
            can.drawRect(200, 200, 400, 400, p);
        }

        menus.peek().draw(can);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(!menus.empty()) menus.peek().handleInputs(event);
        return true;
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
