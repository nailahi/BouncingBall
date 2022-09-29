package com.example.bouncingball;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class PoolView extends SurfaceView implements SurfaceHolder.Callback{

    PoolThread thread;
    public PoolView(Context context) {
        super(context);
    }
    public PoolView(Context context, AttributeSet attrs){
        super(context,attrs);
        getHolder().addCallback(this);
    }

    public PoolView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated( SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    thread = new PoolThread(holder, this.getWidth(), this.getHeight());
    thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    thread.setRunning(false);
    boolean retry = true;
    while(retry){
        try{
            thread.join();
            retry = false;
        }catch(InterruptedException ex){

        }
        }
    }
}
