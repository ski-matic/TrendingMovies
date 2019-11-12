package com.learning.trendingmovies.ui;

import android.os.Build;
import android.transition.Fade;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.learning.trendingmovies.R;
import com.learning.trendingmovies.data.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsPopupWindow extends PopupWindow {

    private static final int FADE_DURATION = 600;
    private View parent;
    private TextView description;
    private TextView title;
    private ImageView image;

    private MovieDetailsPopupWindow(View parent) {
        super(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        this.parent = parent;

        setOutsideTouchable(true);
        setElevation(10);
        setFocusable(true);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.popup_overlay, null);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        image = view.findViewById(R.id.image);
        setContentView(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setEnterTransition(new Fade().setDuration(FADE_DURATION));
            setExitTransition(new Fade().setDuration(FADE_DURATION));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void show(Movie movie) {
        title.setText(movie.getTitle());
        description.setText(movie.getOverview());
        Picasso.with(parent.getContext())
                .load(movie.getBackdropFullUrl())
                .placeholder(R.drawable.backdrop_loading)
                .into(image);

        showAtLocation(parent, Gravity.CENTER, 0, 0);
    }

    public static void show(View parent, Movie movie) {
        MovieDetailsPopupWindow popupWindow = new MovieDetailsPopupWindow(parent);
        popupWindow.show(movie);
    }
}
