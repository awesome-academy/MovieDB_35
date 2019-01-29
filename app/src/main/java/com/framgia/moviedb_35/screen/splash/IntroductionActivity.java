package com.framgia.moviedb_35.screen.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.screen.MainActivity;

public class IntroductionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        mImageView = findViewById(R.id.image_button);
        mImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(MainActivity.getIntent(IntroductionActivity.this));
        finish();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, IntroductionActivity.class);
    }
}
