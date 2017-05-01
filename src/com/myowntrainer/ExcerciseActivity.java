package com.myowntrainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class ExcerciseActivity extends Activity {

    LinearLayout ll;
    String exercise = ExcerciseListActivity.excercise_entered;
    int reps;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        ll = new LinearLayout(this);
        ll.addView(new ExcercisePanel(this, exercise));
        setContentView(ll);
        reps = 4;
    }
    
    public void setReps(int val){
    	this.reps = val;
    }
}
