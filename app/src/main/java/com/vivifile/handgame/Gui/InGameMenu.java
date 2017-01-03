package com.vivifile.handgame.Gui;

import android.graphics.Canvas;

import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.RenderView;

/**
 * Created by alex on 1/2/17.
 */

public class InGameMenu extends Menu {

    public InGameMenu(GameLoop gl) {
        super(gl);
    }

    @Override
    protected void addElements(){
        addButton(new Button(0, Button.BUTTON_CENTER_X, RenderView.HEIGHT - 200, "| |"));
    }

    @Override
    public void draw(Canvas can) {
        super.draw(can);
    }

    @Override
    protected void onClick(Button b) {
        if(b.getId() == 0) gl.getGame().pause();
    }
}
