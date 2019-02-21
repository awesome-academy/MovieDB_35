package com.framgia.moviedb_35.screen.actor;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.databinding.ActivityActorBinding;

public class ActorActivity extends AppCompatActivity {
    ActivityActorBinding mActorBinding;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ActorActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActorBinding = DataBindingUtil.setContentView(this, R.layout.activity_actor);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.viewpager_actor);

        Toolbar toolbar = findViewById(R.id.toolbar_actor);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    private static class TabsAdapter extends FragmentPagerAdapter {
        private static final int TAB_COUNT = 2;

        TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public Fragment getItem(int i) {
            return MoviesFragment.newInstance();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return String.valueOf(position);
        }
    }
}
