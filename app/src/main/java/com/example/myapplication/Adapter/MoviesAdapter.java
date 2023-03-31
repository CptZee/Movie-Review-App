package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Data.Helper.ImageHelper;
import com.example.myapplication.Data.Image;
import com.example.myapplication.Data.Movie;
import com.example.myapplication.R;
import com.example.myapplication.Review.MovieViewActivity;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> list;
    private Context context;

    public MoviesAdapter(List<Movie> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_card_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ImageHelper helper = new ImageHelper(context);
        Movie data = list.get(position);
        viewHolder.getTitle().setText(data.getTitle());
        viewHolder.getYear().setText(data.getYear());
        viewHolder.getRating().setRating(data.getRating());
        Image img = helper.get(data.getPosterID());
        Bitmap bm = BitmapFactory.decodeByteArray(img.getImg(), 0, img.getImg().length);
        viewHolder.getPoster().setImageBitmap(bm);
        viewHolder.getLayout().setOnClickListener(v->{
            Intent i = new Intent(context, MovieViewActivity.class);
            i.putExtra("movieID", data.getID());
            context.startActivity(i);
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, year;
        private RatingBar rating;
        private ImageView poster;
        private ConstraintLayout layout;
        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.card_title);
            year = view.findViewById(R.id.card_year);
            rating = view.findViewById(R.id.card_rating);
            poster = view.findViewById(R.id.card_image);
            layout = view.findViewById(R.id.card_movie);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getYear() {
            return year;
        }

        public RatingBar getRating() {
            return rating;
        }

        public ImageView getPoster() {
            return poster;
        }

        public ConstraintLayout getLayout() {
            return layout;
        }
    }
}
