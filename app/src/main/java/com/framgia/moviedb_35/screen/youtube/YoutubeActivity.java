package com.framgia.moviedb_35.screen.youtube;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.framgia.moviedb_35.R;

import static com.framgia.moviedb_35.screen.home.HomeViewModel.BUNDLE_KEY;

public class YoutubeActivity extends AppCompatActivity {
    private static final String EXTRAS_ARGS = "com.framgia.moviedb_35.screen.detail.EXTRAS_ARGS";
    private String mVideoId;
    private YouTubeVideoFragment mYouTubeVideoFragment;

    public static Intent getIntent(Context context, String youtubeId) {
        Intent intent = new Intent(context, YoutubeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, youtubeId);
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        initFragmentYoutube();
    }

    public void initFragmentYoutube() {
        mVideoId = getIntent().getBundleExtra(EXTRAS_ARGS).getString(BUNDLE_KEY);
        mYouTubeVideoFragment =
                (YouTubeVideoFragment) getFragmentManager().findFragmentById(R.id.player);
        mYouTubeVideoFragment.setVideoId(String.valueOf(mVideoId));
        mYouTubeVideoFragment.playVideo();
    }
}
