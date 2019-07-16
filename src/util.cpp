#include "jni_helper.h"

BEGIN_EXTERN_C

#define UTIL_FUNC(FUNC) AF_MANGLE(Util, FUNC)

JNIEXPORT void JNICALL UTIL_FUNC(info)(JNIEnv *env, jclass clazz) { af_info(); }

END_EXTERN_C
