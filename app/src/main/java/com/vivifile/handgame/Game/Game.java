package com.vivifile.handgame.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.Gui.PauseMenu;
import com.vivifile.handgame.Render;

/**
 * Created by alex on 1/2/17.
 */

public class Game {


    private GameLoop gl;
    private Table table;
    private boolean isPaused = false;
    private boolean gameStarted = false;
    private Paint paint;
    private int countdownTime = 3;
    private long prevTime;

    public Game(GameLoop gl) {
        this.gl = gl;
        table = new Table(this, 4);
        paint = new Paint();
        prevTime = System.currentTimeMillis();
    }

    public void draw(Canvas can) {
        table.draw(can);

        if(countdownTime > 0) {
            paint.setTextSize(100);
            paint.setColor(Color.WHITE);
            Render.drawCenterText(can, paint, Table.TABLE_RADIUS, Table.TABLE_RADIUS - 100, "Get Ready!");
            Render.drawCenterText(can, paint, Table.TABLE_RADIUS, Table.TABLE_RADIUS + 100, countdownTime + "");
        }
    }

    public void update(float spf) {
        if(isPaused) return;
        if(gameStarted)table.update(spf);

        if(System.currentTimeMillis() - prevTime > 1000) {
            countdownTime--;
            if(countdownTime <= 0) gameStarted = true;
            prevTime = System.currentTimeMillis();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(gameStarted) table.onTouchEvent(event);
        return true;
    }

    public void pause(){
        isPaused = true;
        gl.menus.push(new PauseMenu(gl));
    }

    public void resume(){
        isPaused = false;
        gl.menus.pop();
    }

    public Context getContext(){
        return gl.getContext();
    }
}
