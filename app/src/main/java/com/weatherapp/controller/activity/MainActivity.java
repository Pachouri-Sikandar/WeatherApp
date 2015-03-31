package com.weatherapp.controller.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.weatherapp.R;
import com.weatherapp.controller.fragment.MainFragment;
import com.weatherapp.util.CommonUtils;

/**
 * Created by ankit on 30/3/15.
 */

public class MainActivity extends FragmentActivity {

    private boolean doubleBackPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_frame_layout);

        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        doubleBackPressed = false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackPressed)
        {
            super.onBackPressed();
            return;
        }
        this.doubleBackPressed = true;
        CommonUtils.showToast("Click \"Cancel\" again to exit");
    }
}
