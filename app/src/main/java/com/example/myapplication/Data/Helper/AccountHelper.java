package com.example.myapplication.Data.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.Data.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountHelper extends SQLiteOpenHelper {
    public AccountHelper(@Nullable Context context) {
        super(context, "db_Filmorate", null, 1);
    }

    private String TABLENAME = "tbl_Accounts";

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLENAME + "(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "email TEXT," +
                    "credentialID INTEGER," +
                    "profileID INTEGER," +
                    "archived INTEGER)");
        }catch (SQLiteException e){
            Log.e("Database", "Unable to create " + TABLENAME);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try{
            db.execSQL("DROP TABLE " + TABLENAME);
            onCreate(db);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to upgrade " + TABLENAME);
        }
    }

    public void insert(Account data){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.insert(TABLENAME, null, prepareData(data));
            Log.i("Database", "Successfully inserted into " + TABLENAME);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
    }

    public List<Account> get(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Account> list = new ArrayList<>();
        try{
            Cursor cursor = db.rawQuery("SELECT ID, email, credentialID, profileID FROM " + TABLENAME + " WHERE archived = 0", null);
            while (cursor.moveToNext())
                list.add(prepareData(cursor));
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
        Log.i("Database", "Successfully retrieved from " + TABLENAME);
        return list;
    }

    public Account get(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Account data = null;
        try{
            Cursor cursor = db.rawQuery("SELECT ID, email, credentialID, profileID FROM " + TABLENAME + " WHERE ID = ?", new String[]{String.valueOf(ID)});
            while (cursor.moveToNext())
                data = prepareData(cursor);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
        Log.i("Database", "Successfully retrieved from " + TABLENAME);
        return data;
    }

    public void update(Account data){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.update(TABLENAME, prepareData(data), "ID = ?", new String[]{String.valueOf(data.getID())});
            Log.i("Database", "Successfully inserted into " + TABLENAME);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
    }

    public void remove(Account data){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues content = new ContentValues();
            content.put("archived", 1);
            db.update(TABLENAME, content, "ID = ?", new String[]{String.valueOf(data.getID())});
            Log.i("Database", "Successfully inserted into " + TABLENAME);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
    }

    public int getID(){
        SQLiteDatabase db = this.getReadableDatabase();
        int ID = 0;
        try{
            Cursor cursor = db.rawQuery("SELECT MAX(ID) FROM " + TABLENAME,null);
            while (cursor.moveToNext())
                ID = cursor.getInt(0);
            ID++;
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
        return ID;
    }

    private ContentValues prepareData(Account data){
        ContentValues content = new ContentValues();
        if(data.getEmail() != null)
            content.put("email", data.getEmail());
        if(data.getCredentialID() != 0)
            content.put("credentialID", data.getCredentialID());
        if(data.getProfileID() != 0)
            content.put("profileID", data.getProfileID());
        content.put("archived", 0);
        return content;
    }

    private Account prepareData(Cursor cursor){
        Account data = new Account();
        data.setID(cursor.getInt(0));
        data.setEmail(cursor.getString(1));
        data.setCredentialID(cursor.getInt(2));
        data.setProfileID(cursor.getInt(3));
        return data;
    }
}
