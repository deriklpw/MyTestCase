package com.derik.myapps.component;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;

import com.derik.myapps.R;

/**
 * Created by derik on 16-10-13.
 */

public class SoundPlay {

    public static void playWavFile(final Context ctx, final int ResId) {

        Thread streamThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // API 21
//                AudioAttributes audioAttributes = new AudioAttributes.Builder().setLegacyStreamType(AudioManager.STREAM_MUSIC).build();
//                final SoundPool pool = new SoundPool.Builder().setMaxStreams(1).setAudioAttributes(audioAttributes).build();
                SoundPool pool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);  //srcQuality，官方建议用0
                pool.load(ctx, ResId, 1); //第三个参数目前无用，保持和未来的兼容性，该方法返回一个id
                pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(final SoundPool soundPool, int sampleId, int status) {
                        // TODO Auto-generated method stub

                        soundPool.play(sampleId, 100, 100, 0, 0, 1f);  //第四个参数指定优先级， loop(-1，无限；正数，指定次数)
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                    soundPool.release();

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                });

            }
        });

        streamThread.start();
    }

}
