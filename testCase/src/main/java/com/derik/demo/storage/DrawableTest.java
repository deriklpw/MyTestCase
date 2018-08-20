package com.derik.demo.storage;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

import com.derik.demo.R;

/**
 * Created by derik on 16-9-7.
 */
public class DrawableTest extends Activity implements View.OnClickListener {

    private ImageView image1;
    private ImageView image2;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private SeekBar seekBar;

    private ClipDrawable clipDrawable;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                clipDrawable.setLevel(clipDrawable.getLevel() + 200);
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        seekBar = (SeekBar) findViewById(R.id.seek_bar);

        clipDrawable = (ClipDrawable) image1.getDrawable();

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

        final AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        seekBar.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        int progress = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar.setProgress(progress);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                am.setStreamVolume(AudioManager.STREAM_MUSIC, i, AudioManager.FLAG_PLAY_SOUND);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = Message.obtain();
                        msg.what = 0x123;
                        handler.sendMessage(msg);
                        if (clipDrawable.getLevel() >= 10000) {
                            timer.cancel();
                        }
                    }
                }, 0, 300);
                break;

            case R.id.button2:
                break;

            case R.id.button3:
                Animation anim = AnimationUtils.loadAnimation(this, R.anim.my_anim);
                anim.setFillAfter(false);

                image2.startAnimation(anim);
                break;

            case R.id.button4:


                break;

            case R.id.button5:
                FrameLayout layout = (FrameLayout) findViewById(R.id.frame_layout);
                layout.addView(new MyAnimatorView(this));

                break;

            default:
                break;
        }
    }

    public class MyAnimatorView extends View{

        public MyAnimatorView(Context context) {
            super(context);
            ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.color_anim);
            objectAnimator.setEvaluator(new ArgbEvaluator());
            objectAnimator.setTarget(this);
            objectAnimator.start();
        }
    }

}
