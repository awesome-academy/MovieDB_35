package com.framgia.moviedb_35.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.screen.home.HomeFragment;
import com.framgia.moviedb_35.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HomeFragment homeFragment = HomeFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                homeFragment, R.id.frame_fragments_container);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
}
