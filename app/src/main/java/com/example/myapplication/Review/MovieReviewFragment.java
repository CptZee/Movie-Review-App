package com.example.myapplication.Review;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Data.Helper.ImageHelper;
import com.example.myapplication.Data.Helper.MovieHelper;
import com.example.myapplication.Data.Helper.ReviewHelper;
import com.example.myapplication.Data.Image;
import com.example.myapplication.Data.Movie;
import com.example.myapplication.Data.Review;
import com.example.myapplication.R;

public class MovieReviewFragment extends Fragment {
    public MovieReviewFragment() {
        super(R.layout.fragment_movie_review);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView title = view.findViewById(R.id.review_title);
        TextView year = view.findViewById(R.id.review_year);
        TextView synosis = view.findViewById(R.id.review_sypnosis);
        EditText comment = view.findViewById(R.id.review_comment);
        ImageView poster = view.findViewById(R.id.view_image);
        RatingBar rating = view.findViewById(R.id.view_rating);
        Button button = view.findViewById(R.id.review_submit_button);

        SharedPreferences preferences = getActivity().getSharedPreferences("Filmograph", Context.MODE_PRIVATE);
        int ID = getActivity().getIntent().getIntExtra("movieID", 0);
        MovieHelper movieHelper = new MovieHelper(getContext());
        ImageHelper imageHelper = new ImageHelper(getContext());
        ReviewHelper reviewHelper = new ReviewHelper(getContext());

        Movie movie = movieHelper.get(ID);
        Image image = imageHelper.get(movie.getPosterID());


        title.setText(movie.getTitle());
        year.setText(movie.getYear());
        synosis.setText(movie.getSypnosis());

        Bitmap bm = BitmapFactory.decodeByteArray(image.getImg(),0,image.getImg().length);

        poster.setImageBitmap(bm);

        button.setOnClickListener(v ->{
            Review review = new Review();
            review.setMovieID(movie.getID());
            review.setReview(rating.getRating());
            review.setComment(comment.getText().toString());
            review.setReviewerID(preferences.getInt("loggedUserID", 0));
            reviewHelper.insert(review);
            Toast.makeText(getContext(), "Review submitted!", Toast.LENGTH_SHORT);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_view_container, new MovieViewFragment())
                    .commit();
        });
    }
}