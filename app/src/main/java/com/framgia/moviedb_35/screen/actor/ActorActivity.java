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
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.data.source.local.MovieLocalDataSource;
import com.framgia.moviedb_35.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_35.databinding.ActivityActorBinding;
import com.framgia.moviedb_35.screen.actor.fragment.InfoFragment;
import com.framgia.moviedb_35.screen.actor.fragment.MoviesFragment;

public class ActorActivity extends AppCompatActivity {
    private static final String BUNDLE_ACTOR_KEY = "BUNDLE_ACTOR_KEY";
    private static final String EXTRAS_ARGS = "com.framgia.moviedb_35.screen.detail.EXTRAS_ARGS";
    private static final String INFO = "Info";
    private static final String MOVIES = "Movies";
    private static final int NUMBER_ONE = 1;
    private static final int NUMBER_ZERO = 0;

    ActivityActorBinding mActorBinding;
    public static String mActorId;
    private ActorViewModel mActorViewModel;

    public static Intent getIntent(Context context, String actorId) {
        Intent intent = new Intent(context, ActorActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_ACTOR_KEY, actorId);
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActorBinding = DataBindingUtil.setContentView(this, R.layout.activity_actor);
        initData();
    }

    private void initData() {
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager_actor);

        Toolbar toolbar = findViewById(R.id.toolbar_actor);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        mActorId = getIntent().getExtras().getBundle(EXTRAS_ARGS).getString(BUNDLE_ACTOR_KEY);
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getInstance());
        mActorViewModel = new ActorViewModel(movieRepository, mActorId);
        mActorBinding.setViewModel(mActorViewModel);
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
            if (i == NUMBER_ONE) {
                return MoviesFragment.newInstance(mActorId);
            } else {
                return InfoFragment.newInstance(mActorId);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == NUMBER_ZERO) {
                return INFO;
            } else {
                return MOVIES;
            }
        }
    }
}
