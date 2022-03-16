package com.example.movieandroidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private List<Slide> listslide;
    private ViewPager sliderpaper;
    private TabLayout indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderpaper = findViewById(R.id.slider_paper);
        indicator = findViewById(R.id.indicator);

        listslide = new ArrayList<>();
        listslide.add(new Slide(R.drawable.parasyte_lofi, "Slide Title \nmore text here"));
        listslide.add(new Slide(R.drawable.one_punch_man_lofi, "Slide Title \nmore text here"));
        listslide.add(new Slide(R.drawable.demon_slayer_lofi, "Slide Title \nmore text here"));
        listslide.add(new Slide(R.drawable.my_hero_academy_lofi, "Slide Title \nmore text here"));
        listslide.add(new Slide(R.drawable.fire_force_lofi, "Slide Title \nmore text here"));

        SliderPagerAdapter adapter = new SliderPagerAdapter(this, listslide) ;
        sliderpaper.setAdapter(adapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(), 2000, 4000);

        indicator.setupWithViewPager(sliderpaper, true);
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