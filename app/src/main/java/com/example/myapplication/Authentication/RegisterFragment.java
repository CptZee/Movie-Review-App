package com.example.myapplication.Authentication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Data.Account;
import com.example.myapplication.Data.Credential;
import com.example.myapplication.Data.Helper.AccountHelper;
import com.example.myapplication.Data.Helper.CredentialHelper;
import com.example.myapplication.R;

public class RegisterFragment extends Fragment {
    public RegisterFragment() {
    super(R.layout.fragment_register);
}

    private EditText username, email, password, password2;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        username = view.findViewById(R.id.register_username);
        email = view.findViewById(R.id.register_email);
        password = view.findViewById(R.id.register_password1);
        password2 = view.findViewById(R.id.register_password2);
        Button button = view.findViewById(R.id.register_button);
        TextView register = view.findViewById(R.id.textView5);

        button.setOnClickListener(v->{
            String user = username.getText().toString();
            String mail = email.getText().toString();
            String pass = password.getText().toString();
            String pass2 = password2.getText().toString();
            boolean valid = true;
            if(user.isEmpty()){
                username.setError("Please enter an username!");
                valid = false;
            }
            if(mail.isEmpty()){
                email.setError("Please enter an email!");
                valid = false;

            }
            if(pass.isEmpty()){
                password.setError("Please enter a password!");
                valid = false;
            }
            if(pass2.isEmpty()){
                password2.setError("Please enter a password!");
                valid = false;
            }
            if(!valid)
                return;
            if(!pass.equals(pass2)){
                password2.setError("Passwords does not match!");
                return;
            }
            AccountHelper accountHelper = new AccountHelper(getActivity());
            CredentialHelper credentialHelper = new CredentialHelper(getActivity());

            Account account = new Account(mail, credentialHelper.getID());
            Credential credential = new Credential(user, pass, accountHelper.getID());

            accountHelper.insert(account);
            credentialHelper.insert(credential);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dashboard_container, new LoginFragment())
                    .commit();
        });

        register.setOnClickListener(v->{
            getActivity().
                    getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dashboard_container, new RegisterFragment())
                    .commit();
        });
    }
}