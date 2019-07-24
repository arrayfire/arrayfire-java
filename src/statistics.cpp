#include "jni_helper.h"

BEGIN_EXTERN_C

#define STATISTICS_FUNC(FUNC) AF_MANGLE(Statistics, FUNC)

#define INSTANTIATE_STAT_ALL_WEIGHTED(Name, name)                              \
  JNIEXPORT jobject JNICALL STATISTICS_FUNC(af##Name##AllWeighted)(            \
      JNIEnv * env, jclass clazz, jlong ref, jlong weightsRef) {               \
    double real = 0, img = 0;                                                  \
    AF_CHECK(                                                                  \
        af_##name##_all_weighted(&real, &img, ARRAY(ref), ARRAY(weightsRef))); \
    return java::createJavaObject(env, java::JavaObjects::DoubleComplex, real, \
                                  img);                                        \
  }

#define INSTANTIATE_STAT_WEIGHTED(Name, name)                                 \
  JNIEXPORT jlong JNICALL STATISTICS_FUNC(af##Name##Weighted)(                \
      JNIEnv * env, jclass clazz, jlong ref, jlong weightsRef, jint dim) {    \
    af_array ret = 0;                                                         \
    AF_CHECK(af_##name##_weighted(&ret, ARRAY(ref), ARRAY(weightsRef), dim)); \
    return JLONG(ret);                                                        \
  }

#define INSTANTIATE_STAT(Name, name)                     \
  JNIEXPORT jlong JNICALL STATISTICS_FUNC(af##Name)(     \
      JNIEnv * env, jclass clazz, jlong ref, jint dim) { \
    af_array ret = 0;                                    \
    AF_CHECK(af_##name(&ret, ARRAY(ref), dim));          \
    return JLONG(ret);                                   \
  }

#define INSTANTIATE_STAT_ALL(Name, name)                                       \
  JNIEXPORT jobject JNICALL STATISTICS_FUNC(af##Name##All)(                    \
      JNIEnv * env, jclass clazz, jlong ref) {                                 \
    double real = 0, img = 0;                                                  \
    AF_CHECK(af_##name##_all(&real, &img, ARRAY(ref)));                        \
    return java::createJavaObject(env, java::JavaObjects::DoubleComplex, real, \
                                  img);                                        \
  }

// Mean
INSTANTIATE_STAT(Mean, mean)
INSTANTIATE_STAT_ALL(Mean, mean)
INSTANTIATE_STAT_ALL_WEIGHTED(Mean, mean)
INSTANTIATE_STAT_WEIGHTED(Mean, mean)

// Variance
JNIEXPORT jlong JNICALL STATISTICS_FUNC(afVar)(JNIEnv *env, jclass clazz,
                                               jlong ref, jboolean isBiased,
                                               jint dim) {
  af_array ret = 0;
  AF_CHECK(af_var(&ret, ARRAY(ref), isBiased, dim));
  return JLONG(ret);
}

JNIEXPORT jobject JNICALL STATISTICS_FUNC(afVarAll)(JNIEnv *env, jclass clazz,
                                               jlong ref, jboolean isBiased) {
    double real = 0, img = 0;
  AF_CHECK(af_var_all(&real, &img, ARRAY(ref), isBiased));
  return java::createJavaObject(env, java::JavaObjects::DoubleComplex, real, img);
}

INSTANTIATE_STAT_WEIGHTED(Var, var)
INSTANTIATE_STAT_ALL_WEIGHTED(Var, var)

// Standard dev
INSTANTIATE_STAT(Stdev, stdev)
INSTANTIATE_STAT_ALL(Stdev, stdev)

// Median
INSTANTIATE_STAT(Median, median)
INSTANTIATE_STAT_ALL(Median, median)

// Covariance
JNIEXPORT jlong JNICALL STATISTICS_FUNC(afCov)(JNIEnv *env, jclass clazz,
                                               jlong ref, jlong ref2,
                                               jboolean isBiased) {
  af_array ret = 0;
  AF_CHECK(af_cov(&ret, ARRAY(ref), ARRAY(ref2), isBiased));
  return JLONG(ret);
}

// Correlation coefficient
JNIEXPORT jobject JNICALL STATISTICS_FUNC(afCorrcoef)(JNIEnv *env, jclass clazz,
                                                      jlong ref, jlong ref2) {
  double real = 0;
  AF_CHECK(af_corrcoef(&real, NULL, ARRAY(ref), ARRAY(ref2)));
  return java::createJavaObject(env, java::JavaObjects::DoubleComplex, real,
                                0.0);
}

// Topk elements
JNIEXPORT jlongArray JNICALL STATISTICS_FUNC(afTopk)(JNIEnv *env, jclass clazz,
                                                     jlong ref, jint k,
                                                     jint dim, jint order) {
  af_array vals = 0, idxs = 0;
  AF_CHECK(af_topk(&vals, &idxs, ARRAY(ref), k, dim,
                   static_cast<af_topk_function>(order)));
  jlong refs[2]{JLONG(idxs), JLONG(vals)};
  jlongArray jRefs = env->NewLongArray(2);
  env->SetLongArrayRegion(jRefs, 0, 2, refs);
  return jRefs;
}

#undef INSTANTIATE_STAT
#undef INSTANTIATE_STAT_ALL
#undef INSTANTIATE_STAT_WEIGHTED
#undef INSTANTIATE_STAT_ALL_WEIGHTED

END_EXTERN_C
