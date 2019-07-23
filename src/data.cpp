#include <af/complex.h>
#include "jni_helper.h"

BEGIN_EXTERN_C

#define DATA_FUNC(FUNC) AF_MANGLE(Data, FUNC)

using af::cdouble;
using af::cfloat;

JNIEXPORT jlong JNICALL DATA_FUNC(createRanduArray)(JNIEnv *env, jclass clazz,
                                                    jintArray dims, jint type) {
  af_array ret = 0;
  jint *dimptr = env->GetIntArrayElements(dims, 0);
  dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};
  env->ReleaseIntArrayElements(dims, dimptr, 0);
  AF_CHECK(af_randu(&ret, 4, tdims, (af_dtype)(type)));
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL DATA_FUNC(createRandnArray)(JNIEnv *env, jclass clazz,
                                                    jintArray dims, jint type) {
  af_array ret = 0;
  jint *dimptr = env->GetIntArrayElements(dims, 0);
  dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};
  env->ReleaseIntArrayElements(dims, dimptr, 0);
  AF_CHECK(af_randn(&ret, 4, tdims, (af_dtype)(type)));
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL DATA_FUNC(createConstantsArray)(
    JNIEnv *env, jclass clazz, jdouble val, jintArray dims, jint type) {
  af_array ret = 0;
  jint *dimptr = env->GetIntArrayElements(dims, 0);
  dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};
  env->ReleaseIntArrayElements(dims, dimptr, 0);
  AF_CHECK(af_constant(&ret, val, 4, tdims, (af_dtype)(type)));
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL DATA_FUNC(createIdentityArray)(JNIEnv *env,
                                                       jclass clazz,
                                                       jintArray dims,
                                                       jint type) {
  af_array ret = 0;
  jint *dimptr = env->GetIntArrayElements(dims, 0);
  dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};
  env->ReleaseIntArrayElements(dims, dimptr, 0);
  AF_CHECK(af_identity(&ret, 4, tdims, (af_dtype)(type)));
  return JLONG(ret);
}

#define GET_T_FROM_ARRAY(Ty, ty)                                \
  JNIEXPORT j##ty##Array JNICALL DATA_FUNC(get##Ty##FromArray)( \
      JNIEnv * env, jclass clazz, jlong ref) {                  \
    j##ty##Array result;                                        \
    dim_t elements = 0;                                         \
    AF_CHECK(af_get_elements(&elements, ARRAY(ref)));           \
    result = env->New##Ty##Array(elements);                     \
    if (result == NULL) {                                       \
      LOG("Something terrible happened. "                       \
          "Couldn't allocate heap space!!!!");                  \
      return NULL;                                              \
    }                                                           \
    j##ty *resf = env->Get##Ty##ArrayElements(result, 0);       \
    AF_CHECK(af_get_data_ptr((void *)resf, ARRAY(ref)));        \
    env->Release##Ty##ArrayElements(result, resf, 0);           \
    return result;                                              \
  }

GET_T_FROM_ARRAY(Float, float);
GET_T_FROM_ARRAY(Double, double);
GET_T_FROM_ARRAY(Int, int);
GET_T_FROM_ARRAY(Boolean, boolean);

JNIEXPORT jobjectArray JNICALL DATA_FUNC(getFloatComplexFromArray)(JNIEnv *env,
                                                                   jclass clazz,
                                                                   jlong ref) {
  jobjectArray result;
  dim_t elements = 0;
  AF_CHECK(af_get_elements(&elements, ARRAY(ref)));

  jclass cls = env->FindClass("com/arrayfire/FloatComplex");
  result = env->NewObjectArray(elements, cls, NULL);

  af_cfloat *tmp = (af_cfloat *)malloc(sizeof(af_cfloat) * elements);
  AF_CHECK(af_get_data_ptr((void *)tmp, ARRAY(ref)));

  for (int i = 0; i < elements; i++) {
    float re = tmp[i].real;
    float im = tmp[i].imag;
    jobject obj = java::createJavaObject(env, java::JavaObjects::FloatComplex, re, im);
    env->SetObjectArrayElement(result, i, obj);
  }

  free(tmp);
  return result;
}

JNIEXPORT jobjectArray JNICALL
DATA_FUNC(getDoubleComplexFromArray)(JNIEnv *env, jclass clazz, jlong ref) {
  jobjectArray result;
  dim_t elements = 0;
  AF_CHECK(af_get_elements(&elements, ARRAY(ref)));

  jclass cls = env->FindClass("com/arrayfire/DoubleComplex");
  result = env->NewObjectArray(elements, cls, NULL);

  af_cdouble *tmp = (af_cdouble *)malloc(sizeof(af_cdouble) * elements);
  AF_CHECK(af_get_data_ptr((void *)tmp, ARRAY(ref)));

  for (int i = 0; i < elements; i++) {
    double re = tmp[i].real;
    double im = tmp[i].imag;
    jobject obj =
        java::createJavaObject(env, java::JavaObjects::DoubleComplex, re, im);
    env->SetObjectArrayElement(result, i, obj);
  }

  free(tmp);
  return result;
}

END_EXTERN_C
