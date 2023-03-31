package com.example.myapplication.Intro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.Dashboard.DashboardActivity;
import com.example.myapplication.R;

public class IntroSlider extends Fragment {

    public IntroSlider() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro_slider, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences("Filmograph", Context.MODE_PRIVATE);

        Button button = view.findViewById(R.id.button);

        button.setOnClickListener(v ->{
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstLaunch", false);
            editor.commit();
            startActivity(new Intent(getActivity(), DashboardActivity.class));
            getActivity().finish();
        });
        return view;
    }
}