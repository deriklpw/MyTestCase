package com.media.mediaapp;

import java.io.File;

import android.app.Activity;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VedioViewActivity extends Activity {

	private VideoView video;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videoview);
		video = (VideoView) findViewById(R.id.video1);

		File file = new File(Environment.getExternalStorageDirectory()
				.getPath(), "Girls_Generation.mp4");

		MediaController mc = new MediaController(this);
		if (file.exists()) {
			video.setVideoPath(file.getAbsolutePath());
			video.setMediaController(mc);
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
					Toast.makeText(VedioViewActivity.this, "播放完毕",
							Toast.LENGTH_LONG).show();
				}
			});
		} else {
			Toast.makeText(VedioViewActivity.this, "文件不存在", Toast.LENGTH_LONG)
					.show();
		}
	}
}
