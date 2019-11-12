package com.learning.trendingmovies.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.Fade;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.learning.trendingmovies.R;
import com.learning.trendingmovies.data.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsPopupWindow extends PopupWindow {

    private static final int FADE_DURATION = 600;
    private static final float DIM_AMOUNT = 0.7f;
    private ViewGroup parent;
    private TextView description;
    private TextView title;
    private ImageView image;

    private MovieDetailsPopupWindow(ViewGroup parent) {
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
                .placeholder(R.drawable.backdrop_loading30)
                .into(image);

        applyDim();

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                parent.getOverlay().clear();
            }
        });
        showAtLocation(parent, Gravity.CENTER, 0, 0);
    }

    private void applyDim() {
        Drawable dim = new ColorDrawable(Color.BLACK);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * DIM_AMOUNT));

        parent.getOverlay().add(dim);
    }

    /***
     * Create and show a PopupWindow displaying the details of a movie
     *
     * @param root Note that this needs to be a root of the window, so it can be dimmed
     * @param movie The movie to be displayed
     */
    public static void show(final ViewGroup root, final Movie movie) {
        MovieDetailsPopupWindow popupWindow = new MovieDetailsPopupWindow(root);
        popupWindow.show(movie);
    }
}
