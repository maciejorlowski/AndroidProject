package com.example.maciej.projectgame.Database;

import com.example.maciej.projectgame.Game.ResultDTO;

import java.util.List;

public interface IResultDAO {


    public void insert(ResultDTO object);
    public List<ResultDTO> getAll(String table);
    public void deleteAll(String table);


}
