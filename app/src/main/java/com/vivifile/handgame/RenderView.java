package com.vivifile.handgame;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by alex on 1/1/17.
 */

public class RenderView extends SurfaceView implements SurfaceHolder.Callback {

    public static final String TAG = "RenderView";

    public static int WIDTH, HEIGHT;

    private SurfaceHolder surfaceHolder;
    private GameLoop gameLoop;
    private DisplayMetrics dm;

    public RenderView(Context context){
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        dm = context.getResources().getDisplayMetrics();
        WIDTH = dm.widthPixels;
        HEIGHT = dm.heightPixels;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated");
        gameLoop = new GameLoop(surfaceHolder, getContext());
        gameLoop.doStart();
        HEIGHT = getHeight();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed");
        gameLoop.doStop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gameLoop.onTouchEvent(event);
    }

    public void stop(){
        gameLoop.doStop();
        gameLoop = null;
    }
}
