package ru.samsung.itschool.mdev.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private float x,y,r;
    private MyThread myThread;
    private boolean touchFlag;

    public TestSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
            myThread = new MyThread();
            myThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.x = event.getX();
        this.y = event.getY();
        this.r = 0;
        this.touchFlag = true;
        return true;
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            Paint paint = new Paint();
            paint.setColor(Color.YELLOW);
            while(true) {
                Canvas canvas = getHolder().lockCanvas();
                canvas.drawColor(Color.BLUE);
                r += touchFlag ? 5 : 0;
                canvas.drawCircle(x,y,r,paint);
                getHolder().unlockCanvasAndPost(canvas);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
