package com.example.maciej.projectgame.Game.Scenes;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

public class SceneManager {

    private ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;

    public SceneManager(Context context) {
        ACTIVE_SCENE = 0;
        scenes.add(new GameplayScene(context));
    }

    public void recieveouch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }




    public void update()
    {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas)
    {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }
}
