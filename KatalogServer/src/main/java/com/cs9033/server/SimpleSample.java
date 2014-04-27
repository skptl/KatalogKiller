package com.cs9033.server;

import java.util.List;


/** http://answers.opencv.org/question/10022/the-homography-tutorial-in-java/ */
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.highgui.Highgui;

import com.cs9033.server.parsers.MatParser;
import com.google.gson.Gson;

class SimpleSample {

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		//System.out.println("Welcome to OpenCV " + Core.VERSION);

		Mat img_object = Highgui.imread("/Users/CoRrUpT/Desktop/e.png",
				Highgui.CV_LOAD_IMAGE_ANYDEPTH);
		Mat img_scene = Highgui.imread("/Users/CoRrUpT/Desktop/c.png",
				Highgui.CV_LOAD_IMAGE_ANYDEPTH);

		FeatureDetector detector = FeatureDetector.create(FeatureDetector.SIFT);

		MatOfKeyPoint keypoints_object = new MatOfKeyPoint();
		MatOfKeyPoint keypoints_scene = new MatOfKeyPoint();

		detector.detect(img_object, keypoints_object);
		detector.detect(img_scene, keypoints_scene);

		DescriptorExtractor extractor = DescriptorExtractor
				.create(DescriptorExtractor.SIFT);

		Mat descriptor_object = new Mat();
		Mat descriptor_scene = new Mat();

		extractor.compute(img_object, keypoints_object, descriptor_object);
		extractor.compute(img_scene, keypoints_scene, descriptor_scene);
		
		System.out.println(MatParser.matToJson(descriptor_object));

		DescriptorMatcher matcher = DescriptorMatcher
				.create(DescriptorMatcher.FLANNBASED);
		MatOfDMatch matches = new MatOfDMatch();

		matcher.match(descriptor_object, descriptor_scene, matches);
		List<DMatch> matchesList = matches.toList();

		double max_dist = 0.0;
		double min_dist = 100.0;

		for (int i = 0; i < descriptor_object.rows(); i++) {
			double dist = matchesList.get(i).distance;
			if (dist < min_dist)
				min_dist = dist;
			if (dist > max_dist)
				max_dist = dist;
		}

		//System.out.println("-- Max dist : " + max_dist);
		//System.out.println("-- Min dist : " + min_dist);

		if (min_dist > 0.1F)
			System.out.println("Not matched!!");
		else if (min_dist < 0.1F)
			System.out.println("Matched!!");

	}

}