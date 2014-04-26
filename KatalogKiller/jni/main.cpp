#include <jni.h>
#include <android/log.h>

#include <opencv2/core/core.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/features2d/features2d.hpp>
#include <vector>

#include <opencv2/highgui/highgui.hpp>
#include <opencv2/nonfree/features2d.hpp>
#include <opencv2/nonfree/nonfree.hpp>
#include <iostream>

using namespace std;
using namespace cv;

#define  LOG_TAG    "main.cpp"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)

extern "C" {
	JNIEXPORT void JNICALL Java_com_cs9033_katalogkiller_ScannerActivity_findFeatures(JNIEnv*, jobject, jlong addrGray, jlong addrRgba);
}

JNIEXPORT void JNICALL Java_com_cs9033_katalogkiller_ScannerActivity_findFeatures(JNIEnv*, jobject, jlong addrGray, jlong addrRgba)
{
    Mat& mGr  = *(Mat*)addrGray;
    Mat& mRgb = *(Mat*)addrRgba;
    vector<KeyPoint> keypoints;
    Mat descriptors;

	// Create a SIFT keypoint detector.
	SiftFeatureDetector detector;
	detector.detect(mGr, keypoints);
	LOGI("Detected %d keypoints\n", (int) keypoints.size());

	// Compute feature description.
	detector.compute(mGr,keypoints, descriptors);
	LOGI("Compute feature.\n");

    //FastFeatureDetector detector(50);
    //detector.detect(mGr, v);
    /*for( unsigned int i = 0; i < keypoints.size(); i++ )
    {
        const KeyPoint& kp = keypoints[i];
        Scalar keypointColor = Scalar(255, 0, 0);
        circle(mRgb, Point(kp.pt.x, kp.pt.y), 10, keypointColor);
    }*/
	Scalar keypointColor = Scalar(255, 0, 0);
	drawKeypoints(mGr, keypoints, mRgb, keypointColor, DrawMatchesFlags::DRAW_RICH_KEYPOINTS);
}

