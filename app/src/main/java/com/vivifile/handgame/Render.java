package com.vivifile.handgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by alex on 1/3/17.
 */

public class Render {

    public static Bitmap flipBitmap(Bitmap bitmap) {
        Matrix flip = new Matrix();
        flip.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), flip, false);
    }

    public static void drawCenterText(Canvas can, Paint paint, float centerX, float centerY, String text) {
        Rect textBounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBounds);
        can.drawText(text, centerX - (textBounds.width() / 2), centerY + (textBounds.height() / 2), paint);
    }
}
