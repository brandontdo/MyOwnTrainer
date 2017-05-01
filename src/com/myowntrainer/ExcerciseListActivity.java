package com.myowntrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class ExcerciseListActivity extends Activity {

    public static String excercise_entered = null;
	LinearLayout ll;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        ll = new LinearLayout(this);
        ll.addView(new ExcerciseListPanel(this));
        setContentView(ll);
    }

    public void Exercise(String str) {
        Intent intent = new Intent(this, ExcerciseActivity.class);
        excercise_entered = str;
        this.startActivity(intent);
    }
}
