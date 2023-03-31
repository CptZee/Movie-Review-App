package com.example.myapplication.Authentication.Modify;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Data.Account;
import com.example.myapplication.Data.Credential;
import com.example.myapplication.Data.Helper.AccountHelper;
import com.example.myapplication.Data.Helper.CredentialHelper;
import com.example.myapplication.Data.Helper.ImageHelper;
import com.example.myapplication.Data.Image;
import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;

public class PersonalFragment extends Fragment {
    public PersonalFragment() {
        super(R.layout.fragment_personal);
    }

    private final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView profile;
    private boolean newProfileSelected;
    private Bitmap img;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        profile = view.findViewById(R.id.personal_profile);
        ImageView profileButton = view.findViewById(R.id.personal_profile_button);
        EditText username = view.findViewById(R.id.personal_username);
        EditText email = view.findViewById(R.id.personal_email);
        Button updateButton = view.findViewById(R.id.personal_update);
        Button passwordButton = view.findViewById(R.id.personal_password);
        newProfileSelected = false;

        AccountHelper accountHelper = new AccountHelper(getContext());
        CredentialHelper credentialHelper = new CredentialHelper(getContext());
        ImageHelper imageHelper = new ImageHelper(getContext());

        SharedPreferences preferences = getActivity().getSharedPreferences("Filmograph", Context.MODE_PRIVATE);

        Account account = accountHelper.get(preferences.getInt("loggedUserID", 0));
        Credential credential = credentialHelper.get(account.getCredentialID());
        Image image = imageHelper.get(account.getProfileID());
        if(image != null) {
            Bitmap bm = BitmapFactory.decodeByteArray(image.getImg(), 0, image.getImg().length);
            profile.setImageBitmap(bm);
        }

        profileButton.setOnClickListener(v ->{
            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_IMAGE_CAPTURE);
        });

        updateButton.setOnClickListener(v ->{
            if(!username.getText().toString().isEmpty())
                credential.setUsername(username.getText().toString());
            if(!email.getText().toString().isEmpty())
                account.setEmail(email.getText().toString());
            if(newProfileSelected){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                img.compress(Bitmap.CompressFormat.PNG, 100, baos);
                Image image2 = new Image(credential.getUsername()+"Profile", baos.toByteArray());
                Log.i("ImageManager", "Image byte is " + image2.getImg());
                imageHelper.insert(image2);
                account.setProfileID(imageHelper.getID());
            }
            credentialHelper.update(credential);
            accountHelper.update(account);

            getActivity().finish();
            Toast.makeText(getContext(), "Account updated!", Toast.LENGTH_SHORT);
        });

        passwordButton.setOnClickListener(v->{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.setup_container, new PasswordFragment())
                    .addToBackStack(null)
                    .commit();
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            img = data.getParcelableExtra("data");
            profile.setImageBitmap(img);
            newProfileSelected = true;
        }
    }
}