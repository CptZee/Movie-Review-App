package com.example.myapplication.Data.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.Data.Image;
import java.util.ArrayList;
import java.util.List;

public class ImageHelper extends SQLiteOpenHelper {
    public ImageHelper(@Nullable Context context) {
        super(context, "db_Filmorate", null, 1);
    }

    private String TABLENAME = "tbl_Images";

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLENAME + "(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "alt TEXT," +
                    "img BLOB," +
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

    public void insert(Image data){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            Log.i("ImageManager", "Image data to be inserted is: "+ prepareData(data).getAsByteArray("img"));
            db.insert(TABLENAME, null, prepareData(data));
            Log.i("Database", "Successfully inserted into " + TABLENAME);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
    }

    public List<Image> get(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Image> list = new ArrayList<>();
        try{
            Cursor cursor = db.rawQuery("SELECT ID, alt, img FROM " + TABLENAME + " WHERE archived = 0", null);
            while (cursor.moveToNext())
                list.add(prepareData(cursor));
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
        Log.i("Database", "Successfully retrieved from " + TABLENAME);
        return list;
    }

    public Image get(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Image data = null;
        try{
            Cursor cursor = db.rawQuery("SELECT ID, alt, img FROM " + TABLENAME + " WHERE ID = ?", new String[]{String.valueOf(ID)});
            while (cursor.moveToNext())
                data = prepareData(cursor);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
        Log.i("Database", "Successfully retrieved from " + TABLENAME);
        return data;
    }

    public void update(Image data){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.update(TABLENAME, prepareData(data), "ID = ?", new String[]{String.valueOf(data.getID())});
            Log.i("Database", "Successfully inserted into " + TABLENAME);
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
    }

    public void remove(Image data){
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
        }catch (SQLiteException e){
            Log.e("Database", "Unable to insert into " + TABLENAME);
        }
        return ID;
    }

    private ContentValues prepareData(Image data){
        ContentValues content = new ContentValues();
        if(data.getAlt() != null)
            content.put("alt", data.getAlt());
        if(data.getImg() != null)
            content.put("img", data.getImg());
        content.put("archived", 0);
        return content;
    }

    private Image prepareData(Cursor cursor){
        Image data = new Image();
        data.setID(cursor.getInt(0));
        data.setAlt(cursor.getString(1));
        data.setImg(cursor.getBlob(2));
        return data;
    }
}