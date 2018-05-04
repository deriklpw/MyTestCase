package com.media.mediaapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.VideoView;

public class LooperThreadActivity extends Activity {

	private VideoView video;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loop);
		
		Handler handler=new Handler();
		Message message= new Message();
		Bundle bundle = new Bundle();
		bundle.putCharSequence("test1", "Handler消息测试。");
		message.setData(bundle);
		
	}
	
	@Override
	protected void onDestroy() {
		if (video.isPlaying()) {
			video.pause();
		}
		super.onDestroy();
	}
}
