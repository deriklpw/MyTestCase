package com.derik.demo.a_first.soundpool;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;

/**
 * Created by derik on 16-10-13.
 */

public class SoundPlay {

    public static void playWavFile(final Context ctx, final int ResId, final Handler handler) {

        Thread streamThread = new Thread(new Runnable() {
            @Override
            public void run() {

                SoundPool pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
                pool.load(ctx, ResId, 1);
                pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(final SoundPool soundPool, int sampleId, int status) {
                        // TODO Auto-generated method stub
                        if (handler != null) {
                            handler.sendEmptyMessage(0x123);
                        }

                        soundPool.play(sampleId, 100, 100, 0, 0, 1f);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                    soundPool.release();
                                    if (handler != null) {
                                        handler.sendEmptyMessage(0x456);
                                    }

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

    public static void playWavFile(final Context ctx, final int ResId) {

        Thread streamThread = new Thread(new Runnable() {
            @Override
            public void run() {


//                AudioAttributes audioAttributes = new AudioAttributes.Builder().setLegacyStreamType(AudioManager.STREAM_MUSIC).build();
//                final SoundPool pool = new SoundPool.Builder().setMaxStreams(1).setAudioAttributes(audioAttributes).build();
                SoundPool pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
                pool.load(ctx, ResId, 1);
                pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(final SoundPool soundPool, int sampleId, int status) {
                        // TODO Auto-generated method stub

                        soundPool.play(sampleId, 100, 100, 0, 0, 1f);
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
