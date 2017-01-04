package com.vivifile.handgame.Game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.vivifile.handgame.GameLoop;
import com.vivifile.handgame.Gui.PauseMenu;
import com.vivifile.handgame.RenderView;

import java.util.Random;

/**
 * Created by alex on 1/2/17.
 */

public class Game {

    private static final int TABLE_RADIUS = RenderView.WIDTH / 2;
    private static final int NUM_HANDS = 8;

    private GameLoop gl;
    private boolean isPaused = false;

    private Hand[] hands = new Hand[NUM_HANDS];
    private int currentTurn = 0;
    private int player;

    public Game(GameLoop gl) {
        this.gl = gl;
        setupHands();
        player = new Random().nextInt(NUM_HANDS);
    }

    private void setupHands(){
        hands[0] = new Hand(Hand.RED_HAND, true, gl.getContext());
        hands[1] = new Hand(Hand.GREEN_HAND, false, gl.getContext());
        hands[2] = new Hand(Hand.YELLOW_HAND, true, gl.getContext());
        hands[3] = new Hand(Hand.RED_HAND, false, gl.getContext());
        hands[4] = new Hand(Hand.BLUE_HAND, true, gl.getContext());
        hands[5] = new Hand(Hand.YELLOW_HAND, false, gl.getContext());
        hands[6] = new Hand(Hand.GREEN_HAND, true, gl.getContext());
        hands[7] = new Hand(Hand.BLUE_HAND, false, gl.getContext());
    }

    public void draw(Canvas can) {
        Paint p = new Paint();
        p.setARGB(255, 144, 150, 120);
        can.drawCircle(TABLE_RADIUS, TABLE_RADIUS + 60, TABLE_RADIUS, p);

        for(int i = 0; i < hands.length; i++) {
            int radius = TABLE_RADIUS - (hands[i].getHeight() / 2) - 40;
            double angleRad = i * (Math.PI / 4);
            float x = radius * (float) Math.cos(angleRad);
            float y = radius * (float) Math.sin(angleRad);
            float handAngle = (i * 45) - 90;
            hands[i].draw(can, TABLE_RADIUS + x, TABLE_RADIUS + 60 + y, handAngle);
        }

        int width = RenderView.WIDTH / 2;
        Bitmap playerLeftBitmap = hands[player].getHandBitmap();
        Bitmap playerRightBitmap = hands[player].getFlippedHandBitmap();
        can.drawBitmap(playerLeftBitmap, (width - playerLeftBitmap.getWidth()) / 2, RenderView.WIDTH + 200, null);
        can.drawBitmap(playerRightBitmap, width + ((width - playerRightBitmap.getWidth()) / 2), RenderView.WIDTH + 200, null);
    }

    public void update(float spf) {

    }

    public void pause(){
        isPaused = true;
        gl.menus.push(new PauseMenu(gl));
    }

    public void resume(){
        isPaused = false;
        gl.menus.pop();
    }
}
