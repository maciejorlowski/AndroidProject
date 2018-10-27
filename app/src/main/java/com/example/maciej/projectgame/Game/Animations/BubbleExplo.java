package com.example.maciej.projectgame.Game.Animations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.maciej.projectgame.Game.Constants;
import com.example.maciej.projectgame.R;

public class BubbleExplo {
    private Animation[] animations = new Animation[10];

    public BubbleExplo()
    {
        BitmapFactory bf = new BitmapFactory();
        Bitmap[] bitmap = new Bitmap[10];

        bitmap[0] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bubble_explo1);
        bitmap[1]= bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bubble_explo2);
        bitmap[2] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bubble_explo3);
        bitmap[3] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bubble_explo4);
        bitmap[4] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bubble_explo5);
        bitmap[5] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bubble_explo6);
        bitmap[6] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bubble_explo7);
        bitmap[7] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bubble_explo8);
        bitmap[8] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bubble_explo9);
        bitmap[9] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bubble_explo10);


        for(int i=0;i<10;i++)
        {
            bitmap[i] = Bitmap.createBitmap(bitmap[i], 0, 0, bitmap[i].getWidth(), bitmap[i].getHeight(), null, false);
        }
        for(int i=0;i<10;i++)
        {
            animations[i] = new Animation(new Bitmap[]{bitmap[i]}, 10);
        }


    }
    public Animation[] getAnimations(){return animations;}

}
