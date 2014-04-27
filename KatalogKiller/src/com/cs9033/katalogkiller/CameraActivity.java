package com.cs9033.katalogkiller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.cs9033.katalogkiller.utilities.Utilities;

public class CameraActivity extends Activity {

	private static String TAG = "CameraActivity";

	private Camera mCamera;
	private CameraSurfaceView mPreview;
	private Button captureButton;

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

		captureButton = (Button) findViewById(R.id.button_capture);
		captureButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// get an image from the camera
				mCamera.takePicture(null, null, mPicture);
			}
		});
	}

	private PictureCallback mPicture = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {

			String string = Base64.encodeToString(data, Base64.DEFAULT);
			new UploadFile().execute(string);
		}
	};

	@Override
	protected void onPause() {
		super.onPause();
		releaseCamera(); // release the camera immediately on pause event
	}

	private void releaseCamera() {
		if (mCamera != null) {
			mCamera.release(); // release the camera for other applications
			mCamera = null;
		}
	}

	private class UploadFile extends AsyncTask<String, Integer, Long> {
		
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(CameraActivity.this,
					"Wait", "We are working on this...");
		}

		protected Long doInBackground(String... imageData) {
			long totalSize = 0;
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return totalSize;
		}

		protected void onPostExecute(Long result) {
			progressDialog.dismiss();
			// TODO; make UI suggestion based on the result received.
		}
	}

}