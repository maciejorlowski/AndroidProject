package com.example.maciej.projectgame.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteAdapter extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    private static SQLiteAdapter instance = null;

    private SQLiteDatabase database;

    private SQLiteAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static SQLiteAdapter getInstance(Context context) {
        if(null == instance)
        {
            instance = new SQLiteAdapter(context);
        }
        return instance;
    }

    public static SQLiteAdapter getInstance()
    {
        if(null == instance)
            throw new IllegalArgumentException("Parametr context is missing!");
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("onCreate","create SQLiteDatabase");
        database = db;
        initialize();
    }

    protected void initialize()
    {
        database.execSQL(
                "create table results(" + "id integer primary key autoincrement," + "nickname text," + "result integer);" + "");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addResult(String nickname, Integer result) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("nickname", nickname);
        value.put("result", result);
        db.insertOrThrow("results", null, value);
    }

    public Cursor readAll() {
        String[] col = {"id", "nickname", "result"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("results", col, null, null, null, null, "result DESC");
        return cursor;
    }

    public void deleteALl() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("results", null,null);
    }

}
