LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := JniTest
LOCAL_SRC_FILES := JniTest.cpp

include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := MyJniClass
LOCAL_SRC_FILES := MyJniClass.cpp

include $(BUILD_SHARED_LIBRARY)