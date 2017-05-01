package com.myowntrainer;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ChallengePanel extends SurfaceView implements SurfaceHolder.Callback {
    ChallengeActivity context;
    PanelThread _thread;      
    public Paint paint = new Paint();
    Random r = new Random();

    public Bitmap blacktile = BitmapFactory.decodeResource(getResources(), R.drawable.greytile);
    public Bitmap bluetile = BitmapFactory.decodeResource(getResources(), R.drawable.bluetile);
    public Bitmap redtile = BitmapFactory.decodeResource(getResources(), R.drawable.redtile);
    float length = 0.0f;
    float reps = 30.0f; //this will change depending on the backend.
    int num = 0;
    
    //Constructors
    public ChallengePanel(Context context) { 
        super(context);
        this.context = (ChallengeActivity) context;          
        this.setBackgroundColor(Color.WHITE);
        num = r.nextInt(3 - 1 + 1) + 1;
        System.out.println(num);
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
    	paint.setTextSize(150);
    	paint.setColor(Color.WHITE);
    	if(num == 1){
    		canvas.drawBitmap(blacktile,null,new Rect(220,430,860,1120), paint);
    		canvas.drawText("500m", 320, 780, paint);
    		canvas.drawText("Jog", 440, 960, paint);
    		length = (reps / 500.0f) * 690.0f;
    	}
    	else if(num == 2){
    		canvas.drawBitmap(bluetile,null,new Rect(220,430,860,1120), paint);
    		canvas.drawText("60", 290, 760, paint);
    		canvas.drawText("Burpees", 290, 940, paint);
    		length = (reps / 60.0f) * 690.0f;
    	}
    	else if(num == 3){
    		canvas.drawBitmap(redtile,null,new Rect(220,430,860,1120), paint);
    		canvas.drawText("80", 285, 760, paint);
    		canvas.drawText("Push-Up", 285, 940, paint);
    		length = (reps / 80.0f) * 690.0f;
    	}
    	paint.setColor(Color.BLACK);
    	canvas.drawRect(new Rect(200, 1170,900, 1200), paint);
    	paint.setColor(Color.CYAN);
    	canvas.drawRect(205, 1175,205+length, 1195, paint);
    }
}