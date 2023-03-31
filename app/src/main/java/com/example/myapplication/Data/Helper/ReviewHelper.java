package com.example.myapplication.Data.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.Data.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewHelper extends SQLiteOpenHelper {
    public ReviewHelper(@Nullable Context context) {
        super(context, "db_Filmorate", null, 1);
    }

    private String TABLENAME = "tbl_Reviews";

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLENAME + "(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "movieID INTEGER," +
                    "reviewerID INTEGER," +
                    "review INTEGER," +
                    "comment TEXT," +
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

    public void insert(Review data){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.insert(TABLENAME, null, prepareData(data));
            Log.i("Database", "Successfully inserted into " + TABLENAME);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
    }

    public List<Review> get(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Review> list = new ArrayList<>();
        try{
            Cursor cursor = db.rawQuery("SELECT ID, movieID, reviewerID, review, comment FROM " + TABLENAME + " WHERE archived = 0", null);
            while (cursor.moveToNext())
                list.add(prepareData(cursor));
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
        Log.i("Database", "Successfully retrieved from " + TABLENAME);
        return list;
    }

    public Review get(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Review data = null;
        try{
            Cursor cursor = db.rawQuery("SELECT ID, movieID, reviewerID, review, comment FROM " + TABLENAME + " WHERE ID = ?", new String[]{String.valueOf(ID)});
            while (cursor.moveToNext())
                data = prepareData(cursor);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
        Log.i("Database", "Successfully retrieved from " + TABLENAME);
        return data;
    }

    public void update(Review data){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.update(TABLENAME, prepareData(data), "ID = ?", new String[]{String.valueOf(data.getID())});
            Log.i("Database", "Successfully inserted into " + TABLENAME);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
    }

    public void remove(Review data){
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

    private ContentValues prepareData(Review data){
        ContentValues content = new ContentValues();
        if(data.getMovieID() != 0)
            content.put("movieID", data.getMovieID());
        if(data.getReviewerID() != 0)
            content.put("reviewerID", data.getReviewerID());
        if(data.getReviewerID() != 0)
            content.put("review", data.getReview());
        if(data.getComment() == null)
            content.put("comment", data.getComment());
        content.put("archived", 0);
        return content;
    }

    private Review prepareData(Cursor cursor){
        Review data = new Review();
        data.setID(cursor.getInt(0));
        data.setMovieID(cursor.getInt(1));
        data.setReviewerID(cursor.getInt(2));
        data.setReview(cursor.getInt(3));
        data.setComment(cursor.getString(4));
        return data;
    }
}
