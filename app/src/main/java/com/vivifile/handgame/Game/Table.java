package com.vivifile.handgame.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.vivifile.handgame.Render;
import com.vivifile.handgame.RenderView;

import java.util.Random;

/**
 * Created by alex on 1/3/17.
 */

public class Table {

    public static final int TABLE_RADIUS = RenderView.WIDTH / 2;
    public static final int TURN_TIME_LIMIT = 600; // Max numberof miliseconds that a turn can take
    public static final int DOUBLE_TAP_TIME_LIMIT = 300;

    private Game game;
    private Hand[] hands;
    private int numPlayers;
    private int currentTurn, playerTurn;
    private boolean clockwise = true;
    private Paint paint;
    private long lastTapTime, playerTapStart;

    public Table(Game game, int numPlayers){
        this.game = game;
        this.numPlayers = numPlayers;
        hands = new Hand[numPlayers * 2];
        Context context = game.getContext();
        hands[0] = new Hand(Hand.RED_HAND, true, context);
        hands[1] = new Hand(Hand.GREEN_HAND, false, context);
        hands[2] = new Hand(Hand.YELLOW_HAND, true, context);
        hands[3] = new Hand(Hand.RED_HAND, false, context);
        hands[4] = new Hand(Hand.BLUE_HAND, true, context);
        hands[5] = new Hand(Hand.YELLOW_HAND, false, context);
        hands[6] = new Hand(Hand.GREEN_HAND, true, context);
        hands[7] = new Hand(Hand.BLUE_HAND, false, context);
        currentTurn = 4;
        playerTurn = 0;
        paint = new Paint();
    }

    public void draw(Canvas can) {
        paint.setARGB(255, 144, 150, 120);
        can.drawCircle(TABLE_RADIUS, TABLE_RADIUS, TABLE_RADIUS, paint);

        for(int i = 0; i < hands.length; i++) {
            int radius = TABLE_RADIUS - (hands[i].getHeight() / 2) - 40;
            double angleRad = i * (Math.PI / 4);
            float x = radius * (float) Math.cos(angleRad);
            float y = radius * (float) Math.sin(angleRad);
            float handAngle = (i * 45) - 90;
            hands[i].draw(can, TABLE_RADIUS + x, TABLE_RADIUS + y, handAngle);
        }
    }

    public void update(float delta){
        for(Hand hand : hands) hand.update(delta);

        if(currentTurn >= 2 * numPlayers) currentTurn = 0;
        if(currentTurn < 0) currentTurn = 2 * numPlayers - 1;

        if(hands[currentTurn].isOut()) tap(false);

        long dt = System.currentTimeMillis() - lastTapTime;
        if(dt > TURN_TIME_LIMIT) {
            if(isPlayerTurn()) {
                hands[currentTurn].setOut(true);
                tap(false);
                return;
            }

            boolean shouldDoubleTap = new Random().nextInt(101) > 80;
            tap(shouldDoubleTap);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();

        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_UP: {
//                if (isPlayerTurn()) playerTapStart = System.currentTimeMillis();
//                if (System.currentTimeMillis() - playerTapStart < DOUBLE_TAP_TIME_LIMIT) {
//                    tap(true);
//                    playerTapStart = System.currentTimeMillis();
//                } else tap(false);

                tap(false);
            }
            break;
        }

        return true;
    }

    private void tap(boolean isDoubleTap){
        if(currentTurn >= 2 * numPlayers) currentTurn = 0;
        if(currentTurn < 0) currentTurn = 2 * numPlayers - 1;

        lastTapTime = System.currentTimeMillis();

        if(isDoubleTap) clockwise = !clockwise;

        hands[currentTurn].tap(isDoubleTap);

//        if(clockwise) {
//            currentTurn++;
//            return;
//        }
//        currentTurn--;
    }

    private boolean isPlayerTurn(){
        return currentTurn == 0 || currentTurn == 3;
    }
}
