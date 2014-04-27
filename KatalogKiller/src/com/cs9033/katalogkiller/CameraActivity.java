package com.cs9033.katalogkiller;

import com.cs9033.katalogkiller.utilities.Utilities;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.FrameLayout;

public class CameraActivity extends Activity {

    private Camera mCamera;
    private CameraSurfaceView mPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_preview);

        // Create an instance of Camera
        mCamera = Utilities.getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraSurfaceView(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }
}