package com.vivifile.handgame.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.Gui.PauseMenu;
import com.vivifile.handgame.RenderView;

/**
 * Created by alex on 1/2/17.
 */

public class Game {

    private GameLoop gl;
    private boolean isPaused = false;

    private Hand redHand;

    public Game(GameLoop gl) {
        this.gl = gl;
        redHand = new Hand(Hand.HandType.RIGHT, gl.getContext());
    }

    public void start(){

    }

    public void draw(Canvas can) {
        Paint p = new Paint();
        p.setARGB(255, 144, 150, 120);
        can.drawCircle(RenderView.WIDTH / 2 + 2, RenderView.WIDTH / 2 + 60, RenderView.WIDTH / 2 - 4, p);

        redHand.draw(can);
    }

    public void update(float spf) {

    }

    public void pause(){
        isPaused = true;
        gl.menus.push(new PauseMenu(gl));
    }

    public void resume(){
        isPaused = false;
        gl.menus.pop();
    }
}
