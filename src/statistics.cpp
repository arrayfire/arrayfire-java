
#include "jni_helper.h"

BEGIN_EXTERN_C

#define STATISTICS_FUNC(FUNC) AF_MANGLE(Statistics, FUNC)

JNIEXPORT jlong JNICALL STATISTICS_FUNC(afMean)(JNIEnv *env, jclass clazz,
                                                jlong ref, jint dim) {
  af_array ret = 0;
  AF_CHECK(af_mean(&ret, ARRAY(ref), dim));
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL STATISTICS_FUNC(afMeanWeighted)(JNIEnv *env,
                                                        jclass clazz, jlong ref,
                                                        jlong weightsRef,
                                                        jint dim) {
  af_array ret = 0;
  AF_CHECK(af_mean_weighted(&ret, ARRAY(ref), ARRAY(weightsRef), dim));
  return JLONG(ret);
}

JNIEXPORT jdouble JNICALL STATISTICS_FUNC(afMeanAll)(JNIEnv *env, jclass clazz,
                                                     jlong ref) {
  double ret = 0;
  AF_CHECK(af_mean_all(&ret, NULL, ARRAY(ref)));
  return (jdouble)ret;
}

JNIEXPORT jdouble JNICALL STATISTICS_FUNC(afMeanAllWeighted)(JNIEnv *env,
                                                             jclass clazz,
                                                             jlong ref,
                                                             jlong weightsRef) {
  double ret = 0;
  AF_CHECK(af_mean_all_weighted(&ret, NULL, ARRAY(ref), ARRAY(weightsRef)));
  return (jdouble)ret;
}

#define INSTANTIATE_MEAN(jtype, param)                               \
  JNIEXPORT jobject JNICALL STATISTICS_FUNC(afMeanAll##jtype)(       \
      JNIEnv * env, jclass clazz, jlong ref) {                       \
    double real = 0, img = 0;                                        \
    AF_CHECK(af_mean_all(&real, &img, ARRAY(ref)));                  \
    jclass cls = env->FindClass("com/arrayfire/" #jtype);            \
    jmethodID id = env->GetMethodID(cls, "<init>", "(" #param ")V"); \
    jobject obj = env->NewObject(cls, id, real, img);                \
    return obj;                                                      \
  }

#define INSTANTIATE_MEAN_WEIGHTED(jtype, param)                            \
  JNIEXPORT jobject JNICALL STATISTICS_FUNC(afMeanAll##jtype##Weighted)(   \
      JNIEnv * env, jclass clazz, jlong ref, jlong weightsRef) {           \
    double real = 0, img = 0;                                              \
    AF_CHECK(                                                              \
        af_mean_all_weighted(&real, &img, ARRAY(ref), ARRAY(weightsRef))); \
    jclass cls = env->FindClass("com/arrayfire/" #jtype);                  \
    jmethodID id = env->GetMethodID(cls, "<init>", "(" #param ")V");       \
    jobject obj = env->NewObject(cls, id, real, img);                      \
    return obj;                                                            \
  }

INSTANTIATE_MEAN(FloatComplex, FF)
INSTANTIATE_MEAN(DoubleComplex, DD)
INSTANTIATE_MEAN_WEIGHTED(FloatComplex, FF)
INSTANTIATE_MEAN_WEIGHTED(DoubleComplex, DD)

END_EXTERN_C
