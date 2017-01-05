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
        can.drawText("Each round you're assigned a specific color.", 70, 400, paint);
        can.drawText("When it lands on your color, press the correct button.", 70, 450, paint);
    }

    @Override
    protected void onClick(Button b) {
        if(b.getId() == 0) gl.menus.pop();
    }
}
