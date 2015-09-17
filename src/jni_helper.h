#pragma once
#include <arrayfire.h>
#include <jni.h>

#ifdef ANDROID
#include <android/log.h>
#define  LOG(...)  __android_log_print(ANDROID_LOG_INFO, "ArrayFireJNI", __VA_ARGS__)
#define AF_INFO()
#else
#define LOG(msg,...) do {                       \
        printf(__FILE__":%d: " msg "\n",        \
               __LINE__, ##__VA_ARGS__);        \
    } while (0)
#define AF_INFO() af::info()
#endif


#ifdef __cplusplus
#define BEGIN_EXTERN_C extern "C" {
#define END_EXTERN_C }
#endif

#define AF_MANGLE(CLASS, FUNC) Java_com_arrayfire_##CLASS##_##FUNC

const int MaxDimSupported = 3;
