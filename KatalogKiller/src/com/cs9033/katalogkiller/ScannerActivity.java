package com.cs9033.katalogkiller;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.core.Mat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class ScannerActivity extends Activity implements CvCameraViewListener2 {

	private static final String TAG = "ScannerActivity";

	private Mat mRgba;
	private Mat mIntermediateMat;
	private Mat mGray;

	private CameraBridgeViewBase mOpenCvCameraView;

	private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
		@Override
		public void onManagerConnected(int status) {
			switch (status) {
			case LoaderCallbackInterface.SUCCESS: {
				Log.i(TAG, "OpenCV loaded successfully");

				// Load native library after(!) OpenCV initialization
				System.loadLibrary("mixed_sample");

				mOpenCvCameraView.enableView();
			}
				break;
			default: {
				super.onManagerConnected(status);
			}
				break;
			}
		}
	};

	public ScannerActivity() {
		Log.i(TAG, "Instantiated new " + this.getClass());
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "called onCreate");
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		setContentView(R.layout.scanner);

		mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.camera_surface_view);
		mOpenCvCameraView.setCvCameraViewListener(this);
	}

	@Override
	public void onCameraViewStarted(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCameraViewStopped() {
		// TODO Auto-generated method stub

	}

	@Override
	public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
		// TODO Auto-generated method stub
		return null;
	}

}
