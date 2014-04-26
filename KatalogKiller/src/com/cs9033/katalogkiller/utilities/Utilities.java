package com.cs9033.katalogkiller.utilities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

public class Utilities {

	static boolean debug = true;

	/** Check if this device has a camera */
	public static boolean checkCameraHardware(Context context) {
		if (context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {

			return true;
		} else {
			// no camera on this device
			return false;
		}
	}

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
			}
		}
	}

}
