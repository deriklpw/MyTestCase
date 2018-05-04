package com.derik.demo.a_first.soundpool;

import android.content.Context;
import android.content.res.Resources;

import java.util.HashMap;

/**
 * Created by derik on 16-10-13.
 */
public class SoundUtils {
    public static final String SOUND_BACK = "back";
    public static final String SOUND_CONFIRM = "confirm";
    public static final String SOUND_CORRECT1 = "correct1";
    public static final String SOUND_CORRECT2 = "correct2";

    public static final String SOUND_ERROR = "error";
    public static final String SOUND_FINISH = "finish";
    public static final String SOUND_MENU1 = "menu1";
    public static final String SOUND_MENU2 = "menu2";

    public static final String SOUND_NEXT = "next";
    public static final String SOUND_UI1 = "ui1";
    public static final String SOUND_UI2 = "ui2";
    public static final String SOUND_UI3 = "ui3";
    public static final String SOUND_WARNING = "warning";
    public static final String SOUND_WIN = "win";

    //private static SoundPool soundPool;
    private static HashMap<String, Integer> soundMap;
    private Resources resources;
    private static boolean isInit = false;
    private static Context mContext;

    public SoundUtils(Context ctx) {

        final String[] sounds = {SOUND_BACK, SOUND_CONFIRM, SOUND_CORRECT1, SOUND_CORRECT2, SOUND_ERROR,
                SOUND_FINISH, SOUND_MENU1, SOUND_MENU2, SOUND_NEXT, SOUND_UI1, SOUND_UI2, SOUND_UI3,
                SOUND_WARNING, SOUND_WIN,
        };

        //soundPool = new SoundPool(sounds.length, AudioManager.STREAM_MUSIC, 0);
        resources = ctx.getResources();
        soundMap = new HashMap<>();
        mContext = ctx;

//        for (int i = 0; i < sounds.length; i++) {
//            soundMap.put(sounds[i], soundPool.load(ctx, resources.getIdentifier(sounds[i], "raw", ctx.getPackageName()), 1));
//        }

        for (int i = 0; i < sounds.length; i++) {
            soundMap.put(sounds[i], resources.getIdentifier(sounds[i], "raw", ctx.getPackageName()));
        }

    }

    public static boolean init(Context ctx) {
        new SoundUtils(ctx);
        isInit = true;
        return isInit;
    }

    public static void play(Context ctx, String soundName) {
        init(ctx);
//        try {
//            if (isInit){
//                soundPool.play(soundMap.get(soundName), 1, 1, 0, 0, 1);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        SoundPlay.playWavFile(mContext, soundMap.get(soundName));

    }

    public static void release() {
//        if (null != soundPool){
//            soundPool.release();
//        }
    }

}
