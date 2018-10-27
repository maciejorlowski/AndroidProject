package com.example.maciej.projectgame;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.maciej.projectgame.Database.SQLiteAdapter;

public class Highscores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        TextView tv = findViewById(R.id.textView);


        Cursor cursor = SQLiteAdapter.getInstance(this).readAll();
        while(cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String nickname = cursor.getString(1);
            int result = cursor.getInt(2);
            tv.setText(tv.getText()+"\n"+nickname+" "+result);
        }
    }
    public void delete(View view)
    {
        SQLiteAdapter.getInstance(this).deleteALl();
        finish();
        startActivity(getIntent());
    }
}
