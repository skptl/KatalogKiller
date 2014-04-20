LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
OPENCV_INSTALL_MODULES:=on
OPENCV_CAMERA_MODULES:=off

#export OPENCV_PACKAGE_DIR=/Users/CoRrUpT/Downloads/OpenCV-2.4.8-android-sdk

include $(OPENCV_PACKAGE_DIR)/sdk/native/jni/OpenCV.mk

LOCAL_C_INCLUDES += $(OPENCV_PACKAGE_DIR)/sdk/native/jni/include
LOCAL_MODULE    := opencv_feature
LOCAL_CFLAGS    := -Werror -O3 -ffast-math
LOCAL_LDLIBS    += -llog

# for 2.4.8, delete the line precomp.cpp \
LOCAL_SRC_FILES := cuda/surf.cu \
opencl/surf.cl \
nonfree_init.cpp \
sift.cpp \
surf_gpu.cpp \
surf.cpp \
surf.ocl.cpp
include $(BUILD_SHARED_LIBRARY)
