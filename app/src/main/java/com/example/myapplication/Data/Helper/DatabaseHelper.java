package com.example.myapplication.Data.Helper;

import android.content.Context;

import com.example.myapplication.Data.Credential;

public class DatabaseHelper {

    private Context context;

    public DatabaseHelper(Context context){
        this.context = context;
    }

    public void initDatabase(){
        AccountHelper accountHelper = new AccountHelper(context);
        CredentialHelper credentialHelper = new CredentialHelper(context);
        ImageHelper imageHelper = new ImageHelper(context);
        MovieHelper movieHelper = new MovieHelper(context);
        ReviewHelper reviewHelper = new ReviewHelper(context);

        accountHelper.onCreate(accountHelper.getWritableDatabase());
        credentialHelper.onCreate(credentialHelper.getWritableDatabase());
        imageHelper.onCreate(imageHelper.getWritableDatabase());
        movieHelper.onCreate(movieHelper.getWritableDatabase());
        reviewHelper.onCreate(reviewHelper.getWritableDatabase());

        accountHelper.close();
        credentialHelper.close();
        imageHelper.close();
        movieHelper.close();
        reviewHelper.close();
    }
}
