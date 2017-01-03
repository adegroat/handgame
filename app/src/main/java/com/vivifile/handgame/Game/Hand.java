package com.vivifile.handgame.Game;

import android.content.Context;
import android.content.res.Resources;
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

    public enum HandType {
        LEFT,
        RIGHT
    }

    public enum HandColor {
        RED,
        GREEN,
        BLUE,
        YELLOW
    }

    protected Bitmap redHandLeft, redHandRight;

    private Bitmap leftHand, rightHand;

    private HandType handType;
    private HandColor color;

    public Hand(HandType handType, Context context){
        this.handType = handType;
        Matrix flip = new Matrix();
        flip.preScale(-1.0f, 1.0f);
        redHandRight = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_hand);
        redHandLeft = Bitmap.createBitmap(redHandRight, 0, 0, redHandRight.getWidth(), redHandRight.getHeight(), flip, false);
    }

    public void draw(Canvas can) {
        int width = RenderView.WIDTH / 2;
        can.drawBitmap(redHandLeft, (width - redHandLeft.getWidth()) / 2, RenderView.WIDTH + 200, null);
        can.drawBitmap(redHandRight, width + ((width - redHandRight.getWidth()) / 2), RenderView.WIDTH + 200, null);

    }
}
