package com.example.maciej.projectgame.Game;

public class ResultDTO {
    private String nickname;
    private int result;


    public ResultDTO(){}

    public ResultDTO(String nickname, int result)
    {
        this.nickname=nickname;
        this.result=result;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname(){
        return nickname;
    }

    public void setResult(int result)
    {
        this.nickname = nickname;
    }

    public int getResult()
    {
        return result;
    }
}
