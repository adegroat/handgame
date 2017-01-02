package com.vivifile.handgame.Gui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.vivifile.handgame.GameLoop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 1/1/17.
 */

public class Menu {

    private List<Button> buttons;
    protected GameLoop gl;
    protected Paint paint;

    public Menu(GameLoop gl){
        this.gl = gl;
        paint = new Paint();
        paint.setTextSize(70);
        buttons = new ArrayList<Button>();
        addElements();
    }

    public void draw(Canvas can) {
        for(Button b : buttons) b.draw(can);
    }

    public void handleInputs(MotionEvent event){
        for(Button b : buttons) {
            b.handleInput(event, this);
        }
    }

    protected void addButton(Button b) {
        buttons.add(b);
    }

    protected void addElements(){
    }

    protected void onClick(Button b){
    }
}
