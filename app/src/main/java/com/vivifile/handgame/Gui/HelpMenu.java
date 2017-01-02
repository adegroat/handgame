package com.vivifile.handgame.Gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.RenderView;

/**
 * Created by alex on 1/2/17.
 */

public class HelpMenu extends Menu {

    private String title = "How To Play";
    private Rect textBounds = new Rect();

    public HelpMenu(GameLoop gl) {
        super(gl);
        paint.getTextBounds(title, 0, title.length(), textBounds);
    }

    @Override
    protected void addElements(){
        addButton(new Button(0, Button.BUTTON_CENTER_X, RenderView.HEIGHT - 300, "Back"));
    }

    @Override
    public void draw(Canvas can) {
        super.draw(can);
        paint.setColor(Color.WHITE);
        paint.setTextSize(70);
        can.drawText("How To Play", (RenderView.WIDTH - textBounds.width()) / 2, 200, paint);

        paint.setTextSize(40);
        can.drawText("Each round you're assigned a specific color.", 70, 300, paint);
        can.drawText("When it lands on your color, press the correct button.", 70, 350, paint);
    }

    @Override
    protected void onClick(Button b) {
        if(b.getId() == 0) gl.menus.pop();
    }
}
