package com.example.movieandroidapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.movieandroidapplication.models.Movie;
import com.example.movieandroidapplication.adapters.MovieAdapter;
import com.example.movieandroidapplication.adapters.MovieItemClickListener;
import com.example.movieandroidapplication.R;
import com.example.movieandroidapplication.models.Slide;
import com.example.movieandroidapplication.adapters.SliderPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<Slide> listslide;
    private ViewPager sliderpaper;
    private TabLayout indicator;
    private RecyclerView movieRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Curiosity");


        sliderpaper = findViewById(R.id.slider_paper);
        indicator = findViewById(R.id.indicator);
        movieRV = findViewById((R.id.rv_movie));

        listslide = new ArrayList<>();
        listslide.add(new Slide(R.drawable.parasyte_lofi, "Slide Title \nmore text here"));
        listslide.add(new Slide(R.drawable.one_punch_man_lofi, "Slide Title \nmore text here"));
        listslide.add(new Slide(R.drawable.demon_slayer_lofi, "Slide Title \nmore text here"));
        listslide.add(new Slide(R.drawable.my_hero_academy_lofi, "Slide Title \nmore text here"));
        listslide.add(new Slide(R.drawable.fire_force_lofi, "Slide Title \nmore text here"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(this, listslide) ;
        sliderpaper.setAdapter(adapter);
        //Set up time for changing the theme
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(), 2000, 4000);
        indicator.setupWithViewPager(sliderpaper, true);

        //Recyclerview Setup
        List<Movie> lstMovie = new ArrayList<>();
        lstMovie.add(new Movie("Demon Slayer", R.drawable.demon_slayer, R.drawable.demon_slayer_lofi));
        lstMovie.add(new Movie("Black Clover", R.drawable.black_clover));
        lstMovie.add(new Movie("Dr Stone", R.drawable.dr_stone));
        lstMovie.add(new Movie("Fairy Tail", R.drawable.fairy_tail));
        lstMovie.add(new Movie("Fire Force", R.drawable.fire_force, R.drawable.fire_force_lofi));
        lstMovie.add(new Movie("Sword Oratoria", R.drawable.is_it_wrong_to_try_to_pick_up_girls_in_dungeon));
        lstMovie.add(new Movie("Jujutsu Kaisen", R.drawable.jujutsu_kaisen, R.drawable.jujutsu_kaisen_lofi));
        lstMovie.add(new Movie("My Hero Academia", R.drawable.my_hero_academia, R.drawable.my_hero_academy_lofi));
        lstMovie.add(new Movie("One Punch Man", R.drawable.one_punch_man, R.drawable.one_punch_man_lofi));
        lstMovie.add(new Movie("Parasyte", R.drawable.parasyte, R.drawable.parasyte_lofi));
        lstMovie.add(new Movie("Stein Gate", R.drawable.stein_gate));

        MovieAdapter movieAdapter = new MovieAdapter(this, lstMovie, this);
        movieRV.setAdapter(movieAdapter);
        movieRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        //Here we send movie information to detail activity
        //also we ll create the transition animation between the two activity

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhoto());

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, movieImageView, "sharedName");

        startActivity(intent, options.toBundle());

        Toast.makeText(this, "item clicked" + movie.getTitle(), Toast.LENGTH_LONG).show();
    }

    class SliderTimer extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    if(sliderpaper.getCurrentItem() < (listslide.size() - 1)){
                        sliderpaper.setCurrentItem(sliderpaper.getCurrentItem()+1);
                    }
                    else
                        sliderpaper.setCurrentItem(0);
                }
            });
        }
    }
}