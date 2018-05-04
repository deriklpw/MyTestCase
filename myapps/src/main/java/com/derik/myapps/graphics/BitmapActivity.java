package com.derik.myapps.graphics;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.derik.myapps.R;

public class BitmapActivity extends AppCompatActivity {

    private ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        image1 = (ImageView) findViewById(R.id.bitmap_image1);
        AnimationDrawable animationDrawable = (AnimationDrawable) image1.getDrawable();

        BitmapDrawable bitmapDrawable = (BitmapDrawable) image1.getDrawable();
        Bitmap bitmap1 = bitmapDrawable.getBitmap();
        //获取Bitmap的长宽，单位像素px
        System.out.println("Height:"+bitmap1.getHeight()+"; Width:"+bitmap1.getWidth());

    }
}
