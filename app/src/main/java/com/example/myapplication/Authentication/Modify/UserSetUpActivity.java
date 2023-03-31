package com.example.myapplication.Authentication.Modify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.Dashboard.HomeFragment;
import com.example.myapplication.R;

public class UserSetUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_set_up);

        Log.i("UserSetup", "Showing the personal setup fragment");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.setup_container, new PersonalFragment())
                .commit();
    }
}