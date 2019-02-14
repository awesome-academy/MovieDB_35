package com.framgia.moviedb_35.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Movie;

public class DetailActivity extends AppCompatActivity {
    public static Intent getIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
