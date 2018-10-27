package com.example.maciej.projectgame.Game.Objects.Bullets;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.maciej.projectgame.Game.Objects.Ships.EnemyShip;
import com.example.maciej.projectgame.Game.Objects.GameObject;
import com.example.maciej.projectgame.Game.Objects.Ships.RectPlayer;

public class Bullet implements GameObject {

    private Rect rectangle;
    private int color;
    private boolean enemy;

    private Drawable image;

    public Rect getRectangle(){return rectangle;}

    public void pushY(float y)
    {
        if(enemy==true)
        {
            rectangle.top += y;
            rectangle.bottom +=y;
        }
        else
        {
            rectangle.top -= y;
            rectangle.bottom -= y;
        }
    }

    public Bullet(int Height, int Weight, int color, int startX, int startY, boolean enemy, Drawable image)
    {
        this.color=color;
        this.enemy=enemy;
        this.image=image;

        //left,top,right,bottom
        if(enemy==true)
            rectangle = new Rect(startX,startY,startX+Weight,startY+Height);
        else
            rectangle = new Rect(startX,startY-Height,startX+Weight,startY);
    }

    public boolean playerCollide(RectPlayer player)
        {
           return Rect.intersects(rectangle,player.getRectangle());
        }

    public boolean shipCollide(EnemyShip ship)
    {
        return Rect.intersects(rectangle,ship.getShip());
    }


    @Override
    public void draw(Canvas canvas){
//        Paint paint = new Paint();
//        paint.setColor(color);
//        canvas.drawRect(rectangle,paint);
        image.draw(canvas);
    }

    @Override
    public void update(){
        image.setBounds(rectangle.left, rectangle.top, rectangle.left+4, rectangle.top+22);
    }
}


