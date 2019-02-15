package com.framgia.moviedb_35.util.binding;

import android.databinding.BindingAdapter;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Actor;
import com.framgia.moviedb_35.data.model.Company;
import com.framgia.moviedb_35.data.model.Genre;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.screen.actor.ActorAdapter;
import com.framgia.moviedb_35.screen.detail.adapter.CharacterAdapter;
import com.framgia.moviedb_35.screen.detail.adapter.ProductionAdapter;
import com.framgia.moviedb_35.screen.genres.GenresAdapter;
import com.framgia.moviedb_35.screen.home.CategoriesAdapter;
import com.framgia.moviedb_35.util.StringUtils;

import java.util.List;

public class BindingUtils {
    private static final int IMAGE_SIZE_200 = 1280;
    private static final int ONE_HUNDRED = 100;
    private static final int TWO_HUNDRED = 200;
    private static final int ONE = 1;
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private static int mMaxScrollSize;
    private static boolean mIsAvatarShown = true;

    @BindingAdapter({"app:bindMovies"})
    public static void setMoviesForRecyclerView(RecyclerView recyclerView,
                                                List<Movie> movies) {
        CategoriesAdapter adapter = (CategoriesAdapter) recyclerView.getAdapter();
        if (adapter != null && movies != null) {
            adapter.replaceData(movies);
        }
    }

    @BindingAdapter({"app:bindMoviesForActor"})
    public static void setMoviesForActorScreen(RecyclerView recyclerView,
                                               List<Movie> movies) {
        ActorAdapter adapter = (ActorAdapter) recyclerView.getAdapter();
        if (adapter != null && movies != null) {
            adapter.replaceData(movies);
        }
    }

    @BindingAdapter({"app:bindActors"})
    public static void setActorsForRecyclerView(RecyclerView recyclerView,
                                                List<Actor> actors) {
        CharacterAdapter adapter = (CharacterAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.replaceData(actors);
        }
    }

    @BindingAdapter({"app:bindProductions"})
    public static void setCompanyForRecyclerView(RecyclerView recyclerView,
                                                 List<Company> companies) {
        ProductionAdapter adapter = (ProductionAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.replaceData(companies);
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView imageView, String url) {
        if (url == null || url.isEmpty()) {
            imageView.setImageResource(R.drawable.ic_loading);
        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_loading);
        requestOptions.error(R.drawable.ic_loading);
        String imageLink = StringUtils.getImageUrl(IMAGE_SIZE_200, url);
        Glide.with(imageView.getContext())
                .load(imageLink)
                .apply(requestOptions)
                .into(imageView);
    }

    @BindingAdapter("app:circleImage")
    public static void setCircleImage(ImageView imageView, String url) {
        if (url == null || url.isEmpty()) {
            imageView.setImageResource(R.drawable.ic_loading);
        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_loading);
        requestOptions.error(R.drawable.ic_loading);
        requestOptions.circleCropTransform();
        requestOptions.circleCrop();
        String imageLink = StringUtils.getImageUrl(IMAGE_SIZE_200, url);
        Glide.with(imageView.getContext())
                .load(imageLink)
                .apply(requestOptions)
                .into(imageView);
    }


    @BindingAdapter("app:bindGenres")
    public static void setGenresForRecyclerView(RecyclerView recyclerView, List<Genre> genres) {
        GenresAdapter adapter = (GenresAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.replaceData(genres);
        }
    }

    @BindingAdapter("bindImageGenre")
    public static void setImageGenre(ImageView imageView, int position) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_loading);
        requestOptions.error(R.drawable.ic_loading);
        Glide.with(imageView.getContext())
                .load(position)
                .apply(requestOptions)
                .into(imageView);
    }

    @BindingAdapter("app:animationImageView")
    public static void setAnimation(ImageView imageView, AppBarLayout appBarLayout) {
        mMaxScrollSize = appBarLayout.getTotalScrollRange();
        appBarLayout.addOnOffsetChangedListener((appBarLayout1, i) -> {
            if (mMaxScrollSize == 0)
                mMaxScrollSize = appBarLayout.getTotalScrollRange();

            int percentage = (Math.abs(i)) * ONE_HUNDRED / mMaxScrollSize;

            if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
                mIsAvatarShown = false;
                imageView.animate()
                        .scaleY(0).scaleX(0)
                        .setDuration(TWO_HUNDRED)
                        .start();
            }

            if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
                mIsAvatarShown = true;
                imageView.animate()
                        .scaleY(ONE).scaleX(ONE)
                        .start();
            }
        });
    }
}
