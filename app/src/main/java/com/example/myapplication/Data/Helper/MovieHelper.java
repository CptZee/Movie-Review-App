package com.example.myapplication.Data.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.Data.Credential;
import com.example.myapplication.Data.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieHelper extends SQLiteOpenHelper {
    public MovieHelper(@Nullable Context context) {
        super(context, "db_Filmorate", null, 1);
    }

    private String TABLENAME = "tbl_Movies";

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLENAME + "(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT," +
                    "year INTEGER," +
                    "sypnosis TEXT," +
                    "rating REAL," +
                    "posterID INTEGER," +
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

    public void insert(Movie data){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.insert(TABLENAME, null, prepareData(data));
            Log.i("Database", "Successfully inserted into " + TABLENAME);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
    }

    public List<Movie> get(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Movie> list = new ArrayList<>();
        try{
            Cursor cursor = db.rawQuery("SELECT ID, title, year, sypnosis, rating, posterID FROM " + TABLENAME + " WHERE archived = 0", null);
            while (cursor.moveToNext())
                list.add(prepareData(cursor));
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
        Log.i("Database", "Successfully retrieved from " + TABLENAME);
        return list;
    }

    public Movie get(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Movie data = null;
        try{
            Cursor cursor = db.rawQuery("SELECT ID, title, year, sypnosis, rating, posterID FROM " + TABLENAME + " WHERE ID = ?", new String[]{String.valueOf(ID)});
            while (cursor.moveToNext())
                data = prepareData(cursor);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
        Log.i("Database", "Successfully retrieved from " + TABLENAME);
        return data;
    }

    public void update(Movie data){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.update(TABLENAME, prepareData(data), "ID = ?", new String[]{String.valueOf(data.getID())});
            Log.i("Database", "Successfully inserted into " + TABLENAME);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
    }

    public void remove(Movie data){
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

    private ContentValues prepareData(Movie data){
        ContentValues content = new ContentValues();
        if(data.getTitle() != null)
            content.put("title", data.getTitle());
        if(data.getYear() != 0)
            content.put("year", data.getYear());
        if(data.getSypnosis() != null)
            content.put("sypnonis", data.getSypnosis());
        if(data.getRating() != 0)
            content.put("rating", data.getRating());
        if(data.getPosterID() != 0)
            content.put("posterID", data.getPosterID());
        content.put("archived", 0);
        return content;
    }

    private Movie prepareData(Cursor cursor){
        Movie data = new Movie();
        data.setID(cursor.getInt(0));
        data.setTitle(cursor.getString(1));
        data.setYear(cursor.getInt(2));
        data.setSypnosis(cursor.getString(3));
        data.setRating(cursor.getFloat(4));
        data.setPosterID(cursor.getInt(5));
        return data;
    }
}