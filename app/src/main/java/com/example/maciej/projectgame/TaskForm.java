package com.example.maciej.projectgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class TaskForm extends AppCompatActivity {

    private static final String TAG = "TaskForm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);
        Log.d(TAG,"Created");
    }

    public void submit(View view)
    {
        Intent intent = new Intent(TaskForm.this,Tasks.class);
        EditText edit = findViewById(R.id.editText);
        String text = edit.getText().toString();
        intent.putExtra("new",text.toUpperCase());
        setResult(RESULT_OK,intent);
        Log.d(TAG,"Finished");
        finish();
    }
}
