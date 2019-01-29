package com.framgia.moviedb_35.screen.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int DELAY_MILIS = 1500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(IntroductionActivity.getIntent(SplashActivity.this));
                finish();
            }
        }, DELAY_MILIS);
    }
}
