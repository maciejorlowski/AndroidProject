package com.example.maciej.projectgame.Game.Effects;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.maciej.projectgame.Game.Animations.AnimationManager;
import com.example.maciej.projectgame.Game.Objects.GameObject;

public class Bubble implements GameObject {
    AnimationManager animationManager;
    Rect rectangle;
    int state = 0;
    private long frameTime;



    public Bubble(AnimationManager animationManager,int xStart,int yStart)
    {
        this.animationManager = animationManager;
        rectangle = new Rect(xStart,yStart,xStart+100,yStart+100);            ///trzeba bedzie zmiecnic to tylkop dla przykladu
    }

    public int getState(){return state;}

    @Override
    public void update() {

        animationManager.playAnim(state++);
        animationManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setColor(Color.YELLOW);
//        canvas.drawRect(rectangle,paint);
        animationManager.draw(canvas,rectangle);

    }
}
