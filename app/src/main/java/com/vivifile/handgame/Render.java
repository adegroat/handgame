package com.vivifile.handgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by alex on 1/3/17.
 */

public class Render {

    public static final int COLOR_BLUE = 0xFF5FCCED;

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

    public static void drawSmartText(Canvas can, Paint paint, float x, float y, String text) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        String newText = "";
        float offsetY = 0;

        if(bounds.width() >= RenderView.WIDTH - x - 10) {
            String words[] = text.split(" ");

            for(int i = 0; i < words.length; ++i) {
                newText += words[i] + " ";
                Rect newTextBounds = new Rect();
                String oneForward = newText + ((i + 1) >= words.length ? "" : words[i + 1]);
                paint.getTextBounds(oneForward, 0, oneForward.length(), newTextBounds);

                can.drawText(newText, x, y + offsetY, paint);
                if(newTextBounds.width() >= RenderView.WIDTH - x - 10) {
                    newText = "";
                    offsetY += bounds.height() + 15;
                }
            }

            return;
        }
        can.drawText(text, x, y, paint);

    }
}
