package com.vivifile.handgame.Gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.vivifile.handgame.GameLoop;
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
    private int x, y;
    private String text;
    private boolean isHolding;
    private Paint paint;
    private Rect textBounds;

    public Button(int id, int x, int y, String text){
        this.id = id;
        this.x = x;
        this.y = y;
        this.text = text;
        paint = new Paint();
        paint.setTextSize(50);
        textBounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBounds);
    }

    protected void draw(Canvas can) {
        paint.setColor(isHolding ? Color.GREEN : Color.BLUE);
        can.drawRect(x, y, x + BUTTON_WIDTH, y + BUTTON_HEIGHT, paint);

        paint.setColor(Color.WHITE);

        int cx = x + ((BUTTON_WIDTH - textBounds.width()) / 2);
        int cy = y + (BUTTON_HEIGHT + textBounds.height()) / 2 - 5;
        can.drawText(text, cx, cy, paint);
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
