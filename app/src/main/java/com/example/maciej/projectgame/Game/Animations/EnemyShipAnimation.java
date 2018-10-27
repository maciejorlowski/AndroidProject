package com.example.maciej.projectgame.Game.Animations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.example.maciej.projectgame.Game.Constants;
import com.example.maciej.projectgame.R;

public class EnemyShipAnimation {

    private Animation Index;
    private Animation flyRight;
    private Animation flyLeft;

    private Animation[] animations;


    public EnemyShipAnimation()
    {
        BitmapFactory bf = new BitmapFactory();
        Bitmap Image = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienship);
        Bitmap flyRight1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienshipturnright);
        Bitmap flyLeft1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienshipturnleft);

        Matrix m = new Matrix();
        m.preScale(1, -1);
//        m.preRotate(180);
        flyRight1 = Bitmap.createBitmap(flyRight1, 0, 0, flyRight1.getWidth(), flyRight1.getHeight(), m, false);
        flyLeft1 = Bitmap.createBitmap(flyLeft1, 0, 0, flyLeft1.getWidth(), flyLeft1.getHeight(), m, false);

        Index = new Animation(new Bitmap[]{Image}, 2);
        flyRight = new Animation(new Bitmap[]{flyRight1}, 0.5f);
        flyLeft = new Animation(new Bitmap[]{flyLeft1}, 0.5f);
        animations = new Animation[]{Index, flyRight, flyLeft};
    }

    public Animation[] getAnimations(){return animations;}

}
