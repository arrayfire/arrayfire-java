#include "jni_helper.h"

BEGIN_EXTERN_C

#define SIGNAL_FUNC(FUNC) AF_MANGLE(Signal, FUNC)

#define CONVOLVE(N)                                                       \
  JNIEXPORT jlong JNICALL SIGNAL_FUNC(convolve##N)(                       \
      JNIEnv * env, jclass clazz, jlong a, jlong b, int method) {         \
    af_array ret = 0;                                                     \
    AF_CHECK(af_convolve##N(&ret, ARRAY(a), ARRAY(b), (af_conv_mode)method, \
                          AF_CONV_AUTO));                                 \
    return JLONG(ret);                                                    \
  }

CONVOLVE(1)
CONVOLVE(2)
CONVOLVE(3)

#define FFT(func, is_inv)                                                    \
  JNIEXPORT jlong JNICALL SIGNAL_FUNC(func)(JNIEnv * env, jclass clazz,      \
                                            jlong a, int dim0, int method) { \
    af_array ret = 0;                                                        \
    double norm = 1.0;                                                       \
    dim_t dims[4];                                                           \
    AF_CHECK(af_get_dims(dims + 0, dims + 1, dims + 2, dims + 3, ARRAY(a)));   \
    if (is_inv) norm *= dims[0];                                             \
    AF_CHECK(af_##func(&ret, ARRAY(a), norm, dim0));                           \
    return JLONG(ret);                                                       \
  }

FFT(fft, true)
FFT(ifft, false)

#define FFT2(func, is_inv)                                                   \
  JNIEXPORT jlong JNICALL SIGNAL_FUNC(func)(                                 \
      JNIEnv * env, jclass clazz, jlong a, int dim0, int dim1, int method) { \
    af_array ret = 0;                                                        \
    double norm = 1.0;                                                       \
    dim_t dims[4];                                                           \
    AF_CHECK(af_get_dims(dims + 0, dims + 1, dims + 2, dims + 3, ARRAY(a)));   \
    if (is_inv) norm *= dims[0] * dims[1];                                   \
    AF_CHECK(af_##func(&ret, ARRAY(a), norm, dim0, dim1));                     \
    return JLONG(ret);                                                       \
  }

FFT2(fft2, true)
FFT2(ifft2, false)

#define FFT3(func, is_inv)                                                 \
  JNIEXPORT jlong JNICALL SIGNAL_FUNC(func)(JNIEnv * env, jclass clazz,    \
                                            jlong a, int dim0, int dim1,   \
                                            int dim2, int method) {        \
    af_array ret = 0;                                                      \
    double norm = 1.0;                                                     \
    dim_t dims[4];                                                         \
    AF_CHECK(af_get_dims(dims + 0, dims + 1, dims + 2, dims + 3, ARRAY(a))); \
    if (is_inv) norm *= dims[0] * dims[1] * dims[2];                       \
    AF_CHECK(af_##func(&ret, ARRAY(a), norm, dim0, dim1, dim2));             \
    return JLONG(ret);                                                     \
  }

FFT3(fft3, true)
FFT3(ifft3, false)

END_EXTERN_C
