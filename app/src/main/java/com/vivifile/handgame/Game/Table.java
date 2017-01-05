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
    public static final int PLAYER_TURN_TIME = 800;
    public static final int COMPUTER_TURN_TIME = 600;

    private Game game;
    private Hand[] hands;
    private int numPlayers;
    private int currentTurn;
    private Player player;
    private boolean clockwise = true;
    private Paint paint;
    private long lastTapTime, turnTimeLimit;
    private boolean didTap = false;

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
        currentTurn = 5;
        paint = new Paint();
        lastTapTime = Game.getTime() + 3000;
    }

    public void draw(Canvas can) {
        paint.setARGB(255, 144, 150, 120);
        can.drawCircle(TABLE_RADIUS, TABLE_RADIUS, TABLE_RADIUS, paint);

        for(int i = 0; i < hands.length; i++) {
            int radius = TABLE_RADIUS - (hands[i].getHeight() / 2) - 40;
            double angleRad = i * (Math.PI / numPlayers);
            float x = radius * (float) Math.cos(angleRad);
            float y = radius * (float) Math.sin(angleRad);
            float handAngle = (i * 45) - 90;
            hands[i].draw(can, TABLE_RADIUS + x, TABLE_RADIUS + y, handAngle);
        }

        player.drawHands(can);
    }

    public void update(float delta){
        for(Hand hand : hands) hand.update(delta);

        if(hands[player.getPlayerLeft()].isOut() && hands[player.getPlayerRight()].isOut()) {
            game.gameOver();
        }

        if(isPlayerTurn()) {
            if(player.getTapCount() >= 2){
                player.resetTapCount();
                nextTurn();
            }
        }
        else {
            boolean shouldDoubleTap = new Random().nextInt(101) > 75; // 25% chance of computer double tapping
            tap(shouldDoubleTap);
        }

        turnTimeLimit = isPlayerTurn() ? PLAYER_TURN_TIME : COMPUTER_TURN_TIME;
        Log.d("TAG", "update: " + (Game.getTime() - turnTimeLimit) + "\t\t" + Game.getTime());
        if(Game.getTime() - lastTapTime > turnTimeLimit) {
            if(isPlayerTurn() && player.getTapCount() == 0) {
                hands[currentTurn].setOut(true);
            }
            player.resetTapCount();
            nextTurn();
        }

        if(currentTurn >= 2 * numPlayers) currentTurn = 0;
        if(currentTurn < 0) currentTurn = 2 * numPlayers - 1;
        if(hands[currentTurn].isOut()) nextTurn();
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();

        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_UP: {
                if(player.isCollidedLeft(x, y) || player.isCollidedRight(x, y)) {
                    if(isPlayerTurn()) {
                        player.incTapCount();
                        tap(false);
                        return true;
                    }
                    int deadHand = player.isCollidedLeft(x, y) ? player.getPlayerLeft() : player.getPlayerRight();
                    hands[deadHand].setOut(true);
                }
            }
            break;
        }

        return true;
    }

    private void tap(boolean isDoubleTap){
        if(didTap && !isPlayerTurn()) return;

        if(currentTurn >= 2 * numPlayers) currentTurn = 0;
        if(currentTurn < 0) currentTurn = 2 * numPlayers - 1;

        didTap = true;

        boolean shouldDoubleTap = isDoubleTap || player.getTapCount() >= 2;

        if(shouldDoubleTap) clockwise = !clockwise;

        hands[currentTurn].tap(isDoubleTap);
    }

    private void nextTurn(){
        lastTapTime = Game.getTime();

        didTap = false;

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
