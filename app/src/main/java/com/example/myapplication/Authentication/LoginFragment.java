package com.example.myapplication.Authentication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Data.Credential;
import com.example.myapplication.Data.Helper.CredentialHelper;
import com.example.myapplication.R;


public class LoginFragment extends Fragment {


    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    private EditText username, password;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        username = view.findViewById(R.id.login_username);
        password = view.findViewById(R.id.login_password);
        Button button = view.findViewById(R.id.login_button);
        TextView register = view.findViewById(R.id.textView5);
        SharedPreferences preferences = getActivity().getSharedPreferences("Filmograph", Context.MODE_PRIVATE);

        button.setOnClickListener(v->{
            String user = username.getText().toString();
            String pass = password.getText().toString();
            boolean valid = true;
            if(user.isEmpty()){
                username.setError("Please enter an username!");
                valid = false;
            }
            if(pass.isEmpty()){
                password.setError("Please enter a password!");
                valid = false;
            }
            if(!valid)
                return;
            CredentialHelper helper = new CredentialHelper(getActivity());
            for(Credential credential:helper.get()){
                if(credential.getUsername().equals(user) && credential.getPassword().equals(pass)){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("loggedUserID", credential.getAccountID());
                    Log.i("Security", "Logged user saved as " + credential.getAccountID());
                    editor.putBoolean("loggedIn", true);
                    editor.commit();

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.dashboard_container, new AuthenticatedFragment())
                            .addToBackStack(null) //Return to the last fragment
                            .commit();
                    return;
                }
            }
            username.setError("Invalid username!");
            password.setError("Invalid password!");
        });

        register.setOnClickListener(v->{
            getActivity().
                    getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dashboard_container, new RegisterFragment())
                    .commit();
        });
    }
}