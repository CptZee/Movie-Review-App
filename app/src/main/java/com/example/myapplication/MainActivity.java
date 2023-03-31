package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.Data.Helper.DatabaseHelper;
import com.example.myapplication.Intro.SplashScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.activity_fragment_container, new SplashScreen()).commit();

        DatabaseHelper helper = new DatabaseHelper(this);
        helper.initDatabase();
    }
}