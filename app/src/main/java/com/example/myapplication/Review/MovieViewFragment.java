package com.example.myapplication.Review;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapplication.Adapter.ReviewAdapter;
import com.example.myapplication.Data.Helper.ImageHelper;
import com.example.myapplication.Data.Helper.MovieHelper;
import com.example.myapplication.Data.Helper.ReviewHelper;
import com.example.myapplication.Data.Image;
import com.example.myapplication.Data.Movie;
import com.example.myapplication.R;

public class MovieViewFragment extends Fragment {
    public MovieViewFragment() {
        super(R.layout.fragment_movie_view);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView title = view.findViewById(R.id.review_title);
        TextView year = view.findViewById(R.id.review_year);
        TextView synosis = view.findViewById(R.id.review_sypnosis);
        ImageView poster = view.findViewById(R.id.view_image);
        RatingBar rating = view.findViewById(R.id.view_rating);
        RecyclerView recyclerView = view.findViewById(R.id.view_list);
        AppCompatButton button = view.findViewById(R.id.view_rate);

        int ID = getActivity().getIntent().getIntExtra("movieID", 0);
        MovieHelper movieHelper = new MovieHelper(getContext());
        ImageHelper imageHelper = new ImageHelper(getContext());
        ReviewHelper reviewHelper= new ReviewHelper(getContext());

        Movie movie = movieHelper.get(ID);
        Image image = imageHelper.get(movie.getPosterID());


        title.setText(movie.getTitle());
        year.setText(movie.getYear());
        synosis.setText(movie.getSypnosis());
        rating.setRating(movie.getRating());

        Bitmap bm = BitmapFactory.decodeByteArray(image.getImg(),0,image.getImg().length);

        poster.setImageBitmap(bm);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ReviewAdapter adapter = new ReviewAdapter(reviewHelper.get(), getContext());
        recyclerView.setAdapter(adapter);
        button.setOnClickListener(v->{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_view_container, new MovieReviewFragment())
                    .commit();
        });
    }
}