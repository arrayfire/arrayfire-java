
#include "jni_helper.h"

BEGIN_EXTERN_C

#define STATISTICS_FUNC(FUNC) AF_MANGLE(Statistics, FUNC)

#define INSTANTIATE_STAT_WEIGHTED_COMPLEX(jtype, Name, name)                   \
  JNIEXPORT jobject JNICALL STATISTICS_FUNC(af##Name##All##jtype##Weighted)(   \
      JNIEnv * env, jclass clazz, jlong ref, jlong weightsRef) {               \
    double real = 0, img = 0;                                                  \
    AF_CHECK(                                                                  \
        af_##name##_all_weighted(&real, &img, ARRAY(ref), ARRAY(weightsRef))); \
    return java::createJavaObject(env, java::JavaObjects::jtype, real, img);   \
  }

#define INSTANTIATE_STAT_ALL_WEIGHTED(Name, name)                             \
  JNIEXPORT jdouble JNICALL STATISTICS_FUNC(af##Name##AllWeighted)(           \
      JNIEnv * env, jclass clazz, jlong ref, jlong weightsRef) {              \
    double ret = 0;                                                           \
    AF_CHECK(                                                                 \
        af_##name##_all_weighted(&ret, NULL, ARRAY(ref), ARRAY(weightsRef))); \
    return (jdouble)ret;                                                      \
  }

#define INSTANTIATE_STAT_WEIGHTED(Name, name)                                 \
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

#define INSTANTIATE_STAT(Name, name)                     \
  JNIEXPORT jlong JNICALL STATISTICS_FUNC(af##Name)(     \
      JNIEnv * env, jclass clazz, jlong ref, jint dim) { \
    af_array ret = 0;                                    \
    AF_CHECK(af_##name(&ret, ARRAY(ref), dim));          \
    return JLONG(ret);                                   \
  }

#define INSTANTIATE_STAT_ALL(Name, name)                    \
  JNIEXPORT jdouble JNICALL STATISTICS_FUNC(af##Name##All)( \
      JNIEnv * env, jclass clazz, jlong ref) {              \
    double ret = 0;                                         \
    AF_CHECK(af_##name##_all(&ret, NULL, ARRAY(ref)));      \
    return (jdouble)ret;                                    \
  }

#define INSTANTIATE_STAT_ALL_COMPLEX(Name, name, jtype)                      \
  JNIEXPORT jobject JNICALL STATISTICS_FUNC(af##Name##All##jtype)(           \
      JNIEnv * env, jclass clazz, jlong ref) {            \
    double real = 0, img = 0;                                                \
    AF_CHECK(af_##name##_all(&real, &img, ARRAY(ref)));                      \
    return java::createJavaObject(env, java::JavaObjects::jtype, real, img); \
  }

// Mean
INSTANTIATE_STAT(Mean, mean)
INSTANTIATE_STAT_ALL(Mean, mean)
INSTANTIATE_STAT_ALL_COMPLEX(Mean, mean, FloatComplex)
INSTANTIATE_STAT_ALL_COMPLEX(Mean, mean, DoubleComplex)
INSTANTIATE_STAT_ALL_WEIGHTED(Mean, mean)
INSTANTIATE_STAT_WEIGHTED(Mean, mean)
INSTANTIATE_STAT_WEIGHTED_COMPLEX(FloatComplex, Mean, mean)
INSTANTIATE_STAT_WEIGHTED_COMPLEX(DoubleComplex, Mean, mean)

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

// Variance
INSTANTIATE_VAR(FloatComplex)
INSTANTIATE_VAR(DoubleComplex)
INSTANTIATE_STAT_WEIGHTED(Var, var)
INSTANTIATE_STAT_ALL_WEIGHTED(Var, var)
INSTANTIATE_STAT_WEIGHTED_COMPLEX(FloatComplex, Var, var)
INSTANTIATE_STAT_WEIGHTED_COMPLEX(DoubleComplex, Var, var)

// Standard dev
INSTANTIATE_STAT(Stdev, stdev)
INSTANTIATE_STAT_ALL(Stdev, stdev)
INSTANTIATE_STAT_ALL_COMPLEX(Stdev, stdev, FloatComplex)
INSTANTIATE_STAT_ALL_COMPLEX(Stdev, stdev, DoubleComplex)

#undef INSTANTIATE_VAR
#undef INSTANTIATE_STAT_WEIGHTED_COMPLEX
#undef INSTANTIATE_STAT_WEIGHTED
#undef INSTANTIATE_STAT_ALL_WEIGHTED
#undef INSTANTIATE_STAT
#undef INSTANTIATE_STAT_ALL
#undef INSTANTIATE_STAT_ALL_COMPLEX

END_EXTERN_C
