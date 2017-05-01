package com.myowntrainer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

public class MainPanel extends SurfaceView implements SurfaceHolder.Callback {
	
	MainActivity context;
	PanelThread _thread;	  
	public Paint paint = new Paint();
	public Bitmap applogo = BitmapFactory.decodeResource(getResources(), R.drawable.applogo);
	public Bitmap connecttile = BitmapFactory.decodeResource(getResources(), R.drawable.connecttile);
	public Bitmap dailychallengetile = BitmapFactory.decodeResource(getResources(), R.drawable.dailychallengetile);
	public Bitmap friendstile = BitmapFactory.decodeResource(getResources(), R.drawable.friendstile);
	public Bitmap leaderboardstile = BitmapFactory.decodeResource(getResources(), R.drawable.leaderboardstile);
	public Bitmap myfitnesstile = BitmapFactory.decodeResource(getResources(), R.drawable.myfitnesstile);
	public Bitmap exittile = BitmapFactory.decodeResource(getResources(), R.drawable.exittile);
	public Bitmap todaytile = BitmapFactory.decodeResource(getResources(), R.drawable.todaytile);
	
	Rect exit = new Rect(50,50,350,400);
	Rect myfitness = new Rect(140,320,600,850);
	Rect connect = new Rect(500,40,900,500);
	Rect friends = new Rect(620,470,1000,900);
	Rect today = new Rect(350,775,750,1250);
	Rect dailyChallenge = new Rect(100,1120,500,1600);
	Rect leaderboards = new Rect(525,1150,1000,1700);
	
	//Constructors
	public MainPanel(Context context) { 
		super(context);
		this.context = (MainActivity) context;	        
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
			applogo.recycle();
			connecttile.recycle();
			dailychallengetile.recycle();
			exittile.recycle();
			friendstile.recycle();
			leaderboardstile.recycle();
			myfitnesstile.recycle();
			todaytile.recycle();
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
		if (exit.contains(intX, intY)) {
			context.finish();
		}
		else if (myfitness.contains(intX, intY)) {
			context.MyFitness();
		}
		else if (connect.contains(intX, intY)) {
			context.findMyo();
		}
		else if (friends.contains(intX, intY)) {
			context.Friends();
		}
		else if (today.contains(intX, intY)) {
			context.Today();
		}
		else if (dailyChallenge.contains(intX, intY)) {
			context.DailyChallenge();
		}
		else if (leaderboards.contains(intX, intY)) {
			context.Leaderboards();
		}
	}
	
	private void screenMoved(float eventX, float eventY) {

	}
	
	private void screenReleased(float eventX, float eventY) {

	}

	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(exittile, null, exit, paint);
		canvas.drawBitmap(myfitnesstile, null, myfitness, paint);
		canvas.drawBitmap(connecttile, null, connect, paint);
		canvas.drawBitmap(friendstile, null, friends, paint);
		canvas.drawBitmap(todaytile, null, today, paint);
		canvas.drawBitmap(dailychallengetile, null, dailyChallenge, paint);
		canvas.drawBitmap(leaderboardstile, null, leaderboards, paint);
	}
}