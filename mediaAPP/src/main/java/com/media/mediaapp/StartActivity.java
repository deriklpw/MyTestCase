package com.media.mediaapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class StartActivity extends Activity {

	private VideoView video;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);

		video = (VideoView) findViewById(R.id.videoView);
		Uri uri = Uri.parse("android.resource://com.media.mediaapp/"
				+ R.raw.mingrisoft);
		video.setVideoURI(uri);
		video.requestFocus();

		try {
			video.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		video.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(StartActivity.this,
						MainActivity.class);
				startActivity(intent);
				StartActivity.this.finish();
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		if (video.isPlaying()) {
			video.pause();
		}
		super.onDestroy();
	}
}
