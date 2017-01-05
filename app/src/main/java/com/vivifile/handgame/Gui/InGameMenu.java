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
        addButton(new Button(0, 50, RenderView.HEIGHT - 200, Button.BUTTON_HEIGHT, Button.BUTTON_HEIGHT, "| |"));
        addButton(new Button(1, Button.BUTTON_HEIGHT + 100, RenderView.HEIGHT - 200, "Restart"));
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
