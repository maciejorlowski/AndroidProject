package com.example.maciej.projectgame.Game.Objects.Ships;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.maciej.projectgame.Game.Animations.Animation;
import com.example.maciej.projectgame.Game.Animations.AnimationManager;
import com.example.maciej.projectgame.Game.Constants;
import com.example.maciej.projectgame.Game.Objects.Bullets.BulletManager;
import com.example.maciej.projectgame.Game.Objects.GameObject;
import com.example.maciej.projectgame.R;

public class RectPlayer implements GameObject {


    private Rect rectangle;
    private int color;

    private BulletManager bullets;

    private Animation Index;
    private Animation flyRight;
    private Animation flyLeft;
    private AnimationManager animManager;

    public RectPlayer(Rect rectangle, int color){
        this.rectangle= rectangle;
        this.color = color;
        bullets = new BulletManager(500,22,4,Color.BLUE,rectangle,false);

        BitmapFactory bf = new BitmapFactory();
        Bitmap Image = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienship);
        Bitmap flyRight1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienshipturnright);
        Bitmap flyLeft1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienshipturnleft);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Image = Bitmap.createBitmap(Image, 0, 0, Image.getWidth(), Image.getHeight(), m, false);
        flyRight1 = Bitmap.createBitmap(flyRight1, 0, 0, flyRight1.getWidth(), flyRight1.getHeight(), m, false);
        flyLeft1 = Bitmap.createBitmap(flyLeft1, 0, 0, flyLeft1.getWidth(), flyLeft1.getHeight(), m, false);

        Index = new Animation(new Bitmap[]{Image},2);
        flyRight = new Animation(new Bitmap[]{flyRight1},0.5f);
        flyLeft = new Animation(new Bitmap[]{flyLeft1},0.5f);
        animManager = new AnimationManager(new Animation[]{Index, flyLeft,flyRight});

    }

    public Rect getRectangle(){return rectangle;}


    public BulletManager getBullets(){return bullets;}

    @Override
    public void draw(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setColor(color);
//        canvas.drawRect(rectangle,paint);

        bullets.draw(canvas);
        animManager.draw(canvas,rectangle);

    }

    @Override
    public void update( ) {
        animManager.update();
    }

    public void update(Point point){
        float oldLeft = rectangle.left;

        rectangle.set(point.x - rectangle.width()/2,point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
        bullets.update();

        int state = 0;
        if(rectangle.left - oldLeft >5)
            state = 1;
        else if(rectangle.left - oldLeft <-5)
            state = 2;

        animManager.playAnim(state);
        animManager.update();
    }
}
