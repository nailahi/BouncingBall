package com.example.bouncingball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Random;

public class PoolThread extends Thread{
    boolean running = true;
    SurfaceHolder holder;
    int x,y;
    int w,h;

    public PoolThread(SurfaceHolder holder, int w, int h){
        this.holder = holder;
        this.w = w;
        this.h = h;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    public void run(){
        Canvas canvas = null;
        Paint backPaint = new Paint();
        backPaint.setColor(Color.BLUE);
        Paint forePaint = new Paint();
        forePaint.setColor(Color.WHITE);
        Paint borPaint = new Paint();
        borPaint.setColor(Color.BLACK);
        borPaint.setStyle(Paint.Style.STROKE);
        Random rand = new Random();
        long previousTime = System.currentTimeMillis();
        while(running){
            try{
                canvas = holder.lockCanvas(null);
                synchronized (holder){
                    long currentTime = System.currentTimeMillis();
                    double elapsedTime= currentTime - previousTime;
                    if(elapsedTime > 500){
                        previousTime = currentTime;
                        x = rand.nextInt(w);
                        y = rand.nextInt(h);
                        if(x == w-1){
                            x = -x;
                        }else if(y == h-1) {
                            y = -y;
                        }
                    }
                    canvas.drawRect(0,0,w,h,backPaint);
                    canvas.drawRect(0,0,w-1,h-1,borPaint);
                    canvas.drawCircle(x,y,40,forePaint);
                }
            } finally{
                if(canvas != null){
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
