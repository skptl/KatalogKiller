package com.cs9033.katalogkiller;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import com.cs9033.katalogkiller.utilities.Utilities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

public class ScannerActivity extends Activity implements CvCameraViewListener2 {

	private static final String TAG = "ScannerActivity";

	private Mat mRgba;
	private Mat mIntermediateMat;
	private Mat mGray;
	private Mat siftDescriptors;
	private Mat surfDescriptors;

	private CameraBridgeViewBase mOpenCvCameraView;
	private Button captureButton;

	private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
		@Override
		public void onManagerConnected(int status) {
			switch (status) {
			case LoaderCallbackInterface.SUCCESS: {

				// Load native library after(!) OpenCV initialization
				try{
				System.loadLibrary("opencv_java");
	    		System.loadLibrary("opencv_feature");
				} catch(Exception e)
				{
					e.printStackTrace();
				}
	    		Log.i(TAG, "OpenCV loaded successfully");
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

		mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.cameraSurfaceView);
		mOpenCvCameraView.setCvCameraViewListener(this);
		
		captureButton = (Button) findViewById(R.id.captureButton);
		captureButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				findFeatures(mGray.getNativeObjAddr(), mRgba.getNativeObjAddr(), siftDescriptors.getNativeObjAddr(), surfDescriptors.getNativeObjAddr());
				Utilities.log(TAG, "Columns: "+siftDescriptors.cols()+" Rows: "+siftDescriptors.rows(), -1);
				Utilities.log(TAG, "Columns: "+surfDescriptors.cols()+" Rows: "+surfDescriptors.rows(), -1);
				//if (mOpenCvCameraView != null)
		         //   mOpenCvCameraView.disableView();
				
			}
		});
	}
	
    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_8, this, mLoaderCallback);
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mIntermediateMat = new Mat(height, width, CvType.CV_8UC4);
        mGray = new Mat(height, width, CvType.CV_8UC1);
        siftDescriptors = new Mat();
        surfDescriptors = new Mat();
    }

    public void onCameraViewStopped() {
        mRgba.release();
        mGray.release();
        mIntermediateMat.release();
        siftDescriptors.release();
        surfDescriptors.release();
        Utilities.log(TAG, "Memory released!!", -1);
    }

	@Override
	public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
		mRgba = inputFrame.rgba();
        mGray = inputFrame.gray();
		return mRgba;
	}
	
	public native void findFeatures(long matAddrGr, long matAddrRgba, long matAddrSiftDescriptor, long matAddrSurfDescriptor);
	

}
