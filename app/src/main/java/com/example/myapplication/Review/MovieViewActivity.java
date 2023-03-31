package com.example.myapplication.Review;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;

public class MovieViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_view);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.movie_view_container, new MovieViewFragment());

    }
}