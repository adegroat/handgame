package com.vivifile.handgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity {

    public static final String BANNER_AD_UNIT_ID = "ca-app-pub-2991576765220701/6852097073";

    private RenderView renderView;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        renderView = new RenderView(this);

        adView = new AdView(this);
        adView.setAdUnitId(BANNER_AD_UNIT_ID);
        adView.setAdSize(AdSize.SMART_BANNER);

        adView.setVisibility(View.VISIBLE);
        adView.loadAd(new AdRequest.Builder().build());

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams renderViewParams = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, 1);
        layout.addView(renderView, renderViewParams);

        LinearLayout.LayoutParams adParams = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, 0);
        layout.addView(adView, adParams);

        setContentView(layout);
    }

    @Override
    protected void onPause(){
        super.onPause();
        renderView.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        renderView.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        renderView.stop();
    }
}
