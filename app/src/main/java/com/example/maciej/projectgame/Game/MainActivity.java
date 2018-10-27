package com.example.maciej.projectgame.Game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"start1 " + Runtime.getRuntime().freeMemory() + "  " + Runtime.getRuntime().totalMemory() + "  " + Runtime.getRuntime().maxMemory());

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //A structure describing general information about a display, such as its size, density, and font scaling.
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.SCREEN_WIDTH = dm.widthPixels;


        Log.d(TAG,"start2 " + Runtime.getRuntime().freeMemory() + "  " + Runtime.getRuntime().totalMemory() + "  " + Runtime.getRuntime().maxMemory());

        setContentView(new GamePanel(this));
    }
}
