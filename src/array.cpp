#include "jni_helper.h"

BEGIN_EXTERN_C

#define ARRAY_FUNC(FUNC) AF_MANGLE(Array, FUNC)

JNIEXPORT void JNICALL ARRAY_FUNC(destroyArray)(JNIEnv *env, jclass clazz,
                                                jlong ref) {
  AF_CHECK_VOID(af_release_array(ARRAY(ref)));
}

JNIEXPORT void JNICALL ARRAY_FUNC(afPrint)(JNIEnv *env, jclass clazz, jlong ref,
                                           jstring name) {
  const char *str = env->GetStringUTFChars(name, 0);
  AF_CHECK_VOID(af_print_array_gen(str, ARRAY(ref), 3));
  env->ReleaseStringUTFChars(name, str);
}

JNIEXPORT jintArray JNICALL ARRAY_FUNC(getDims)(JNIEnv *env, jclass clazz,
                                                jlong ref) {
  jintArray result = env->NewIntArray(MaxDimSupported);
  if (result == NULL) {
    return NULL;
  }

  dim_t dims[4];
  AF_CHECK(af_get_dims(dims + 0, dims + 1, dims + 2, dims + 3, ARRAY(ref)));

  jint *dimsf = env->GetIntArrayElements(result, 0);

  for (int k = 0; k < MaxDimSupported; ++k) {
    dimsf[k] = dims[k];
  }
  env->ReleaseIntArrayElements(result, dimsf, 0);

  return result;
}

JNIEXPORT jint JNICALL ARRAY_FUNC(getType)(JNIEnv *env, jclass clazz,
                                           jlong ref) {
  af_dtype ty = f32;
  AF_CHECK(af_get_type(&ty, ARRAY(ref)));
  return ty;
}

JNIEXPORT jlong JNICALL ARRAY_FUNC(createEmptyArray)(JNIEnv *env, jclass clazz,
                                                    jintArray dims, jint type) {
  af_array ret = 0;
  jint *dimptr = env->GetIntArrayElements(dims, 0);
  dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};
  env->ReleaseIntArrayElements(dims, dimptr, 0);
  AF_CHECK(af_create_handle(&ret, 4, tdims, (af_dtype)(type)));
  return JLONG(ret);
}

#define CREATE_DATA_T(Ty, ty, dty)                                      \
  JNIEXPORT jlong JNICALL ARRAY_FUNC(createArrayFrom##Ty)(               \
      JNIEnv * env, jclass clazz, jintArray dims, j##ty##Array elems) { \
    af_array ret = 0;                                                   \
    jint *dimptr = env->GetIntArrayElements(dims, 0);                   \
    dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};      \
    void *inptr = (void *)env->Get##Ty##ArrayElements(elems, 0);        \
    AF_CHECK(af_create_array(&ret, inptr, 4, tdims, dty));                \
    env->ReleaseIntArrayElements(dims, dimptr, 0);                      \
    env->Release##Ty##ArrayElements(elems, (j##ty *)inptr, 0);          \
    return JLONG(ret);                                                  \
  }

CREATE_DATA_T(Float, float, f32);
CREATE_DATA_T(Double, double, f64);
CREATE_DATA_T(Int, int, s32);
CREATE_DATA_T(Boolean, boolean, b8);

#undef CREATE_DATA_T

JNIEXPORT jlong JNICALL ARRAY_FUNC(createArrayFromFloatComplex)(
    JNIEnv *env, jclass clazz, jintArray dims, jobjectArray objs) {
  af_array ret = 0;
  jint *dimptr = env->GetIntArrayElements(dims, 0);
  jint len = env->GetArrayLength(objs);

  static jclass cls;
  static jfieldID re, im;
  static bool isfirst = true;

  if (isfirst) {
    cls = env->FindClass("com/arrayfire/FloatComplex");
    re = env->GetFieldID(cls, "real", "F");
    im = env->GetFieldID(cls, "imag", "F");
    isfirst = false;
  }

  af_cfloat *tmp = (af_cfloat *)malloc(len * sizeof(af_cfloat));

  for (int i = 0; i < len; i++) {
    jobject obj = env->GetObjectArrayElement(objs, i);
    jfloat real = env->GetFloatField(obj, re);
    jfloat imag = env->GetFloatField(obj, im);

    tmp[i].real = real;
    tmp[i].imag = imag;
  }

  dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};

  AF_CHECK(af_create_array(&ret, (void *)tmp, 4, tdims, c32));

  free(tmp);
  env->ReleaseIntArrayElements(dims, dimptr, 0);
  return JLONG(ret);
}

JNIEXPORT jlong JNICALL ARRAY_FUNC(createArrayFromDoubleComplex)(
    JNIEnv *env, jclass clazz, jintArray dims, jobjectArray objs) {
  af_array ret = 0;
  jint *dimptr = env->GetIntArrayElements(dims, 0);
  jint len = env->GetArrayLength(objs);

  static jclass cls;
  static jfieldID re, im;
  static bool isfirst = true;

  if (isfirst) {
    cls = env->FindClass("com/arrayfire/DoubleComplex");
    re = env->GetFieldID(cls, "real", "D");
    im = env->GetFieldID(cls, "imag", "D");
    isfirst = false;
  }

  af_cdouble *tmp = (af_cdouble *)malloc(len * sizeof(af_cdouble));

  for (int i = 0; i < len; i++) {
    jobject obj = env->GetObjectArrayElement(objs, i);
    jdouble real = env->GetDoubleField(obj, re);
    jdouble imag = env->GetDoubleField(obj, im);

    tmp[i].real = real;
    tmp[i].imag = imag;
  }

  dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};

  AF_CHECK(af_create_array(&ret, (void *)tmp, 4, tdims, c64));

  free(tmp);
  env->ReleaseIntArrayElements(dims, dimptr, 0);
  return JLONG(ret);
}

END_EXTERN_C
