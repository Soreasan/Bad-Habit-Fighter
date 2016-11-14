package com.example.sorea.badhabitfighter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kenneth Adair on 10/30/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private SQLiteDatabase myDatabase;

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("test", "Creating new database");
        //This query creates a table
        String createQuery = "CREATE TABLE mistakes" +
                "(_id integer primary key autoincrement," +
                "date TEXT, code TEXT, notes TEXT, display TEXT);";
        db.execSQL(createQuery); //execute this query to create the database.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d("test", "On Upgrade");
        //On upgrade delete all tables and recreate them
        db.execSQL("DROP TABLE IF EXISTS mistakes");
        onCreate(db);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        Log.d("test", "Creating DatabaseHelper");
    }

    public SQLiteDatabase open(){
        myDatabase = getWritableDatabase();
        Log.d("test", "SQLiteDatabase open() called.");
        return myDatabase;
    }

    public void close(){
        if(myDatabase != null){
            Log.d("test", "closing the database.");
            myDatabase.close();
        }else{
            Log.d("test", "Failed to close the database");
        }
    }

    public long insertMistake(String date, String code, String notes){
        /*
        We're going to return rowID so we set it to a default value of -1
        so if the insert fails and we return this the program can easily know
        it failed.  If it succeeds it instead returns the id primary key thingy.
         */
        long rowID = -1;
        ContentValues newMistake = new ContentValues();
        newMistake.put("date", date);
        newMistake.put("code", code);
        newMistake.put("notes", notes);
        newMistake.put("display", date + " - " + code);
        if(open()!= null){
            Log.d("test", "INSERT succeeded.");
            rowID = myDatabase.insert("mistakes", null, newMistake);
            close();
        }else{
            Log.d("test", "INSERT failed because database is closed.");
        }
        return rowID;
    }

    public long updateMistake(long _id, String date, String code, String notes){
        long rowID = -1;
        ContentValues newMistake = new ContentValues();
        newMistake.put("date", date);
        newMistake.put("code", code);
        newMistake.put("notes", notes);
        newMistake.put("display", date + " - " + code);
        if(open()!= null){
            Log.d("test", "UPDATE succeeded.");
            rowID = myDatabase.update("mistakes", newMistake, "_id=" + _id, null);
            close();
        }else{
            Log.d("test", "UPDATE failed because database is closed.");
        }
        return rowID;
    }

    public void deleteMistake(long _id){
        if(open() != null) {
            Log.d("test", "DELETE succeeded");
            myDatabase.delete("mistakes", "_id=" + _id, null);
            close();
        }else{
            Log.d("test","delete failed because database was closed");
        }
    }

    public Cursor getAllMistakes(){
        Cursor cursor = null;
        if(open() != null) {
            Log.d("test", "SELECT ALL succeeded and returned a cursor");
            cursor = myDatabase.rawQuery("SELECT * FROM mistakes", null);
        }else{
            Log.d("test", "SELECT ALL failed because the database was closed.");
        }
        return cursor;
    }

    public Cursor getOneMistake(long _id){
        String[] params = new String[1];
        params[0] = "" + _id;
        Cursor cursor = null;
        if(open() != null){
            Log.d("test", "SELECT one mistake has succeeded.");
            cursor = myDatabase.rawQuery("SELECT * FROM mistakes where _id = ?", params);
        }else{
            Log.d("test", "Failed to delete this specific mistake.");
        }
        return cursor;
    }
}
