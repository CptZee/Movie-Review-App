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
import com.example.myapplication.Data.Credential;

import java.util.ArrayList;
import java.util.List;

public class CredentialHelper extends SQLiteOpenHelper {
    public CredentialHelper(@Nullable Context context) {
        super(context, "db_Filmorate", null, 1);
    }

    private String TABLENAME = "tbl_Credentials";

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLENAME + "(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT," +
                    "password TEXT," +
                    "accountID INTEGER," +
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

    public void insert(Credential data){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.insert(TABLENAME, null, prepareData(data));
            Log.i("Database", "Successfully inserted into " + TABLENAME);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
    }

    public List<Credential> get(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Credential> list = new ArrayList<>();
        try{
            Cursor cursor = db.rawQuery("SELECT ID, username, password, accountID FROM " + TABLENAME + " WHERE archived = 0", null);
            while (cursor.moveToNext())
                list.add(prepareData(cursor));
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
        Log.i("Database", "Successfully retrieved from " + TABLENAME);
        return list;
    }

    public Credential get(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Credential data = null;
        try{
            Cursor cursor = db.rawQuery("SELECT ID, username, password, accountID FROM " + TABLENAME + " WHERE ID = ?", new String[]{String.valueOf(ID)});
            while (cursor.moveToNext())
                data = prepareData(cursor);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
        Log.i("Database", "Successfully retrieved from " + TABLENAME);
        return data;
    }

    public void update(Credential data){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.update(TABLENAME, prepareData(data), "ID = ?", new String[]{String.valueOf(data.getID())});
            Log.i("Database", "Successfully inserted into " + TABLENAME);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
    }

    public void remove(Credential data){
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

    private ContentValues prepareData(Credential data){
        ContentValues content = new ContentValues();
        if(data.getUsername() != null)
            content.put("username", data.getUsername());
        if(data.getPassword() != null)
            content.put("password", data.getPassword());
        if(data.getAccountID() != 0)
            content.put("accountID", data.getAccountID());
        content.put("archived", 0);
        return content;
    }

    private Credential prepareData(Cursor cursor){
        Credential data = new Credential();
        data.setID(cursor.getInt(0));
        data.setUsername(cursor.getString(1));
        data.setPassword(cursor.getString(2));
        data.setAccountID(cursor.getInt(3));
        return data;
    }
}
