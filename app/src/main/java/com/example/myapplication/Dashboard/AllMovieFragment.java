package com.example.myapplication.Dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Adapter.MoviesAdapter;
import com.example.myapplication.Data.Helper.MovieHelper;
import com.example.myapplication.Data.Movie;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllMovieFragment extends Fragment {

    public AllMovieFragment(){
        super(R.layout.fragment_all_movie);
    }

    private EditText search;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView list = view.findViewById(R.id.all_list);
        search = view.findViewById(R.id.all_search_edit);
        Button button = view.findViewById(R.id.all_search_button);

        list.setLayoutManager(new LinearLayoutManager(getContext()));

        MovieHelper helper = new MovieHelper(getContext());
        List<Movie> movies = helper.get();

        MoviesAdapter adapter = new MoviesAdapter(movies, getContext());
        list.setAdapter(adapter);
    }
}