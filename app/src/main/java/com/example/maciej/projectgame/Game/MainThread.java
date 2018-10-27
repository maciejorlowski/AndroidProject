package com.example.maciej.projectgame.Game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    public static final int MAX_FPS=30;
    private double avargeFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public void setRunning(boolean running){
        this.running = running;}

    public MainThread(SurfaceHolder surfaceHolder,GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run(){
        long startTime;
        long timeMills = 1000/MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long TotalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while (running){
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch (Exception e){e.printStackTrace();}
            finally {
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){e.printStackTrace();     }
                }
            }
            timeMills = (System.nanoTime() - startTime)/1000000;
            waitTime =  targetTime = timeMills;
            try {
                if (waitTime > 0)
                    this.sleep(waitTime);
                }catch (Exception e){e.printStackTrace();}
            TotalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == MAX_FPS) {
                avargeFPS = 1000 / ((TotalTime / frameCount) / 1000000);
                frameCount = 0;
                TotalTime = 0;
                System.out.println(avargeFPS);
                }
            }
        }

    }




