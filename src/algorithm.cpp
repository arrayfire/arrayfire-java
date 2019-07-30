#include "jni_helper.h"

BEGIN_EXTERN_C

#define ALGO_FUNC(FUNC) AF_MANGLE(Algorithm, FUNC)

#define SCALAR_RET_OP_DEF(func)                                              \
  JNIEXPORT jdouble JNICALL ALGO_FUNC(func##All)(JNIEnv * env, jclass clazz, \
                                                 jlong a) {                  \
    double real, imag;                                                       \
    AF_CHECK(af_##func##_all(&real, &imag, ARRAY(a)));                         \
    return real;                                                             \
  }

SCALAR_RET_OP_DEF(sum)
SCALAR_RET_OP_DEF(max)
SCALAR_RET_OP_DEF(min)

#define ARRAY_RET_OP_DEF(func)                                                 \
  JNIEXPORT jlong JNICALL ALGO_FUNC(func)(JNIEnv * env, jclass clazz, jlong a, \
                                          jint dim) {                          \
    af_array ret = 0;                                                          \
    AF_CHECK(af_##func(&ret, ARRAY(a), dim));                                    \
    return JLONG(ret);                                                         \
  }

ARRAY_RET_OP_DEF(sum)
ARRAY_RET_OP_DEF(max)
ARRAY_RET_OP_DEF(min)

JNIEXPORT jlong JNICALL ALGO_FUNC(afWhere)(JNIEnv *env, jclass clazz, jlong arr) {
    af_array ret = 0;
    AF_CHECK(af_where(&ret, ARRAY(arr)));
    return JLONG(ret);
}

END_EXTERN_C
