package com.example.maciej.projectgame;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Tasks extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView list;
    private ArrayAdapter<String> adapter;
    private boolean delete;
    private LinearLayout linearLayout;
    private static final String TAG = "Tasks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        Log.d(TAG,"Started");
        linearLayout = (LinearLayout) findViewById(R.id.linnearlayout);

        list= (ListView)findViewById(R.id.List1);
        ArrayList<String> task = new ArrayList<String>();
        String tasks[]={"task1","https://www.youtube.com/","task3"};

        task.addAll(Arrays.asList(tasks));

        adapter = new ArrayAdapter<String >(this,R.layout.editlist,task);

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void add(View view)
    {
        Intent intent = new Intent(Tasks.this,TaskForm.class);
        Log.d(TAG,"Paused");
        startActivityForResult(intent,90);
    }
    public void delete(View view)
    {
        delete = !delete;
    }

//    public void select(View view)
//    {
//        if(delete) {
//            Toast.makeText(Tasks.this, "usunieto", Toast.LENGTH_SHORT).show();
//            adapter.remove(String.valueOf(myButtonClickListener));
//            adapter.notifyDataSetChanged();
//            delete = false;
//
//     }}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d(TAG,"Resumed");
        if(requestCode == requestCode)
            if(resultCode == RESULT_OK)
            {
                String text = data.getStringExtra("new");
                adapter.add(text);
                Toast.makeText(Tasks.this,"added "+text,Toast.LENGTH_SHORT).show();
            }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(delete) {
            Snackbar snackbar = Snackbar.make(linearLayout,"usunieto",Snackbar.LENGTH_LONG);
            snackbar.show();

//            Toast.makeText(Tasks.this, "usunieto", Toast.LENGTH_SHORT).show();
            adapter.remove(adapter.getItem(position));
            adapter.notifyDataSetChanged();
            delete = false;
        }
        else
        {
            String url = adapter.getItem(position).toString();
            if(url.contains("https://")){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            Log.d(TAG,"Paused");
            startActivity(intent);
            Log.d(TAG,"Resumed");
        }}
    }
}
