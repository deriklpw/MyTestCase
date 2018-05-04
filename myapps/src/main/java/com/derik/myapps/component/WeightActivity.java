package com.derik.myapps.component;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.derik.myapps.R;

public class WeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
    }

    public void play(View v){

        SoundPlay.playWavFile(this, R.raw.finish);
    }
}
