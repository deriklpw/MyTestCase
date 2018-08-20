package com.derik.demo.media.soundpool;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.derik.demo.R;

import java.util.HashMap;

public class SoundPoolTestActivity extends Activity {

    private HashMap<String, Integer> soundMap;
    private SoundPool soundPool;

    private Button btn1;
    private  Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool_test);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);

        soundMap = new HashMap<>();
        soundPool = new SoundPool(11, AudioManager.STREAM_MUSIC, 0);
        soundMap.put("ui1", soundPool.load(this, R.raw.ui1, 1));
        soundMap.put("ui2", soundPool.load(this, R.raw.ui2, 1));
        soundMap.put("ui3", soundPool.load(this, R.raw.ui3, 1));
        soundMap.put("error01", soundPool.load(this, R.raw.error01, 1));
        soundMap.put("error10", soundPool.load(this, R.raw.error10, 1));
        soundMap.put("w01", soundPool.load(this, R.raw.w01, 1));
        soundMap.put("w03", soundPool.load(this, R.raw.w03, 1));
        soundMap.put("w04", soundPool.load(this, R.raw.w04, 1));
        soundMap.put("w05", soundPool.load(this, R.raw.w05, 1));
        soundMap.put("button6", soundPool.load(this, R.raw.button6, 1));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(soundMap.get("ui1"), 1, 1, 0, 0, 1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(soundMap.get("w01"), 1, 1, 0, 0, 1);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(soundMap.get("w05"), 1, 1, 0, 0, 1);
            }
        });

    }
}
