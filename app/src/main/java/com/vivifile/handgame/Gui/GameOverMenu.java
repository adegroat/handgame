package com.vivifile.handgame.Gui;

import android.graphics.Canvas;
import android.graphics.Color;

import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.Render;
import com.vivifile.handgame.RenderView;

import java.text.DecimalFormat;

/**
 * Created by alex on 1/5/17.
 */

public class GameOverMenu extends Menu {

    private double playTime;

    public GameOverMenu(GameLoop gl, double playTime) {
        super(gl);
        this.playTime = playTime;
    }

    @Override
    protected void addElements(){
        addButton(new Button(0, Button.BUTTON_FILL_CENTER_X, Button.BUTTON_CENTER_Y + 250, "Play Again"));
        addButton(new Button(1, Button.BUTTON_FILL_CENTER_X, Button.BUTTON_CENTER_Y + 400, "Main Menu"));
    }

    @Override
    public void draw(Canvas can) {
        can.drawColor(Color.BLACK);
        super.draw(can);

        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        Render.drawCenterText(can, paint, CENTER_X, 200, "Game Over");

        paint.setTextSize(70);
        Render.drawCenterText(can, paint, CENTER_X, 400, "Time: " + String.format("%.2f", playTime) + " s");
    }

    @Override
    protected void onClick(Button b) {
        if(b.getId() == 0) gl.startNewGame();
        if(b.getId() == 1) gl.menus.push(new MainMenu(gl));
    }
}
