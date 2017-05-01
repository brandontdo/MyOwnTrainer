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

public class ExcercisePanel extends SurfaceView implements SurfaceHolder.Callback {
        ExcerciseActivity context;
        PanelThread _thread;      
        public Paint paint = new Paint();
        Rect reset = new Rect(740,45, 1075,380);
        Rect done = new Rect(5,20, 400,400);
        Random r = new Random();
        public Bitmap blacktile = BitmapFactory.decodeResource(getResources(), R.drawable.greytile);
        public Bitmap bluetile = BitmapFactory.decodeResource(getResources(), R.drawable.bluetile);
        public Bitmap redtile = BitmapFactory.decodeResource(getResources(), R.drawable.redtile);
        public Bitmap reseticon = BitmapFactory.decodeResource(getResources(), R.drawable.reseticon);
        public Bitmap doneicon = BitmapFactory.decodeResource(getResources(), R.drawable.doneicon);
        
        String exercise;
        //Constructors
        public ExcercisePanel(Context context, String exercise) { 
            super(context);
            this.context = (ExcerciseActivity) context;          
            this.exercise = exercise;
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
            if (reset.contains(intX, intY)) {
    			context.setReps(0);
    		}
            else if (done.contains(intX, intY)) {
    			//FILL IN
    		}
        }
        
        private void screenMoved(float eventX, float eventY) {

        }
        
        private void screenReleased(float eventX, float eventY) {

        }

        public void draw(Canvas canvas, Paint paint) {
        	int num = r.nextInt(3 - 1 + 1) + 1;
        	paint.setTextSize(225);

        	canvas.drawBitmap(reseticon,null,reset,paint);
        	canvas.drawBitmap(doneicon,null,done,paint);
        	
        	if(num == 1)
        		canvas.drawBitmap(blacktile,null,new Rect(220,430,860,1120), paint);
        	else if(num == 2)
        		canvas.drawBitmap(bluetile,null,new Rect(220,430,860,1120), paint);
        	else if(num == 3)
        		canvas.drawBitmap(redtile,null,new Rect(220,430,860,1120), paint);
        		
        	if(exercise!=null){
        		paint.setColor(Color.WHITE);
        		canvas.drawText("" + context.reps, 475, 840, paint);
        		paint.setTextSize(100);
        		paint.setColor(Color.BLACK);
        		if(exercise.equals("JUMPING JACK"))
        			canvas.drawText(context.exercise, 230, 1250, paint);
        		else 
        			canvas.drawText(context.exercise, 360, 1250, paint);
        	}
        }
}
