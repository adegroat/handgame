package com.vivifile.handgame.Gui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.Render;
import com.vivifile.handgame.RenderView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 1/1/17.
 */

public class Menu {

    public static final int CENTER_X = RenderView.WIDTH / 2;
    public static final int CENTER_Y = RenderView.HEIGHT / 2;


    private List<Button> buttons;
    protected GameLoop gl;
    protected Paint paint;
    private boolean drawOverlay;

    public Menu(GameLoop gl){
        this.gl = gl;
        paint = new Paint();
        paint.setTextSize(70);
        drawOverlay = true;
        buttons = new ArrayList<Button>();
        addElements();
    }

    public void draw(Canvas can) {
        if(drawOverlay) can.drawColor(Render.COLOR_BLUE);
        for(Button b : buttons) b.draw(can);
    }

    public void handleInputs(MotionEvent event){
        for(Button b : buttons) {
            b.handleInput(event, this);
        }
    }

    public Menu setOverlay(boolean drawOverlay) {
        this.drawOverlay = drawOverlay;
        return this;
    }

    protected void addButton(Button b) {
        buttons.add(b);
    }

    protected void addElements(){
    }

    protected void onClick(Button b){
    }
}
