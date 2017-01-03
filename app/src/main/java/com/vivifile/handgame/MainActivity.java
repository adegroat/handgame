package com.vivifile.handgame;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private RenderView renderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderView = new RenderView(getApplicationContext());
        setContentView(renderView);
        renderView.start();

    }
}
