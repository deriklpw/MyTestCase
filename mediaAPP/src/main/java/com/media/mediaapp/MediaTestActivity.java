package com.media.mediaapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MediaTestActivity extends Activity {

    private MediaPlayer player;
    private List<String> audioList = new ArrayList<String>();
    private int currentItem = 0;
    private Button pausebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediatest);

        final Button startbutton = (Button) findViewById(R.id.start);
        pausebutton = (Button) findViewById(R.id.pause);
        final Button stopbutton = (Button) findViewById(R.id.stop);
        final Button pre = (Button) findViewById(R.id.prev);
        final Button next = (Button) findViewById(R.id.next);
        player = new MediaPlayer();

        final AudioManager am = (AudioManager) MediaTestActivity.this.getSystemService(Context.AUDIO_SERVICE);
        MediaTestActivity.this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        SeekBar seekbar = (SeekBar) findViewById(R.id.seekbar1);
        seekbar.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        int progress = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekbar.setProgress(progress);

        final TextView tv = (TextView) findViewById(R.id.volume);
        tv.setText("当前音量：" + progress);

        audioList();
        player.setOnCompletionListener(new OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                nextMusic();
            }
        });

        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText("当前音量：" + progress);                        //显示改变后的音量
                am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_PLAY_SOUND);    //设置改变后的音量
            }
        });

        startbutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                playMusic(audioList.get(currentItem));
            }
        });

        pausebutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (player.isPlaying()) {
                    player.pause();
                    ((Button) v).setText("conti");
                } else {
                    player.start();
                    ((Button) v).setText("pause");
                }
            }
        });

        stopbutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (player.isPlaying()) {
                    player.stop();
                }
                pausebutton.setEnabled(false);
            }
        });

        next.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                nextMusic();
            }
        });

        pre.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                preMusic();
            }
        });

    }

    private void audioList() {
        getFiles("/sdcard/");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, audioList);
        ListView listview = (ListView) findViewById(R.id.listView1);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                currentItem = position;
                playMusic(audioList.get(currentItem));
            }
        });
    }

    private static String[] imageFormatSet = new String[]{".mp3", ".wav",
            ".3gp", ".aac"};

    private static boolean isAudioFile(String path) {
        for (String format : imageFormatSet) {
            if (path.endsWith(format)) {
                return true;
            }
        }
        return false;
    }

    private void getFiles(String url) {
        File files = new File(url);
        File[] file = files.listFiles();
        try {
            for (File f : file) {
                if (f.isDirectory()) {
                    getFiles(f.getAbsolutePath());
                } else {
                    if (isAudioFile(f.getPath())) {
                        audioList.add(f.getPath());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void playMusic(String path) {
        try {
            if (player.isPlaying()) {
                player.stop();
            }
            player.reset();
            player.setDataSource(path);
            player.prepare();
            player.start();
            pausebutton.setText("pause");
            pausebutton.setEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void nextMusic() {
        if (++currentItem >= audioList.size()) {
            currentItem = 0;
        }
        playMusic(audioList.get(currentItem));
    }

    void preMusic() {
        if (--currentItem >= 0) {
            if (currentItem >= audioList.size()) {
                currentItem = 0;
            }
        } else {
            currentItem = audioList.size() - 1;
        }
        playMusic(audioList.get(currentItem));
    }

    @Override
    protected void onDestroy() {
        if (player.isPlaying()) {
            player.stop();
        }
        player.release();
        super.onDestroy();
    }

}
