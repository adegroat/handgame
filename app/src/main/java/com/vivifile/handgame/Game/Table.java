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
    public static final int PLAYER_TURN_TIME = 1000;
    public static final int COMPUTER_TURN_TIME = 600;

    private Game game;
    private Hand[] hands;
    private int numPlayers;
    private int currentTurn;
    private Player player;
    private boolean clockwise = true;
    private Paint paint;
    private long lastTapTime;
    private int turnTimeLimit = COMPUTER_TURN_TIME;

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
        player = new Player(hands[0], hands[3]);
        currentTurn = 4;
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

        player.drawHands(can);
    }

    public void update(float delta){
        for(Hand hand : hands) hand.update(delta);

        if(currentTurn >= 2 * numPlayers) currentTurn = 0;
        if(currentTurn < 0) currentTurn = 2 * numPlayers - 1;

        if(hands[currentTurn].isOut()) tap(false);
        turnTimeLimit = isPlayerTurn() ? PLAYER_TURN_TIME : COMPUTER_TURN_TIME;


        long dt = System.currentTimeMillis() - lastTapTime;
        if(dt > turnTimeLimit) {
            if(isPlayerTurn()) {
                hands[currentTurn].setOut(true);
                tap(false);
                return;
            }

            boolean shouldDoubleTap = new Random().nextInt(101) > 75;
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
                boolean leftTap = x < RenderView.WIDTH / 2 && currentTurn == player.getPlayerLeft();
                boolean rightTap = x > RenderView.WIDTH / 2 && currentTurn == player.getPlayerRight();
                boolean validTap = leftTap || rightTap;
                if(!validTap) {
                    int deadHand = x > RenderView.WIDTH / 2 ? player.getPlayerRight() : player.getPlayerLeft();
                    hands[deadHand].setOut(true);
                }

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

        if(clockwise) {
            currentTurn++;
            return;
        }
        currentTurn--;
    }

    private boolean isPlayerTurn(){
        return currentTurn == player.getPlayerLeft() || currentTurn == player.getPlayerRight();
    }
}
