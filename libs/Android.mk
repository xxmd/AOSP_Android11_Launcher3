LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := lib_launcher_client
LOCAL_SRC_FILES := launcher_client.jar
LOCAL_MODULE_CLASS := JAVA_LIBRARIES
LOCAL_MODULE_SUFFIX := .jar
LOCAL_UNINSTALLABLE_MODULE := true
include $(BUILD_PREBUILT)
