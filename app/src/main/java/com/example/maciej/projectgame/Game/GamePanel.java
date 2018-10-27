package com.example.maciej.projectgame.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.maciej.projectgame.Game.Scenes.SceneManager;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private SceneManager sceneManager;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);

        Constants.CURRENT_CONTEXT = context;
        thread = new MainThread(getHolder(),this);

        setFocusable(true);

        sceneManager = new SceneManager(context);
    }

    //reset the game


    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(),this);
        Constants.INIT_TIME = System.currentTimeMillis();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
        try{
            thread.setRunning(false);
            thread.join();
        }catch (Exception e){e.printStackTrace();}
        retry = false;
    }}

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        sceneManager.recieveouch(event);

        return true;

        //return super.onTouchEvent(event);
    }
    public void update() {
        sceneManager.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        sceneManager.draw(canvas);
    }

}
