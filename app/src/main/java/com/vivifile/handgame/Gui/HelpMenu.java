package com.vivifile.handgame.Gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.Render;
import com.vivifile.handgame.RenderView;

/**
 * Created by alex on 1/2/17.
 */

public class HelpMenu extends Menu {


    public HelpMenu(GameLoop gl) {
        super(gl);
    }

    @Override
    protected void addElements(){
        addButton(new Button(0, Button.BUTTON_FILL_CENTER_X, RenderView.HEIGHT - 300, "Back"));
    }

    @Override
    public void draw(Canvas can) {
        super.draw(can);
        paint.setColor(Color.WHITE);
        paint.setTextSize(70);
        Render.drawCenterText(can, paint, CENTER_X, 300, "How To Play");

        paint.setTextSize(40);
        can.drawText("Take turns tapping in a circle.", 70, 400, paint);
        Render.drawSmartText(can, paint, 70, 470, "When its your turn, tap on the correct hand at the bottom of the screen.");
        can.drawText("Double taps reverse the direction of the game.", 70, 600, paint);
    }

    @Override
    protected void onClick(Button b) {
        if(b.getId() == 0) gl.menus.pop();
    }
}
