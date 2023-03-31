package com.example.myapplication.Intro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Dashboard.DashboardActivity;
import com.example.myapplication.R;

public class SplashScreen extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences("Filmograph", Context.MODE_PRIVATE);

        new Handler().postDelayed(() -> {
            boolean firstLaunch = preferences.getBoolean("firstLaunch", true);

            if(firstLaunch)
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_fragment_container, new IntroSlider())
                        .commit();
            else {

                startActivity(new Intent(getActivity(), DashboardActivity.class));
                getActivity().finish();
            }
        }, 2000);

        return view;
    }
}