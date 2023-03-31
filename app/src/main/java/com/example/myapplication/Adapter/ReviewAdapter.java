package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Data.Account;
import com.example.myapplication.Data.Credential;
import com.example.myapplication.Data.Helper.AccountHelper;
import com.example.myapplication.Data.Helper.CredentialHelper;
import com.example.myapplication.Data.Helper.ImageHelper;
import com.example.myapplication.Data.Image;
import com.example.myapplication.Data.Review;
import com.example.myapplication.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<Review> list;
    private Context context;

    public ReviewAdapter(List<Review> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_review_card, viewGroup, false);
        return new ReviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolder viewHolder, final int position) {
        ImageHelper helper = new ImageHelper(context);
        AccountHelper accountHelper = new AccountHelper(context);
        CredentialHelper credentialHelper = new CredentialHelper(context);
        Review data = list.get(position);

        Account account = accountHelper.get(data.getReviewerID());
        Credential credential = credentialHelper.get(account.getCredentialID());

        viewHolder.getUsername().setText(credential.getUsername());
        viewHolder.getComment().setText(data.getComment());
        viewHolder.getRating().setRating(data.getReview());

        Image img = helper.get(account.getProfileID());
        Bitmap bm = BitmapFactory.decodeByteArray(img.getImg(), 0, img.getImg().length);
        viewHolder.getProfile().setImageBitmap(bm);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username, comment;
        private RatingBar rating;
        private ImageView profile;
        public ViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.card_review_username);
            comment = view.findViewById(R.id.card_review_comment);
            rating = view.findViewById(R.id.card_review_rating);
            profile = view.findViewById(R.id.card_review_profile);
        }

        public TextView getUsername() {
            return username;
        }

        public TextView getComment() {
            return comment;
        }

        public RatingBar getRating() {
            return rating;
        }

        public ImageView getProfile() {
            return profile;
        }
    }
}