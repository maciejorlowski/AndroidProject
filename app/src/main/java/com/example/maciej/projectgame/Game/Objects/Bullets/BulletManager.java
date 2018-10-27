package com.example.maciej.projectgame.Game.Objects.Bullets;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.example.maciej.projectgame.Game.Constants;
import com.example.maciej.projectgame.Game.Objects.Ships.EnemyShip;
import com.example.maciej.projectgame.Game.Objects.Ships.RectPlayer;
import com.example.maciej.projectgame.R;

import java.util.ArrayList;

public class BulletManager {
    //higher index = lower on screen = higher y value
    private ArrayList<Bullet> bullets;
    private int rate;              //odstep pomiedzy bulletami
    private int bulletHeight;           //wysokosc bulletu
    private int bulletWeight;           //szerokosc bulletu
    private int color;
    private boolean enemy;

    private Rect ship;
    private long startTime;



    public BulletManager(int rate,int bulletHeight,int bulletWeight ,int color,Rect ship,boolean enemy){
        this.rate = rate;
        this.bulletHeight = bulletHeight;
        this.color = color;
        this.bulletWeight = bulletWeight;
        this.ship=ship;
        this.enemy=enemy;

        startTime = System.currentTimeMillis();
        bullets = new ArrayList<>();
    }

    public ArrayList<Bullet> getBullets(){return bullets;}

    public boolean playerColide(RectPlayer player)
    {
        if(enemy==false)
            return false;
        for(Bullet o: bullets)
        {
            if(o.playerCollide(player))
                return true;
        }
        return  false;
    }

    public boolean shipColide(EnemyShip ship)
    {
        if(enemy==true)
            return false;
        for(Bullet o:bullets)
        {
            if(o.shipCollide(ship))
                return true;
        }
        return false;
    }

    public void update(){
        if(enemy==true) {
            int elapsedTime = (int) (System.currentTimeMillis() - startTime);
            startTime = System.currentTimeMillis();
            float speed = Constants.SCREEN_HEIGHT / 10000.0f;
            for (Bullet o : bullets) {
                o.pushY((speed + elapsedTime) / 4);
            }
            if (bullets.isEmpty() || bullets.get(bullets.size() - 1).getRectangle().top - ship.bottom > rate) {
                Drawable image = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.bullet);
                bullets.add(new Bullet(bulletHeight, bulletWeight, color, ship.centerX(), ship.bottom, enemy,image));
            }
            if (bullets.get(0).getRectangle().top >= Constants.SCREEN_HEIGHT) {
                bullets.remove(0);
            }
        }
        else
        {
            int elapsedTime = (int) (System.currentTimeMillis() - startTime);
            startTime = System.currentTimeMillis();
            float speed = Constants.SCREEN_HEIGHT / 10000.0f;
            for (Bullet o : bullets) {
                o.pushY((speed + elapsedTime) / 4);
            }
            if (bullets.isEmpty() || ship.top - bullets.get(bullets.size() - 1).getRectangle().bottom > rate) {
                Drawable image = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.bullet);
                bullets.add(new Bullet(bulletHeight, bulletWeight, color, (ship.left+ship.right)/2, ship.top, enemy,image));
            }
            if (bullets.get(0).getRectangle().top <= 0) {
                bullets.remove(0);
            }

        }
        for(Bullet b : bullets)
            b.update();

    }

    public void draw(Canvas canvas){
        for(Bullet o : bullets)
            o.draw(canvas);

    }
    public void clean(){
        bullets.clear();
    }
}
