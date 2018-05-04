package com.derik.demo.a_first.attribute;

import android.app.Activity;
import android.os.Bundle;

import com.derik.demo.R;

public class AttributeTest extends Activity{

	AlphaImageView alphaImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arrtibute);
		alphaImageView = (AlphaImageView) findViewById(R.id.alpha_image);

	}

	@Override
	public void onStart(){
		super.onStart();
		alphaImageView.setDuration(10000);
	}
}
