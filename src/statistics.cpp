
#include "jni_helper.h"

BEGIN_EXTERN_C

#define STATISTICS_FUNC(FUNC) AF_MANGLE(Statistics, FUNC)

#define INSTANTIATE_MEAN(jtype)                                              \
  JNIEXPORT jobject JNICALL STATISTICS_FUNC(afMeanAll##jtype)(               \
      JNIEnv * env, jclass clazz, jlong ref) {                               \
    double real = 0, img = 0;                                                \
    AF_CHECK(af_mean_all(&real, &img, ARRAY(ref)));                          \
    return java::createJavaObject(env, java::JavaObjects::jtype, real, img); \
  }

#define INSTANTIATE_WEIGHTED(jtype, Name, name)                                \
  JNIEXPORT jobject JNICALL STATISTICS_FUNC(af##Name##All##jtype##Weighted)(   \
      JNIEnv * env, jclass clazz, jlong ref, jlong weightsRef) {               \
    double real = 0, img = 0;                                                  \
    AF_CHECK(                                                                  \
        af_##name##_all_weighted(&real, &img, ARRAY(ref), ARRAY(weightsRef))); \
    return java::createJavaObject(env, java::JavaObjects::jtype, real, img);   \
  }

#define INSTANTIATE_ALL_REAL_WEIGHTED(Name, name)                             \
  JNIEXPORT jdouble JNICALL STATISTICS_FUNC(af##Name##AllWeighted)(           \
      JNIEnv * env, jclass clazz, jlong ref, jlong weightsRef) {              \
    double ret = 0;                                                           \
    AF_CHECK(                                                                 \
        af_##name##_all_weighted(&ret, NULL, ARRAY(ref), ARRAY(weightsRef))); \
    return (jdouble)ret;                                                      \
  }

#define INSTANTIATE_REAL_WEIGHTED(Name, name)                                 \
  JNIEXPORT jlong JNICALL STATISTICS_FUNC(af##Name##Weighted)(                \
      JNIEnv * env, jclass clazz, jlong ref, jlong weightsRef, jint dim) {    \
    af_array ret = 0;                                                         \
    AF_CHECK(af_##name##_weighted(&ret, ARRAY(ref), ARRAY(weightsRef), dim)); \
    return JLONG(ret);                                                        \
  }

#define INSTANTIATE_VAR(jtype)                                               \
  JNIEXPORT jobject JNICALL STATISTICS_FUNC(afVarAll##jtype)(                \
      JNIEnv * env, jclass clazz, jlong ref, jboolean isBiased) {            \
    double real = 0, img = 0;                                                \
    AF_CHECK(af_var_all(&real, &img, ARRAY(ref), isBiased));                 \
    return java::createJavaObject(env, java::JavaObjects::jtype, real, img); \
  }

#define INSTANTIATE_STAT(Name, name)                                    \
    JNIEXPORT jlong JNICALL STATISTICS_FUNC(af##Name)(JNIEnv *env, jclass clazz, \
                                                    jlong ref, jint dim) { \
    af_array ret = 0;                                                   \
    AF_CHECK(af_##name(&ret, ARRAY(ref), dim));                           \
    return JLONG(ret);                                                  \
}

INSTANTIATE_STAT(Mean, mean)
INSTANTIATE_STAT(Stdev, stdev)

#define INSTANTIATE_STAT_ALL(Name, name)                                \
    JNIEXPORT jdouble JNICALL STATISTICS_FUNC(af##Name##All)(JNIEnv *env, jclass clazz, \
                                                         jlong ref) {   \
    double ret = 0;                                                     \
    AF_CHECK(af_##name##_all(&ret, NULL, ARRAY(ref)));                      \
    return (jdouble)ret;                                                \
}

INSTANTIATE_STAT_ALL(Mean, mean)

INSTANTIATE_MEAN(FloatComplex)
INSTANTIATE_MEAN(DoubleComplex)
INSTANTIATE_ALL_REAL_WEIGHTED(Mean, mean)
INSTANTIATE_REAL_WEIGHTED(Mean, mean)
INSTANTIATE_WEIGHTED(FloatComplex, Mean, mean)
INSTANTIATE_WEIGHTED(DoubleComplex, Mean, mean)

#undef INSTANTIATE_MEAN

JNIEXPORT jlong JNICALL STATISTICS_FUNC(afVar)(JNIEnv *env, jclass clazz,
                                               jlong ref, jboolean isBiased,
                                               jint dim) {
  af_array ret = 0;
  AF_CHECK(af_var(&ret, ARRAY(ref), isBiased, dim));
  return JLONG(ret);
}

JNIEXPORT jdouble JNICALL STATISTICS_FUNC(afVarAll)(JNIEnv *env, jclass clazz,
                                                    jlong ref,
                                                    jboolean isBiased) {
  double ret = 0;
  AF_CHECK(af_var_all(&ret, NULL, ARRAY(ref), isBiased));
  return (jdouble)ret;
}

INSTANTIATE_VAR(FloatComplex)
INSTANTIATE_VAR(DoubleComplex)
INSTANTIATE_REAL_WEIGHTED(Var, var)
INSTANTIATE_ALL_REAL_WEIGHTED(Var, var)
INSTANTIATE_WEIGHTED(FloatComplex, Var, var)
INSTANTIATE_WEIGHTED(DoubleComplex, Var, var)

#undef INSTANTIATE_VAR
#undef INSTANTIATE_MEAN
#undef INSTANTIATE_WEIGHTED
#undef INSTANTIATE_REAL_WEIGHTED
#undef INSTANTIATE_ALL_REAL_WEIGHTED

END_EXTERN_C
