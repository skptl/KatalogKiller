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
	JNIEXPORT void JNICALL Java_com_cs9033_katalogkiller_ScannerActivity_findFeatures(JNIEnv*, jobject, jlong addrGray, jlong addrRgba, jlong addrSiftDescriptor, jlong addrSurfDescriptor);
}

JNIEXPORT void JNICALL Java_com_cs9033_katalogkiller_ScannerActivity_findFeatures(JNIEnv*, jobject, jlong addrGray, jlong addrRgba, jlong addrSiftDescriptor, jlong addrSurfDescriptor)
{
	LOGI("Java_com_cs9033_katalogkiller_ScannerActivity_findFeatures\n");
    Mat& mGr  = *(Mat*)addrGray;
    Mat& mRgb = *(Mat*)addrRgba;
    Mat& siftDescriptors = *(Mat*)addrSiftDescriptor;
    Mat& surfDescriptors = *(Mat*)addrSurfDescriptor;
    vector<KeyPoint> siftKeypoints;
    vector<KeyPoint> surfKeypoints;

	// Create a SIFT keypoint detector.
	SiftFeatureDetector siftDetector;
	siftDetector.detect(mGr, siftKeypoints);
	LOGI("Detected SIFT %d keypoints\n", (int) siftKeypoints.size());
	siftDetector.compute(mGr,siftKeypoints, siftDescriptors);
	LOGI("Computed SIFT descriptors.\n");
	// Create SURF keypoint detector
	SurfFeatureDetector surfDetector;
	surfDetector.detect(mGr, surfKeypoints);
	LOGI("Detected SURF %d keypoints\n", (int) siftKeypoints.size());
	surfDetector.compute(mGr,surfKeypoints, surfDescriptors);
	LOGI("Computed SURF descriptors.\n");
	// Compute feature description.
	LOGI("Compute feature completed.\n");



    /*for( unsigned int i = 0; i < keypoints.size(); i++ )
    {
        const KeyPoint& kp = keypoints[i];
        Scalar keypointColor = Scalar(255, 0, 0);
        circle(mRgb, Point(kp.pt.x, kp.pt.y), 10, keypointColor);
    }*/
	//Scalar keypointColor = Scalar(255, 0, 0);
	//drawKeypoints(mGr, keypoints, mRgb, keypointColor, DrawMatchesFlags::DRAW_RICH_KEYPOINTS);
}

