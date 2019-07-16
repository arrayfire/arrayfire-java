#include "jni_helper.h"

BEGIN_EXTERN_C

#define IMAGE_FUNC(FUNC) AF_MANGLE(Image, FUNC)

#define MORPH_OP_DEF(func)                                             \
  JNIEXPORT jlong JNICALL IMAGE_FUNC(func)(JNIEnv * env, jclass clazz, \
                                           jlong a, jlong b) {         \
    af_array ret = 0;                                                  \
    AF_TO_JAVA(af_##func(&ret, ARRAY(a), ARRAY(b)));                   \
    return JLONG(ret);                                                 \
  }

MORPH_OP_DEF(erode)
MORPH_OP_DEF(dilate)

JNIEXPORT jlong JNICALL IMAGE_FUNC(medfilt)(JNIEnv *env, jclass clazz, jlong a,
                                            jint w, jint h) {
  af_array ret = 0;
  AF_TO_JAVA(af_medfilt(&ret, ARRAY(a), w, h, AF_PAD_ZERO));
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(bilateral)(JNIEnv *env, jclass clazz,
                                              jlong a, jfloat space,
                                              jfloat color) {
  af_array ret = 0;
  AF_TO_JAVA(af_bilateral(&ret, ARRAY(a), space, color, false));
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(meanshift)(JNIEnv *env, jclass clazz,
                                              jlong a, jfloat space,
                                              jfloat color, jint iter) {
  af_array ret = 0;
  AF_TO_JAVA(af_mean_shift(&ret, ARRAY(a), space, color, iter, false));
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(histogram)(JNIEnv *env, jclass clazz,
                                              jlong a, jint nbins) {
  double rmin, imin;
  double rmax, imax;
  AF_TO_JAVA(af_min_all(&rmin, &imin, ARRAY(a)));
  AF_TO_JAVA(af_max_all(&rmax, &imax, ARRAY(a)));

  af_array ret = 0;
  AF_TO_JAVA(af_histogram(&ret, ARRAY(a), nbins, rmin, rmax));
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(hist_mnmx)(JNIEnv *env, jclass clazz,
                                              jlong a, jint nbins, jfloat min,
                                              jfloat max) {
  af_array ret = 0;
  AF_TO_JAVA(af_histogram(&ret, ARRAY(a), nbins, min, max));
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(rotate)(JNIEnv *env, jclass clazz, jlong a,
                                           jfloat theta, jboolean crop,
                                           jint method) {
  af_array ret = 0;
  AF_TO_JAVA(af_rotate(&ret, ARRAY(a), theta, crop, (af_interp_type)method));
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(resize1)(JNIEnv *env, jclass clazz, jlong a,
                                            jfloat scale, jint method) {
  af_array ret = 0;
  dim_t dims[4];
  AF_TO_JAVA(af_get_dims(dims + 0, dims + 1, dims + 2, dims + 3, ARRAY(a)));

  dim_t odim0 = (dim_t)(scale * (float)dims[0]);
  dim_t odim1 = (dim_t)(scale * (float)dims[1]);
  AF_TO_JAVA(af_resize(&ret, ARRAY(a), odim0, odim1, (af_interp_type)method));
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(resize2)(JNIEnv *env, jclass clazz, jlong a,
                                            jfloat scalex, jfloat scaley,
                                            jint method) {
  af_array ret = 0;
  dim_t dims[4];
  AF_TO_JAVA(af_get_dims(dims + 0, dims + 1, dims + 2, dims + 3, ARRAY(a)));

  dim_t odim0 = (dim_t)(scalex * (float)dims[0]);
  dim_t odim1 = (dim_t)(scaley * (float)dims[1]);
  AF_TO_JAVA(af_resize(&ret, ARRAY(a), odim0, odim1, (af_interp_type)method));
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(resize3)(JNIEnv *env, jclass clazz, jlong a,
                                            jint height, jint width,
                                            jint method) {
  af_array ret = 0;
  AF_TO_JAVA(af_resize(&ret, ARRAY(a), height, width, (af_interp_type)method));
  return JLONG(ret);
}

END_EXTERN_C
