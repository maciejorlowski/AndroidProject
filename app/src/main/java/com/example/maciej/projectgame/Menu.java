package com.example.maciej.projectgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.maciej.projectgame.Game.MainActivity;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void start(View view)
    {
        Intent intent = new Intent(Menu.this,MainActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void tasks(View view)
    {
        Intent intent = new Intent(Menu.this,Highscores.class);
        startActivity(intent);
    }

}
