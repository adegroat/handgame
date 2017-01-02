package com.vivifile.handgame.Gui;

import android.graphics.Canvas;
import android.graphics.Color;

import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.RenderView;

/**
 * Created by alex on 1/2/17.
 */

public class PauseMenu extends Menu {

    public PauseMenu(GameLoop gl) {
        super(gl);
    }

    @Override
    protected void addElements(){
        addButton(new Button(0, Button.BUTTON_CENTER_X, Button.BUTTON_CENTER_Y, "Resume Game"));
    }

    @Override
    public void draw(Canvas can) {
        super.draw(can);
        paint.setTextSize(70);
        paint.setColor(Color.WHITE);
        can.drawText("Paused", 200, 200, paint);
    }

    @Override
    protected void onClick(Button b) {
        if(b.getId() == 0) gl.resumeGame();
    }
}
