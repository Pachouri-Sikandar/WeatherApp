package com.weatherapp.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.weatherapp.R;

/**
 * Created by ankit on 5/5/17.
 */
public class SplashActivity extends FragmentActivity {

    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashTimer();
    }

    private void splashTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent openMainActivity = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(openMainActivity);

            }
        }, SPLASH_TIME_OUT);
    }
}
