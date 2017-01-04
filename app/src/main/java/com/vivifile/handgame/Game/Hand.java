package com.vivifile.handgame.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.vivifile.handgame.R;
import com.vivifile.handgame.RenderView;

/**
 * Created by alex on 1/2/17.
 */

public class Hand {

    public static final int RED_HAND = R.drawable.red_hand;
    public static final int GREEN_HAND = R.drawable.green_hand;
    public static final int BLUE_HAND = R.drawable.blue_hand;
    public static final int YELLOW_HAND = R.drawable.yellow_hand;

    private int handColor;
    private boolean isRightHand;
    private boolean isComputer;
    private Bitmap handBitmap;

    public Hand(int handColor, boolean isRightHand, Context context){
        this.handColor = handColor;
        this.isRightHand = isRightHand;
        isComputer = true;
        loadBitmaps(context);
    }

    private void loadBitmaps(Context context){
        handBitmap = BitmapFactory.decodeResource(context.getResources(), handColor);

        if(!isRightHand){
            Matrix flip = new Matrix();
            flip.preScale(-1.0f, 1.0f);
            handBitmap = Bitmap.createBitmap(handBitmap, 0, 0, handBitmap.getWidth(), handBitmap.getHeight(), flip, false);
        }
    }

    public void draw(Canvas can, float x, float y, float rotAngle) {
        can.rotate(rotAngle, x, y);
        can.drawBitmap(handBitmap, x - (handBitmap.getWidth() / 2), y - (handBitmap.getHeight() / 2), null);
        can.rotate(-rotAngle, x, y);
    }

    public void setIsComputer(boolean isComputer) {
        this.isComputer = isComputer;
    }

    public int getHeight(){
        return handBitmap.getHeight();
    }

    public Bitmap getHandBitmap() {
        return handBitmap;
    }

    public Bitmap getFlippedHandBitmap(){
        Matrix flip = new Matrix();
        flip.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(handBitmap, 0, 0, handBitmap.getWidth(), handBitmap.getHeight(), flip, false);

    }
}
