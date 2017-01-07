package com.vivifile.handgame.Game;

import android.graphics.Canvas;

import com.vivifile.handgame.RenderView;

/**
 * Created by alex on 1/4/17.
 */

public class Player {

    public static final int TAP_PADDING = 25;

    private int playerLeft, playerRight;
    private int tapCount;
    private Hand leftHand, rightHand;
    private float leftHandX, leftHandY, rightHandX, rightHandY;

    public Player(Hand rightHand, Hand leftHand){
        this.rightHand = rightHand;
        this.leftHand = leftHand;
        playerRight = 0;
        playerLeft = 3;
        tapCount = 0;
        float width = RenderView.WIDTH / 2;
        leftHandX =(width - leftHand.getWidth()) / 2;
        leftHandY = 2 * Table.TABLE_RADIUS + 100;
        rightHandX = width + ((width - rightHand.getWidth()) / 2);
        rightHandY = leftHandY;
    }

    public void drawHands(Canvas can) {
        can.drawBitmap(leftHand.getHandBitmap(), leftHandX, leftHandY, null);
        can.drawBitmap(rightHand.getHandBitmap(), rightHandX, rightHandY, null);
    }

    public int getPlayerLeft(){
        return playerLeft;
    }

    public int getPlayerRight(){
        return playerRight;
    }

    public void resetTapCount(){
        tapCount = 0;
    }

    public int getTapCount(){
        return tapCount;
    }

    public void incTapCount(){
        tapCount++;
    }

    public boolean isCollidedLeft(float x, float y) {
        return x >= leftHandX - TAP_PADDING && x <= leftHandX + leftHand.getWidth() + TAP_PADDING && y >= leftHandY - TAP_PADDING && y <= leftHandY + leftHand.getHeight() + TAP_PADDING;
    }

    public boolean isCollidedRight(float x, float y) {
        return x >= rightHandX - TAP_PADDING && x <= rightHandX + rightHand.getWidth() + TAP_PADDING && y >= rightHandY - TAP_PADDING && y <= rightHandY + rightHand.getHeight() + TAP_PADDING;
    }
}
