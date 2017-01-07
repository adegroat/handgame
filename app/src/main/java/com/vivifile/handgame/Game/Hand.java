package com.vivifile.handgame.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;

import com.vivifile.handgame.R;
import com.vivifile.handgame.Render;
import com.vivifile.handgame.RenderView;

/**
 * Created by alex on 1/2/17.
 */

public class Hand {

    public static final int RED_HAND = R.drawable.red_hand;
    public static final int GREEN_HAND = R.drawable.green_hand;
    public static final int PURPLE_HAND = R.drawable.purple_hand;
    public static final int YELLOW_HAND = R.drawable.yellow_hand;

    private int handColor;
    private boolean isRightHand;
    private boolean isOut;
    private Bitmap handBitmap, handBitmapCopy;
    private boolean isTapping, isDoubleTap;
    private long tapStart, outTime;
    private static MediaPlayer tapPlayer, doubleTapPlayer;

    public Hand(int handColor, boolean isRightHand, Context context){
        this.handColor = handColor;
        this.isRightHand = isRightHand;
        isOut = false;
        isTapping = false;
        loadBitmaps(context);
        tapPlayer = MediaPlayer.create(context, R.raw.tap);
        doubleTapPlayer = MediaPlayer.create(context, R.raw.double_tap);
    }

    private void loadBitmaps(Context context){
        handBitmap = BitmapFactory.decodeResource(context.getResources(), handColor);

        if(!isRightHand){
            Matrix flip = new Matrix();
            flip.preScale(-1.0f, 1.0f);
            handBitmap = Bitmap.createBitmap(handBitmap, 0, 0, handBitmap.getWidth(), handBitmap.getHeight(), flip, false);
        }
        handBitmapCopy = handBitmap;
    }

    public void draw(Canvas can, float x, float y, float rotAngle) {

        if(!isOut) {
            can.rotate(rotAngle, x, y);
            can.drawBitmap(handBitmap, x - (handBitmap.getWidth() / 2), y - (handBitmap.getHeight() / 2), null);
            can.rotate(-rotAngle, x, y);
        }
    }

    public void tap(boolean isDoubleTap){
        if(isOut) return;
        this.isDoubleTap = isDoubleTap;
        tapStart = Game.getTime();
        isTapping = true;
    }

    public void update(float delta) {
        if(isOut) return;

        if(isTapping) {
            Bitmap scaledHand = Bitmap.createScaledBitmap(handBitmapCopy, handBitmapCopy.getWidth() + 80, handBitmapCopy.getHeight() + 80, false);
            handBitmap = scaledHand;

            long dt = Game.getTime() - tapStart;

            if(dt > 200) {
                handBitmap = handBitmapCopy;

                if(isDoubleTap){
                    doubleTapPlayer.start();
                    if(dt > 350) {
                        handBitmap = scaledHand;
                        if(dt > 450) {
                            handBitmap = handBitmapCopy;
                            isTapping = false;
                        }
                    }
                } else {
                    tapPlayer.start();
                    isTapping = false;
                }
            }
        }
    }

    public void setOut(boolean isOut) {
        this.isOut = isOut;
        outTime = Game.getTime();
    }

    public boolean isOut(){
        return isOut;
    }

    public int getWidth(){
        return handBitmap.getWidth();
    }

    public int getHeight(){
        return handBitmap.getHeight();
    }

    public Bitmap getHandBitmap() {
        return handBitmap;
    }
}
