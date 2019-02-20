package com.framgia.moviedb_35.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.screen.genres.GenresFragment;
import com.framgia.moviedb_35.screen.home.HomeFragment;
import com.framgia.moviedb_35.util.ActivityUtils;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addHomeFragment();
        setupBottomNavigationView();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = this.findViewById(R.id.navigation);
        if (bottomNavigationView != null) {
            setupBottomNavigationViewContent(bottomNavigationView);
        }
    }

    private void setupBottomNavigationViewContent(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                addHomeFragment();
                break;
            case R.id.navigation_category:
                addCategoryFragment();
                break;
            case R.id.navigation_settings:
                break;
            default:
                break;
        }
        return true;
    }

    private void addHomeFragment() {
        mCurrentFragment = HomeFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                mCurrentFragment, R.id.frame_fragments_container);
    }

    private void addCategoryFragment() {
        mCurrentFragment = GenresFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                mCurrentFragment, R.id.frame_fragments_container);
    }
}
