package com.cs9033.katalogkiller.utilities;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Utilities {

	public static boolean debug = true;

	public static String serverURL = "";

	private static final int NTHREDS = 5;

	private static final ExecutorService executor = Executors
			.newFixedThreadPool(NTHREDS);

	/**
	 * Check if this device has a camera
	 * 
	 * @param context
	 *            Context of the application
	 * @return Status of camera hardware
	 */
	public static boolean checkCameraHardware(Context context) {
		if (context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {

			return true;
		} else {
			// no camera on this device
			return false;
		}
	}

	/**
	 * Check for network status
	 * 
	 * @param context
	 *            Context of the application
	 * @return Status of network
	 */
	public static boolean isNetworkActive(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	/**
	 * One stop logging for application
	 * 
	 * @param tag
	 *            Tag of the message to be logged
	 * @param message
	 *            Message to be logged
	 * @param level
	 *            Severity of logging
	 */
	public static void log(String tag, String message, int level) {
		if (debug) {
			switch (level) {
			case Log.ASSERT:
				Log.wtf(tag, message);
				break;
			case Log.DEBUG:
				Log.d(tag, message);
				break;
			case Log.ERROR:
				Log.e(tag, message);
				break;
			case Log.INFO:
				Log.i(tag, message);
				break;
			case Log.VERBOSE:
				Log.i(tag, message);
				break;
			case Log.WARN:
				Log.w(tag, message);
				break;
			default:
				Log.i(tag, message);
			}
		}
	}

	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
		}
		return c; // returns null if camera is unavailable
	}

	public static String serverCommunication(Context context, String URL,
			List<NameValuePair> nameValuePairs) {
		String result = null;
		try {
			HttpCommunicator worker = new HttpCommunicator(URL, nameValuePairs);
			Future<String> submit = executor.submit(worker);
			result = submit.get(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
