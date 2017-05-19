package com.myowntrainer;

import java.text.Format;
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

public class ExercisePanel extends SurfaceView implements SurfaceHolder.Callback {
    ExerciseActivity context;
    PanelThread _thread;      
    public Paint paint = new Paint();
    Rect reset = new Rect(5, 20, 350, 350);
    Rect done = new Rect(720, 45, 1075, 380);
    Random r = new Random();
    
    public Bitmap bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
    public Bitmap blacktile = BitmapFactory.decodeResource(getResources(), R.drawable.greytile);
    public Bitmap bluetile = BitmapFactory.decodeResource(getResources(), R.drawable.bluetile);
    public Bitmap redtile = BitmapFactory.decodeResource(getResources(), R.drawable.redtile);
    public Bitmap reseticon = BitmapFactory.decodeResource(getResources(), R.drawable.reseticon);
    public Bitmap doneicon = BitmapFactory.decodeResource(getResources(), R.drawable.doneicon);
    
    String exercise;
    
    ExerciseCounter counter;
    
    //Constructors
    public ExercisePanel(Context context, String exercise) { 
        super(context);
        this.context = (ExerciseActivity)context;          
        this.exercise = exercise;
        this.setBackgroundColor(Color.WHITE);
        
        if (exercise.equals(ExerciseListPanel.burpeeStr)) {
          counter = new BurpeesCounter();
        } else if (exercise.equals(ExerciseListPanel.joggingStr)) {
          counter = new JoggingCounter();
        } else if (exercise.equals(ExerciseListPanel.pushupStr)) {
          counter = new PushupCounter();
        } else if (exercise.equals(ExerciseListPanel.jumpingjackStr)) {
          counter = new JumpingjacksCounter();
        } else if (exercise.equals(ExerciseListPanel.situpStr)) {
          counter = new SitupsCounter();
        } else {
          counter = new JoggingCounter();
        }
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
            _thread.setRunning(false); //Tells thread to stop
            _thread.join();            //Removes thread from mem.
        } catch (InterruptedException e) {}
    }
    
    private int col_num = 1;

    public void init() {
      int col_num = r.nextInt(3 - 1 + 1) + 1;
    }

    public void update() {
      counter.update(context.roll, context.pitch, context.yaw);
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
        int intX = (int)eventX, intY = (int)eventY;
        if (reset.contains(intX, intY)) {
          counter.setRep(0);
    } else if (done.contains(intX, intY)) {
      context.done(counter.getReps());
    }
    }
    
    private void screenMoved(float eventX, float eventY) {
    }
    
    private void screenReleased(float eventX, float eventY) {
    }

    public void draw(Canvas canvas, Paint paint) {
      canvas.drawBitmap(bg, null, new Rect(0, 0, 1080, 1920), paint);
      
      paint.setTextSize(225);

      canvas.drawBitmap(reseticon, null, reset, paint);
      canvas.drawBitmap(doneicon, null, done, paint);
      
      if (col_num == 1)
        canvas.drawBitmap(blacktile, null, new Rect(220, 430, 860, 1120), paint);
      else if (col_num == 2)
        canvas.drawBitmap(bluetile, null, new Rect(220, 430, 860, 1120), paint);
      else if (col_num == 3)
        canvas.drawBitmap(redtile, null, new Rect(220, 430, 860, 1120), paint);
            
        canvas.drawBitmap(blacktile, null, new Rect(220, 430, 860, 1120), paint);
      if (exercise != null) {
        paint.setColor(Color.WHITE);
        
        if (exercise.equals(ExerciseListPanel.joggingStr)) {
          canvas.drawText(String.format("%04.0f", counter.getReps()), 290, 800, paint);
          canvas.drawText("m", 440, 970, paint);
        } else {
          canvas.drawText(String.format("%03.0f", counter.getReps()), 350, 860, paint);
        }
        
        paint.setTextSize(100);
        paint.setColor(Color.BLACK);
        if (context.exercise.equals(ExerciseListPanel.jumpingjackStr)) {
          canvas.drawText(context.exercise, 155, 1300, paint);
        } else {
          canvas.drawText(context.exercise, 240, 1300, paint);
        }
      }
    }
}
