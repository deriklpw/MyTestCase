package com.example.screenapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends Activity implements OnClickListener {

	private static final String TAG = ViewFlipperActivity.class.getSimpleName();
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	ViewFlipper viewFlipper;
	private QuickContactBadge contactBadge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(this);
		button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(this);
		contactBadge = (QuickContactBadge)findViewById(R.id.quickContactBadge1);
		contactBadge.assignContactFromPhone("13088867651", false);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:
			finish();
			break;
		case R.id.button2:
			viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
			viewFlipper.addView(getIamgeView(R.drawable.p1));
			viewFlipper.addView(getIamgeView(R.drawable.p2));
			viewFlipper.addView(getIamgeView(R.drawable.p3));
			viewFlipper.addView(getIamgeView(R.drawable.p4));
			viewFlipper.startFlipping();
			break;
		case R.id.button3:
			break;
		case R.id.button4:
			break;
		case R.id.button5:
			break;
		default:
			break;

		}
	}
	
	public ImageView getIamgeView(int id){
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(id);
		return imageView;
	}
}
