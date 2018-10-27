package com.example.maciej.projectgame.Game.Objects.Ships;
import android.graphics.Canvas;
import android.util.Log;

import com.example.maciej.projectgame.Game.Animations.AnimationManager;
import com.example.maciej.projectgame.Game.Animations.EnemyShipAnimation;
import com.example.maciej.projectgame.Game.Constants;
import com.example.maciej.projectgame.Game.Objects.Bullets.Bullet;
import com.example.maciej.projectgame.Game.Objects.Bullets.BulletManager;
import com.example.maciej.projectgame.Game.Objects.Ships.EnemyShip;

import java.util.ArrayList;
import java.util.Random;

public class EnemyShipsManager {
    private ArrayList<EnemyShip> ships;
    private int color;
    private int shipsHeight;
    private int shipsWeight;
    private long startTime;
    private int ammount = 2;

    private ArrayList<BulletManager> bullets;

    private static final String TAG = "EnemyManager";


    public EnemyShipsManager(int height,int weight,int color)
    {
        this.color = color;
        this.shipsHeight = height;
        this.shipsWeight = weight;

        startTime = System.currentTimeMillis();

        ships=new ArrayList<>();
        populateShips(ammount);
        bullets = new ArrayList<>();
    }
    public void populateShips(int ammount)
    {
        while(ammount>0)
        {
            Random rand = new Random();
            int xStart = rand.nextInt(Constants.SCREEN_WIDTH);
            int yStart = rand.nextInt(Constants.SCREEN_WIDTH/3);
            ships.add(new EnemyShip(shipsHeight,shipsWeight,color,xStart,yStart,new AnimationManager(new EnemyShipAnimation().getAnimations())));
            ammount--;
            Log.d(TAG,"update "+Runtime.getRuntime().freeMemory() + "  " + Runtime.getRuntime().totalMemory() + "  " + Runtime.getRuntime().maxMemory());

        }
    }

    public ArrayList<EnemyShip> getShips(){return ships;}

    public void addBullets(BulletManager bullet){bullets.add(bullet);}

    public ArrayList<BulletManager> getBullets(){return bullets;}

    public void update()
    {
        if(startTime<Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;
        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT/10000.0f;
        for (EnemyShip o : ships){
            o.pushX((speed + elapsedTime)/4,o.getDir());
            o.update();
        }
        for (BulletManager o : bullets)
        {
            for (Bullet ob: o.getBullets()) {
                ob.pushY((speed + elapsedTime) / 4);
                ob.update();
            }
        }



    }
    public void draw(Canvas canvas){
        for(EnemyShip o : ships)
            o.draw(canvas);
        for(BulletManager o : bullets)
            o.draw(canvas);


    }

    public void clean()
    {
        bullets.clear();
    }
}
