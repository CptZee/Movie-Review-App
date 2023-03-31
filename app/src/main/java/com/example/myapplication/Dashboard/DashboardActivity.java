package com.example.myapplication.Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.myapplication.Authentication.AuthenticatedFragment;
import com.example.myapplication.Authentication.LoginFragment;
import com.example.myapplication.Data.Helper.DatabaseHelper;
import com.example.myapplication.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SharedPreferences preferences = getSharedPreferences("Filmograph", Context.MODE_PRIVATE);

        MeowBottomNavigation bottomNavigation = findViewById(R.id.dashboard_bottom_nav);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_movie));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_profile));
        bottomNavigation.setOnShowListener(model -> {
            switch(model.getId()){
                case 1:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.dashboard_container, new HomeFragment())
                            .commit();
                    break;
                case 2:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.dashboard_container, new AllMovieFragment())
                            .commit();
                    break;
                case 3:
                    int loggedUserID = preferences.getInt("loggedUserID", 0);
                    boolean loggedIn = preferences.getBoolean("loggedIn", false);

                    if(loggedIn){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.dashboard_container, new AuthenticatedFragment())
                                .commit();
                    }
                    else{
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.dashboard_container, new LoginFragment())
                                .commit();
                    }
                    break;
            }
            return null;
        });

        bottomNavigation.show(1, true);
    }
}