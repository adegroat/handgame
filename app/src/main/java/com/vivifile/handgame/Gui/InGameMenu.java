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
        addButton(new Button(0, (RenderView.WIDTH - Button.BUTTON_HEIGHT) / 2, RenderView.HEIGHT - Button.BUTTON_HEIGHT - 50, Button.BUTTON_HEIGHT, Button.BUTTON_HEIGHT, "| |"));
    }

    @Override
    public void draw(Canvas can) {
        super.draw(can);
    }

    @Override
    protected void onClick(Button b) {
        switch(b.getId()) {
            case 0:
                gl.getGame().pause();
                break;
            case 1:
                gl.startNewGame();
                break;
        }
    }
}
