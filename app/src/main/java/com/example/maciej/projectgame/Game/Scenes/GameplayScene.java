package com.example.maciej.projectgame.Game.Scenes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.maciej.projectgame.Database.ResultDAO;
import com.example.maciej.projectgame.Game.Constants;
import com.example.maciej.projectgame.Game.Effects.BubbleManager;
import com.example.maciej.projectgame.Game.Objects.Bullets.BulletManager;
import com.example.maciej.projectgame.Game.Objects.Ships.EnemyShip;
import com.example.maciej.projectgame.Game.Objects.Ships.EnemyShipsManager;
import com.example.maciej.projectgame.Game.Objects.Ships.RectPlayer;
import com.example.maciej.projectgame.Game.OrientationData;
import com.example.maciej.projectgame.Game.ResultDTO;
import com.example.maciej.projectgame.Menu;

public class GameplayScene implements Scene {

    private RectPlayer player;
    private Point playerPoint;
    private EnemyShipsManager enemyShipsManager;
    private boolean movingPlayer = false;
    private boolean GameOver = false;
    private long GameOverTime;
    private Rect r = new Rect();

    private int level = 1;

    private BubbleManager bubbleManager;
    private OrientationData orientationData;
    private long frameTime;
    private static final String TAG = "GameplayScene";

    private Context context;



    public GameplayScene(Context context){
        Log.d(TAG,"start " + Runtime.getRuntime().freeMemory() + "  " + Runtime.getRuntime().totalMemory() + "  " + Runtime.getRuntime().maxMemory());

        this.context = context;
        player = new RectPlayer(new Rect(Constants.SCREEN_WIDTH/2,1000,Constants.SCREEN_WIDTH/2+100,1550), Color.rgb(0,100,155));
        playerPoint = new Point(Constants.SCREEN_WIDTH/2,1700);
        player.update(playerPoint);

        enemyShipsManager = new EnemyShipsManager(100,100,Color.RED);

        orientationData = new OrientationData();
        orientationData.register();
        frameTime = System.currentTimeMillis();
        bubbleManager = new BubbleManager();
    }

    public void summary()
    {
//        SQLiteAdapter.getInstance(context).addResult(null,level);
        new ResultDAO().insert(new ResultDTO("unkown",level));
        Intent intent = new Intent(context,Menu.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(intent);
    }

    @Override
    public void update() {

        if(!GameOver)
        {
            if(frameTime < Constants.INIT_TIME)
                frameTime = Constants.INIT_TIME;
            int elapsedTime = (int) (System.currentTimeMillis()- frameTime);
            frameTime = System.currentTimeMillis();
            if(orientationData.getOrientation() != null && orientationData.getStartOrientation() != null){
                float pitch = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1];
                float roll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];

                float xSpeed = 2 * roll * Constants.SCREEN_WIDTH/1000f;
                float ySpeed = pitch * Constants.SCREEN_HEIGHT/1000f;

                playerPoint.x += Math.abs(xSpeed*elapsedTime)>5 ? xSpeed*elapsedTime : 0;
                playerPoint.y -= Math.abs(ySpeed*elapsedTime)>5 ? ySpeed*elapsedTime : 0;
            }


            if(playerPoint.x<0)
                playerPoint.x=0;
            else if(playerPoint.x>Constants.SCREEN_WIDTH)
                playerPoint.x=Constants.SCREEN_WIDTH;
            if(playerPoint.y<Constants.SCREEN_HEIGHT/3*2)
                playerPoint.y=Constants.SCREEN_HEIGHT/3*2;
            else if(playerPoint.y>Constants.SCREEN_HEIGHT)
                playerPoint.y=Constants.SCREEN_HEIGHT;

            player.update(playerPoint);
            enemyShipsManager.update();
            bubbleManager.update();



            for(EnemyShip o: enemyShipsManager.getShips())
            {
                if(player.getBullets().shipColide(o)) {
                    enemyShipsManager.addBullets(o.getBullets());
                    bubbleManager.addBubble(o.getCenterX(),o.getCenterY());
                    enemyShipsManager.getShips().remove(o);
                }
                if(o.getBullets().playerColide(player))
                    GameOver= true;
                GameOverTime = System.currentTimeMillis();
            }

            for(BulletManager o : enemyShipsManager.getBullets())
            {
                if(o.playerColide(player))
                    GameOver= true;
            }

            if(enemyShipsManager.getShips().isEmpty()){
                enemyShipsManager.clean();
                player.getBullets().clean();
                enemyShipsManager.populateShips(++level);
            }

        }
//        Log.d(TAG,"update "+Runtime.getRuntime().freeMemory() + "  " + Runtime.getRuntime().totalMemory() + "  " + Runtime.getRuntime().maxMemory());

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.LTGRAY);
        player.draw(canvas);
        enemyShipsManager.draw(canvas);
        bubbleManager.draw(canvas);

        Paint paint= new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("level"+level,0,100 + paint.descent() - paint.ascent(),paint);

        if(GameOver)
        {
            Paint paint2 = new Paint();
            paint2.setTextSize(150);
            paint2.setColor(Color.MAGENTA);
            drawCenterText(canvas,paint2,"GAME OVER");
        }

    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE=0;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!GameOver && player.getRectangle().contains((int)event.getX(),(int)event.getY()))
                    movingPlayer = true;
                if(GameOver && System.currentTimeMillis() - GameOverTime >=3600) {
                    summary();
                    GameOver = false;
                    orientationData.newGame();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!GameOver && movingPlayer)
                    playerPoint.set((int)event.getX(),1700);
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }
    }

    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.rgb(255, 255, 255));
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

}
