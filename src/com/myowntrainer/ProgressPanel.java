package com.myowntrainer;

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

public class ProgressPanel extends SurfaceView implements SurfaceHolder.Callback {
        ProgressActivity context;
        PanelThread _thread;      
        public Paint paint = new Paint();
        public Bitmap burpee = BitmapFactory.decodeResource(getResources(), R.drawable.burpeestile);
        public Bitmap running = BitmapFactory.decodeResource(getResources(), R.drawable.runningtile);
        public Bitmap pushup = BitmapFactory.decodeResource(getResources(), R.drawable.pushuptile);
        public Bitmap jumpingjack = BitmapFactory.decodeResource(getResources(), R.drawable.jumpingjacktile);
        public Bitmap situp = BitmapFactory.decodeResource(getResources(), R.drawable.situptile);
        Rect burpeeRect = new Rect(150,75,560,500);
        Rect runningRect =  new Rect(400,400,830,820);
        Rect pushupRect = new Rect(150,720,560,1140);
        Rect jumpingjackRect = new Rect(525,75,880,500);
        Rect situpRect = new Rect(525,720,880,1140);
        
        //Constructors
        public ProgressPanel(Context context) { 
            super(context);
            this.context = (ProgressActivity) context;          
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
    		canvas.drawBitmap(burpee, null, burpeeRect, paint);
    		canvas.drawBitmap(running, null, runningRect, paint);
    		canvas.drawBitmap(pushup, null, pushupRect, paint);
    		canvas.drawBitmap(jumpingjack, null, jumpingjackRect, paint);
    		canvas.drawBitmap(situp, null, situpRect, paint);
        }
}
