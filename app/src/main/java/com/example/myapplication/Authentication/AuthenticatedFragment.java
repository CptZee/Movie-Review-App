package com.example.myapplication.Authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Authentication.Modify.UserSetUpActivity;
import com.example.myapplication.Data.Account;
import com.example.myapplication.Data.Helper.AccountHelper;
import com.example.myapplication.Data.Helper.CredentialHelper;
import com.example.myapplication.Data.Helper.ImageHelper;
import com.example.myapplication.Data.Image;
import com.example.myapplication.R;

public class AuthenticatedFragment extends Fragment {
    public AuthenticatedFragment() {
        super(R.layout.fragment_authenticated);
    }

    private TextView username;
    private ImageView profile;
    private SharedPreferences preferences;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button edit = view.findViewById(R.id.authenticated_setup);
        Button logout = view.findViewById(R.id.authenticated_logout);
        username = view.findViewById(R.id.authenticated_username);
        profile = view.findViewById(R.id.authenticated_image);
        preferences = getActivity().getSharedPreferences("Filmograph", Context.MODE_PRIVATE);

        init();


        edit.setOnClickListener(v->{
            startActivity(new Intent(getActivity(), UserSetUpActivity.class));
        });
        logout.setOnClickListener(v->{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dashboard_container, new LoginFragment())
                    .commit();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("loggedUserID", 0);
            editor.putBoolean("loggedIn", false);
            editor.commit();
        });
    }

    private void init(){
        CredentialHelper helper = new CredentialHelper(getContext());
        AccountHelper accountHelper = new AccountHelper(getActivity());
        Account account = accountHelper.get(preferences.getInt("loggedUserID", 0));

        username.setText(helper.get(account.getCredentialID()).getUsername());
        ImageHelper imageHelper = new ImageHelper(getContext());
        Image image = imageHelper.get(account.getProfileID());
        if(image != null) {
            Bitmap bm = BitmapFactory.decodeByteArray(image.getImg(), 0, image.getImg().length);
            profile.setImageBitmap(bm);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        init();
    }
}