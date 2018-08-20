package com.derik.demo.media.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.derik.demo.R;

public class ScreenCaptureActivity extends Activity {


    private Intent intent;
    private SurfaceView surfaceView;
    private Surface surface;
    private int screenDensity;
    private int displayWidth = 360;
    private int displayHeight = 640;
    private boolean screenSharing;

    private MediaProjectionManager mediaProjectionManager;
    private MediaProjection mediaProjection;
    private VirtualDisplay virtualDisplay;
    private final int REQUESTCODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_screen_capture);
        mediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);

        surfaceView = (SurfaceView) findViewById(R.id.screen_capture_view);

        DisplayMetrics metrics = new DisplayMetrics();

//        metrics = getResources().getDisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenDensity = metrics.densityDpi;
        surface = surfaceView.getHolder().getSurface();

        ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();
        lp.width = displayWidth;
        lp.height = displayHeight;
        surfaceView.setLayoutParams(lp);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE) {
            if (resultCode != RESULT_OK) {
                Toast.makeText(ScreenCaptureActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                return;
            }

            mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data);
            virtualDisplay = mediaProjection.createVirtualDisplay("截图", displayWidth, displayHeight, screenDensity,
                    DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR, surface, null, null);

        }
    }

    public void onToggleScreenShare(View view) {
        if (((ToggleButton) view).isChecked()) {
            shareScreen();
        } else {
            stopScreenSharing();
        }
    }

    private void shareScreen() {
        screenSharing = true;
        if (surface == null) {
            return;
        } else {
            intent = mediaProjectionManager.createScreenCaptureIntent();
            startActivityForResult(intent, REQUESTCODE);
            return;
        }
    }

    private void stopScreenSharing() {
        screenSharing = false;
        if (virtualDisplay == null) {
            return;
        }
        virtualDisplay.release();
    }

}
