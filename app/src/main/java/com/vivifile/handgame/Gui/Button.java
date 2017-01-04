package com.vivifile.handgame.Gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.Render;
import com.vivifile.handgame.RenderView;

/**
 * Created by alex on 1/1/17.
 */

public class Button {

    public static final int BUTTON_WIDTH = 400;
    public static final int BUTTON_HEIGHT = 150;

    public static final int BUTTON_CENTER_X = (RenderView.WIDTH - BUTTON_WIDTH) / 2;
    public static final int BUTTON_CENTER_Y = (RenderView.HEIGHT - BUTTON_HEIGHT) / 2;

    private int id;
    private int x, y, width, height;
    private String text;
    private boolean isHolding;
    private Paint paint;

    public Button(int id, int x, int y, String text){
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = BUTTON_WIDTH;
        this.height = BUTTON_HEIGHT;
        this.text = text;

        init();
    }

    public Button(int id, int x, int y, int width, int height, String text) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;

        init();
    }

    private void init(){
        paint = new Paint();
        paint.setTextSize(50);
    }

    protected void draw(Canvas can) {
        paint.setColor(isHolding ? Color.GREEN : Color.BLUE);
        can.drawRect(x, y, x + width, y + height, paint);

        paint.setColor(Color.WHITE);

        Render.drawCenterText(can, paint, x + (width / 2), y + (height / 2), text);
    }

    protected void handleInput(MotionEvent event, Menu menu){
        int tapX = (int)event.getRawX();
        int tapY = (int)event.getRawY();

        boolean inBounds = tapX >= x && tapX <= x + BUTTON_WIDTH && tapY >= y && tapY <= y + BUTTON_HEIGHT;

        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                isHolding = inBounds;
                break;
            case MotionEvent.ACTION_UP:
                isHolding = false;
                if(inBounds) menu.onClick(this);
                break;
        }
    }

    public int getId(){
        return id;
    }
}
