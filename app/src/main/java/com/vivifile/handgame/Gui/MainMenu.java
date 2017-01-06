package com.vivifile.handgame.Gui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.R;
import com.vivifile.handgame.RenderView;


/**
 * Created by alex on 1/1/17.
 */

public class MainMenu extends Menu {

    private Bitmap logo;
    private Matrix logoMatrix;

    public MainMenu(GameLoop gl){
        super(gl);
        logo = BitmapFactory.decodeResource(gl.getContext().getResources(), R.drawable.logo);

        Matrix scale = new Matrix();
        float scaleX = 0.9f * (float)RenderView.WIDTH / (float)logo.getWidth();
        scale.preScale(scaleX, 1.0f);
        logo = Bitmap.createBitmap(logo, 0, 0, logo.getWidth(), logo.getHeight(), scale, false);
    }

    @Override
    protected void addElements(){
        addButton(new Button(0, Button.BUTTON_FILL_CENTER_X, Button.BUTTON_CENTER_Y - 100, "Play"));
        addButton(new Button(1, Button.BUTTON_FILL_CENTER_X, Button.BUTTON_CENTER_Y + 100, "How To Play"));
    }

    @Override
    public void draw(Canvas can) {
        super.draw(can);

        can.drawBitmap(logo, CENTER_X - logo.getWidth() / 2, 100, null);
    }

    @Override
    protected void onClick(Button b) {

        switch(b.getId()) {
            case 0:
                gl.startNewGame();
                break;
            case 1:
                gl.menus.push(new HelpMenu(gl));
        }
    }
}
