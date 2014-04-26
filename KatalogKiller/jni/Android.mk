LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
OPENCV_INSTALL_MODULES:=on
OPENCV_CAMERA_MODULES:=off

#export OPENCV_PACKAGE_DIR=/Users/CoRrUpT/Downloads/OpenCV-2.4.8-android-sdk

include $(OPENCV_PACKAGE_DIR)/sdk/native/jni/OpenCV.mk

LOCAL_C_INCLUDES += $(OPENCV_PACKAGE_DIR)/sdk/native/jni/include
LOCAL_MODULE    := opencv_feature
LOCAL_CFLAGS    := -Werror -O3 -ffast-math
LOCAL_LDLIBS    += -llog -ldl

LOCAL_SRC_FILES := nonfree_init.cpp \
main.cpp \
sift.cpp \
surf.cpp \
precomp.hpp

include $(BUILD_SHARED_LIBRARY)


# android javah