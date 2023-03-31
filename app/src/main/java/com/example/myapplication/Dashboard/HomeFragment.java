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


public class HomeFragment extends Fragment {
    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    private EditText search;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView list = view.findViewById(R.id.home_list);
        search = view.findViewById(R.id.home_search_edit);
        Button button = view.findViewById(R.id.home_search_button);

        list.setLayoutManager(new LinearLayoutManager(getContext()));

        MovieHelper helper = new MovieHelper(getContext());
        List<Movie> movies = new ArrayList<>();



        Random generator = new Random();
        if(helper.get().size() < 1)
            return;
        int one = generator.nextInt(helper.get().size());
        int two = generator.nextInt(helper.get().size());
        int three = generator.nextInt(helper.get().size());
        for(Movie m:helper.get()){
            if(m.getID() == one || m.getID() == two || m.getID() == three)
                movies.add(m);
        }

        MoviesAdapter adapter = new MoviesAdapter(movies, getContext());
        list.setAdapter(adapter);

    }
}