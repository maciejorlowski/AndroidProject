package com.example.maciej.projectgame.Game.Objects.Ships;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.maciej.projectgame.Game.Animations.AnimationManager;
import com.example.maciej.projectgame.Game.Constants;
import com.example.maciej.projectgame.Game.Objects.Bullets.BulletManager;
import com.example.maciej.projectgame.Game.Objects.GameObject;

import java.util.Random;

public class EnemyShip implements GameObject {
    private Rect ship;
    private int color;
    private int dir;
    private AnimationManager animManager;

    private BulletManager bullets;

    public EnemyShip(int height, int weight, int color, int Startx, int StartY, AnimationManager anim)
    {
        Random rand = new Random();
        dir= rand.nextInt()%2;
        this.color = color;
        ship = new Rect(Startx,StartY,Startx+weight,StartY+height);
        bullets = new BulletManager(500,22,4,Color.DKGRAY,ship,true);
        animManager = anim;
    }
    public int getDir(){return dir;}

    public void changeDir()
    {
        dir=(dir+1)%2;
    }

    public Rect getShip(){
        return ship;
    }

    public void pushX(float x,int dir)
    {
        if(dir == 0)
        {
            if(ship.left<0)
                changeDir();
            else {
                ship.left -= x;
                ship.right -= x;
            }

        }
        else {
            if ((ship.right +x) > Constants.SCREEN_WIDTH){
                changeDir();
            }
            else{
                ship.left += x;
                ship.right += x;
            }

        }
    }

    public BulletManager getBullets(){return bullets;}

    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(ship,paint);
        bullets.draw(canvas);
        animManager.draw(canvas,ship);
    }



    @Override
    public void update() {
        bullets.update();

        animManager.playAnim(dir+1);
        animManager.update();

    }

    public int getCenterX(){return ship.centerX();}
    public int getCenterY(){return ship.centerY();}

}
