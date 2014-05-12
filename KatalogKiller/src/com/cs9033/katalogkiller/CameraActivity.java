package com.cs9033.katalogkiller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.AsyncTask;
import android.os.Bundle;
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

			String URL = Utilities.serverURL + "requests/add.do";
			new UploadFile(data).execute(URL);
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
		String result;
		byte[] data;

		public UploadFile(byte[] data) {
			this.data = data;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(CameraActivity.this, "Wait",
					"We are working on this...");
		}

		protected Long doInBackground(String... URL) {
			long totalSize = 0;
			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(URL[0]);
				MultipartEntityBuilder builder = MultipartEntityBuilder
						.create();
				builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
				File file = File.createTempFile("temp", ".png");
				FileOutputStream fileOuputStream = new FileOutputStream(file);
				fileOuputStream.write(data);
				fileOuputStream.close();
				FileBody fb = new FileBody(file);
				builder.addPart("imageToScan", fb);
				final HttpEntity reqEntity = builder.build();
				post.setEntity(reqEntity);
				HttpResponse response = client.execute(post);
				result = getContent(response);
				
			//JSONObject jsonObj = new JSONObject(result);
			 JSONArray jsonArray = new JSONArray(result);
			 
		for(int i=0; i <jsonArray.length(); i++)
		{
			System.out.println(jsonArray.get(i));
		}
			
				Utilities.log(TAG, result, -1);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return totalSize;
		}

		protected void onPostExecute(Long result) {
			progressDialog.dismiss();
			// TODO; make UI suggestion based on the result received.
		}

		public String getContent(HttpResponse response) throws IOException {
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String body = "";
			String content = "";

			while ((body = rd.readLine()) != null) {
				content += body + "\n";
			}
			return content.trim();
		}
	}

}