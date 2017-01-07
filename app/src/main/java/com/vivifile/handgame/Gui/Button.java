package com.vivifile.handgame.Gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.Render;
import com.vivifile.handgame.RenderView;

/**
 * Created by alex on 1/1/17.
 */

public class Button {

    public static final int BUTTON_WIDTH = 400;
    public static final int BUTTON_FILL_WIDTH = RenderView.WIDTH - (int)(RenderView.WIDTH * .25);
    public static final int BUTTON_HEIGHT = 120;
    public static final int BORDER_SIZE = 5;

    public static final int BUTTON_CENTER_X = (RenderView.WIDTH - BUTTON_WIDTH) / 2;
    public static final int BUTTON_FILL_CENTER_X = (RenderView.WIDTH - BUTTON_FILL_WIDTH) / 2;
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
        this.width = BUTTON_FILL_WIDTH;
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
        if(isHolding) {
            paint.setColor(Color.WHITE);
            can.drawRect(x - BORDER_SIZE, y - BORDER_SIZE, x + width + BORDER_SIZE, y + height + BORDER_SIZE, paint);
        }

        paint.setColor(Render.COLOR_BLUE);
        can.drawRect(x, y, x + width, y + height, paint);

        paint.setColor(Color.WHITE);

        Render.drawCenterText(can, paint, x + (width / 2), y + (height / 2), text);
    }

    protected void handleInput(MotionEvent event, Menu menu){
        int tapX = (int)event.getRawX();
        int tapY = (int)event.getRawY();

        boolean inBounds = tapX >= x && tapX <= x + width && tapY >= y && tapY <= y + height;

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

    public void setText(String text) {
        this.text = text;
    }

    public int getId(){
        return id;
    }
}
