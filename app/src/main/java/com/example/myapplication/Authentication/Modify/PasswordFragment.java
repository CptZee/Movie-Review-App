package com.example.myapplication.Authentication.Modify;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Data.Account;
import com.example.myapplication.Data.Credential;
import com.example.myapplication.Data.Helper.AccountHelper;
import com.example.myapplication.Data.Helper.CredentialHelper;
import com.example.myapplication.R;

public class PasswordFragment extends Fragment {
    public PasswordFragment() {
        super(R.layout.fragment_password);
    }

    private EditText password1, password2, password3;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        password1 = view.findViewById(R.id.password_current);
        password2 = view.findViewById(R.id.password_new1);
        password3 = view.findViewById(R.id.password_new2);
        Button button = view.findViewById(R.id.password_update_button);

        SharedPreferences preferences = getActivity().getSharedPreferences("Filmograph", Context.MODE_PRIVATE);
        CredentialHelper credentialHelper = new CredentialHelper(getContext());
        AccountHelper accountHelper = new AccountHelper(getContext());

        Account account = accountHelper.get(preferences.getInt("loggedUserID", 0));
        Credential credential = credentialHelper.get(account.getCredentialID());

        button.setOnClickListener(v ->{
            if(!password1.getText().toString().equals(credential.getPassword())){
                password1.setError("Invalid password");
                return;
            }
            if(!password2.getText().toString().equals(password3.getText().toString())){
                password3.setError("Password does not match");
                return;
            }
            credential.setPassword(password2.getText().toString());
            credentialHelper.update(credential);
        });


    }
}