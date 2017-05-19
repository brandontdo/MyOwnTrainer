package com.example.sportshack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ExcerciseListPanel extends SurfaceView implements SurfaceHolder.Callback  {
    ExcerciseListActivity context;
    PanelThread _thread;      
    public Paint paint = new Paint();

    //Constructors
    public ExcerciseListPanel(Context context) { 
        super(context);
        this.context = (ExcerciseListActivity) context;          
        this.setBackgroundColor(Color.WHITE);
    }

    //Essentially the main method, runs multiple times and is where updating and drawing is done.
    @Override 
    public void onDraw(Canvas canvas) {
        //do drawing stuff here.
        update();
        draw(canvas, paint);            
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false); //Allows us to use invalidate() to call onDraw()
        _thread = new PanelThread(getHolder(), this); //Start the thread that
        _thread.setRunning(true);                     //will make calls to 
        _thread.start();                              //onDraw()
        init();
    }
    
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        try {
            _thread.setRunning(false);                //Tells thread to stop
            _thread.join();                           //Removes thread from mem.
        } catch (InterruptedException e) {}
    }
    

    public void init() {

    }   

    public void update() {

    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN:
            screenTouched(eventX, eventY);
            return true;
          case MotionEvent.ACTION_MOVE:
            screenMoved(eventX, eventY);
            break;
          case MotionEvent.ACTION_UP:
            screenReleased(eventX, eventY);
            break;
          default:
            return false;
        }
        return true;
    }
    
    private void screenTouched(float eventX, float eventY) {
        int intX = (int) eventX, intY = (int) eventY;
        
    }
    
    private void screenMoved(float eventX, float eventY) {

    }
    
    private void screenReleased(float eventX, float eventY) {

    }

    public void draw(Canvas canvas, Paint paint) {
        int x = 20, y = 25, boxWidth = 50, boxHeight = 75;
    }
}
