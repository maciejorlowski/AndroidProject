package com.example.maciej.projectgame.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maciej.projectgame.Game.ResultDTO;

import java.util.LinkedList;
import java.util.List;

public class ResultDAO implements IResultDAO {

    protected SQLiteDatabase database;

    public ResultDAO() { database = SQLiteAdapter.getInstance().getWritableDatabase();}

    public ResultDAO(SQLiteDatabase db){ database=db; }


    @Override
    public void insert(ResultDTO object) {
        ContentValues value = new ContentValues();
        value.put("nickname", object.getNickname());
        value.put("result", object.getResult());
        database.insertOrThrow("results", null, value);
    }

    @Override
    public List<ResultDTO> getAll(String table) {
        List<ResultDTO> list = new LinkedList<ResultDTO>();
        String[] col = {"id", "nickname", "result"};
        Cursor cursor = database.query("results", col, null, null, null, null, "result DESC");
        while (cursor.moveToNext())
        {
            ResultDTO result = new ResultDTO();
            result.setNickname(cursor.getString(0));
            result.setResult(cursor.getInt(1));
            list.add(result);
        }
        return list;    }

    @Override
    public void deleteAll(String table) {
        database.delete("results", null,null);

    }
}
