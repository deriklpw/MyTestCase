package com.media.mediaapp;

import java.io.File;
import java.util.HashMap;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SoundPoolActivity extends Activity {

	private SoundPool soundPool;
	private HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.soundpool);
		Button chimes = (Button) findViewById(R.id.button1);
		Button enter = (Button) findViewById(R.id.button2);
		Button notify = (Button) findViewById(R.id.button3);
		Button ding = (Button) findViewById(R.id.button4);
		Button ringout = (Button) findViewById(R.id.button5);
		soundPool = new SoundPool(5,AudioManager.STREAM_SYSTEM,0);
		
		soundmap.put(1, soundPool.load(this, R.raw.chimes, 1));
		soundmap.put(2, soundPool.load(this,R.raw.enter, 1));
		soundmap.put(3, soundPool.load(this,R.raw.notify, 1));
		soundmap.put(4, soundPool.load(this,R.raw.ding, 1));
		soundmap.put(5, soundPool.load(this,R.raw.ringout, 1));

		
		chimes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				soundPool.play(soundmap.get(1), 1, 1, 0, 0, 1);
			}
		});
		
		enter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				soundPool.play(soundmap.get(2), 1, 1, 0, 0, 1);
			}
		});
		
		notify.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				soundPool.play(soundmap.get(3), 1, 1, 0, 0, 1);
			}
		});
		
		ding.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				soundPool.play(soundmap.get(4), 1, 1, 0, 0, 1);
			}
		});
		
		ringout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				soundPool.play(soundmap.get(5), 1, 1, 0, 0, 1);
			}
		});
	}

	@Override
	protected void onDestroy() {
		soundPool.autoPause();
		soundPool.release();
		super.onDestroy();
	}

}
