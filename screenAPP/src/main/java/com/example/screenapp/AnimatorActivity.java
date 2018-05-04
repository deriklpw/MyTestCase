package com.example.screenapp;

import java.io.File;
import java.io.FileDescriptor;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.XmlResourceParser;
import android.graphics.LinearGradient;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AnimatorActivity extends Activity implements OnClickListener {

	private static final String TAG = AnimatorActivity.class.getSimpleName();
	private Button button1;
	private Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animator);
		
		LinearLayout containerLayout = (LinearLayout)findViewById(R.id.container);
		containerLayout.addView(new MyAnimationView(this));
		
		
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:
			finish();
			break;
		case R.id.button2:
			
			break;
		default:
			break;

		}
	}
	
	public class MyAnimationView extends View{
		public MyAnimationView(Context context){
			super(context);
			ObjectAnimator colorAnimator = (ObjectAnimator)AnimatorInflater.loadAnimator(AnimatorActivity.this, R.animator.color_anim);
			colorAnimator.setEvaluator(new ArgbEvaluator());
			colorAnimator.setTarget(this);
			colorAnimator.start();
		}
	}
}
