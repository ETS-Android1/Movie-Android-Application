package com.example.movieandroidapplication;

import android.widget.ImageView;

public interface MovieItemClickListener {
    void onMovieClick(Movie movie, ImageView movieImageView); //We will need the ImageView to make the shared animation between the two activity
}
