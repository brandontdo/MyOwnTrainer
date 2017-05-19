package com.example.sportshack;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class PanelThread extends Thread {
	
    private SurfaceHolder _surfaceHolder;
    private SurfaceView _panel;
    private boolean _run = false;
    float x = 100, y = 100;
    MotionEvent e;

    public PanelThread(SurfaceHolder surfaceHolder, SurfaceView panel) {
        _surfaceHolder = surfaceHolder;
        _panel = panel;
    }

    public void setRunning(boolean run) { // Allow us to stop the thread
        _run = run;
    }

    @Override
    public void run() {
        Canvas c;
        while (_run) { // when setRunning(false) occurs, _run is
            c = null;  // set to false and loop ends, stopping thread
            try {
                c = _surfaceHolder.lockCanvas(null);
                synchronized (_surfaceHolder) {
                    update(); //insert methods to modify positions of items in onDraw()
                }
            } finally {
                if (c != null) {
                    _surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
    
    public void update() {
        _panel.postInvalidate();
        
    }
    
    public void sendEvent(MotionEvent e) {
        //this.e = e;
    }
    
}