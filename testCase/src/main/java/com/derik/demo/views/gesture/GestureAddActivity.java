package com.derik.demo.views.gesture;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.derik.demo.R;
import com.derik.library.view.MsgToast;

import java.util.ArrayList;

public class GestureAddActivity extends Activity {

    private ToggleButton btn_toggle;
    private Switch btn_switch;
    private GestureOverlayView gestureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_add);

        btn_switch = (Switch) findViewById(R.id.gesture_switch);
        btn_toggle = (ToggleButton) findViewById(R.id.gesture_toggle);
        gestureView = (GestureOverlayView) findViewById(R.id.gesture_view);
        gestureView.setGestureColor(Color.RED);
        gestureView.setGestureStrokeWidth(5);

        btn_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MsgToast.show(GestureAddActivity.this, ""+btn_toggle.isChecked());
            }
        });

        gestureView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, final Gesture gesture) {

                if (btn_switch.isChecked()){
                    // TODO recognize
                    GestureLibrary gestureLibrary = GestureLibraries.fromFile("/mnt/sdcard/my_gesture");
                    if (gestureLibrary.load()){
                        Log.i("gestureLibrary load", "OK");
                        ArrayList<Prediction> predictions = gestureLibrary.recognize(gesture);
                        ArrayList<String> result = new ArrayList<String>();
                        for (Prediction pre: predictions) {
                            if (pre.score > 2.0){
                                result.add("与手势:"+pre.name+"相似度为:"+pre.score);
                            }
                        }

                        if (result.size()>0){
                            ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(GestureAddActivity.this, android.R.layout.simple_dropdown_item_1line, result.toArray());
                            new AlertDialog.Builder(GestureAddActivity.this).setAdapter(adapter, null).setPositiveButton("OK", null).create().show();
                        }else{
                            Toast.makeText(GestureAddActivity.this, "No gesture", Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Log.e("gestureLibrary load", "FAIL");
                    }


                }else{
                    // TODO add

                    View saveDialog = getLayoutInflater().inflate(R.layout.save, null);
                    ImageView imageView = (ImageView) saveDialog.findViewById(R.id.save_bitmap);
                    final EditText editText = (EditText) saveDialog.findViewById(R.id.save_name);
                    Bitmap bitmap = gesture.toBitmap(128, 128, 10, 0xffff0000);
                    imageView.setImageBitmap(bitmap);
                    new AlertDialog.Builder(GestureAddActivity.this).setView(saveDialog).setPositiveButton("save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            GestureLibrary gestureLibrary = GestureLibraries.fromFile("/mnt/sdcard/my_gesture");
                            String name = editText.getText().toString().trim();
                            gestureLibrary.addGesture(name, gesture);
                            gestureLibrary.save();
                        }
                    }).setNegativeButton("cancel", null).create().show();

                }

            }
        });

    }
}
