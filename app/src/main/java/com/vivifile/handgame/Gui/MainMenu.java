package com.vivifile.handgame.Gui;

import android.graphics.Canvas;
import android.util.Log;

import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.Gui.Button;
import com.vivifile.handgame.Gui.Menu;
import com.vivifile.handgame.RenderView;

import java.net.HttpRetryException;

/**
 * Created by alex on 1/1/17.
 */

public class MainMenu extends Menu {

    public MainMenu(GameLoop gl){
        super(gl);
    }

    @Override
    protected void addElements(){
        addButton(new Button(0, Button.BUTTON_CENTER_X, Button.BUTTON_CENTER_Y - 100, "Play"));
        addButton(new Button(1, Button.BUTTON_CENTER_X, Button.BUTTON_CENTER_Y + 100, "How To Play"));
    }

    @Override
    public void draw(Canvas can) {
        super.draw(can);
    }

    @Override
    protected void onClick(Button b) {

        switch(b.getId()) {
            case 0:
                gl.resumeGame();
                break;
            case 1:
                gl.menus.push(new HelpMenu(gl));
        }
    }
}
