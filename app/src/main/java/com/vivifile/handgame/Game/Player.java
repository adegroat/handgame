package com.vivifile.handgame.Game;

import android.graphics.Canvas;

import com.vivifile.handgame.RenderView;

/**
 * Created by alex on 1/4/17.
 */

public class Player {

    private long tapTime;
    private int playerLeft, playerRight;
    private boolean leftHandDead, rightHandDead;
    private int tapCount;
    private Hand leftHand, rightHand;

    public Player(Hand rightHand, Hand leftHand){
        this.rightHand = rightHand;
        this.leftHand = leftHand;
        playerRight = 0;
        playerLeft = 3;
        tapCount = 0;
        leftHandDead = false;
        rightHandDead = false;
    }

    public void drawHands(Canvas can) {
        float width = RenderView.WIDTH / 2;
        can.drawBitmap(leftHand.getHandBitmap(), (width - leftHand.getHandBitmap().getWidth()) / 2, 2 * Table.TABLE_RADIUS + 100, null);
        can.drawBitmap(rightHand.getHandBitmap(), width + ((width - rightHand.getHandBitmap().getWidth()) / 2), 2 * Table.TABLE_RADIUS + 100, null);
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

    public void setTapTime(){
        tapTime = System.currentTimeMillis();
    }

    public long lastTapTime(){
        return tapTime;
    }
}
