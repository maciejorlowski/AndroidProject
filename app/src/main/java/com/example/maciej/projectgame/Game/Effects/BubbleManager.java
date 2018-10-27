package com.example.maciej.projectgame.Game.Effects;

import android.graphics.Canvas;

import com.example.maciej.projectgame.Game.Animations.AnimationManager;
import com.example.maciej.projectgame.Game.Animations.BubbleExplo;

import java.util.ArrayList;

public class BubbleManager {
    private ArrayList<Bubble> bubbles;
    AnimationManager animationManager;

    public BubbleManager()
    {
        bubbles = new ArrayList<>();
        animationManager = new AnimationManager(new BubbleExplo().getAnimations());
    }

    public void addBubble(int xStart,int yStart)
    {
        bubbles.add(new Bubble(animationManager,xStart,yStart));
    }

    public void update()
    {
        if(bubbles.isEmpty())
            return;
        for(Bubble b : bubbles)
        {
            if(b.getState()>9)
                bubbles.remove(b);
            else
                b.update();
        }
    }

    public void draw(Canvas canvas)
    {
//        if(bubbles.isEmpty())
//            return;
        for(Bubble b : bubbles)
            b.draw(canvas);
    }
}
