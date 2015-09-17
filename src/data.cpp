#include "jni_helper.h"
#include <af/complex.h>

BEGIN_EXTERN_C

#define DATA_FUNC(FUNC) AF_MANGLE(Data, FUNC)

using af::cfloat;
using af::cdouble;

JNIEXPORT jlong JNICALL DATA_FUNC(createRanduArray)(JNIEnv *env, jclass clazz,
                                                    jintArray dims, jint type)
{
    af_array ret = 0;
    jint* dimptr = env->GetIntArrayElements(dims,0);
    dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};
    env->ReleaseIntArrayElements(dims,dimptr,0);
    (af_randu(&ret, 4, tdims, (af_dtype)(type)));
    return JLONG(ret);
}

JNIEXPORT jlong JNICALL DATA_FUNC(createRandnArray)(JNIEnv *env, jclass clazz,
                                                    jintArray dims, jint type)
{
    af_array ret = 0;
    jint* dimptr = env->GetIntArrayElements(dims,0);
    dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};
    env->ReleaseIntArrayElements(dims,dimptr,0);
    (af_randn(&ret, 4, tdims, (af_dtype)(type)));
    return JLONG(ret);
}


JNIEXPORT jlong JNICALL DATA_FUNC(createConstantsArray)(JNIEnv *env, jclass clazz,
                                                        jdouble val, jintArray dims, jint type)
{
    af_array ret = 0;
    jint* dimptr = env->GetIntArrayElements(dims,0);
    dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};
    env->ReleaseIntArrayElements(dims,dimptr,0);
    (af_constant(&ret, val, 4, tdims, (af_dtype)(type)));
    return JLONG(ret);
}

JNIEXPORT jlong JNICALL DATA_FUNC(createEmptyArray)(JNIEnv *env, jclass clazz,
                                                    jintArray dims, jint type)
{
    af_array ret = 0;
    jint* dimptr = env->GetIntArrayElements(dims,0);
    dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};
    env->ReleaseIntArrayElements(dims,dimptr,0);
    (af_create_handle(&ret, 4, tdims, (af_dtype)(type)));
    return JLONG(ret);
}

#define CREATE_DATA_T(Ty, ty, dty)                                      \
    JNIEXPORT jlong JNICALL DATA_FUNC(createArrayFrom##Ty)              \
        (JNIEnv *env, jclass clazz, jintArray dims, j##ty##Array elems) \
    {                                                                   \
        af_array ret = 0;                                               \
        jint* dimptr = env->GetIntArrayElements(dims,0);                \
        dim_t tdims[4] = {dimptr[0], dimptr[1], dimptr[2], dimptr[3]};  \
        void* inptr= (void*)env->Get##Ty##ArrayElements(elems,0);       \
        (af_create_array(&ret, inptr, 4, tdims, dty));        \
        env->ReleaseIntArrayElements(dims,dimptr,0);                    \
        env->Release##Ty##ArrayElements(elems,(j##ty*)inptr,0);         \
        return JLONG(ret);                                              \
    }                                                                   \

CREATE_DATA_T(Float, float, f32);
CREATE_DATA_T(Double, double, f64);
CREATE_DATA_T(Int, int, s32);
CREATE_DATA_T(Boolean, boolean, b8);

#undef CREATE_DATA_T

JNIEXPORT jlong JNICALL DATA_FUNC(createArrayFromFloatComplex)(JNIEnv *env, jclass clazz,
                                                               jintArray dims, jobjectArray objs)
{
    af_array ret = 0;
    jint* dimptr = env->GetIntArrayElements(dims,0);
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

    (af_create_array(&ret, (void *)tmp, 4, tdims, c32));

    free(tmp);
    env->ReleaseIntArrayElements(dims,dimptr,0);
    return JLONG(ret);
}


JNIEXPORT jlong JNICALL DATA_FUNC(createArrayFromDoubleComplex)(JNIEnv *env, jclass clazz,
                                                               jintArray dims, jobjectArray objs)
{
    af_array ret = 0;
    jint* dimptr = env->GetIntArrayElements(dims,0);
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

    (af_create_array(&ret, (void *)tmp, 4, tdims, c64));

    free(tmp);
    env->ReleaseIntArrayElements(dims,dimptr,0);
    return JLONG(ret);
}


#define GET_T_FROM_ARRAY(Ty, ty)                                    \
    JNIEXPORT j##ty##Array JNICALL DATA_FUNC(get##Ty##FromArray)    \
        (JNIEnv *env, jclass clazz, jlong ref)                      \
    {                                                               \
        j##ty##Array result;                                        \
        dim_t elements = 0;                                         \
        (af_get_elements(&elements, ARRAY(ref)));         \
        result = env->New##Ty##Array(elements);                     \
        if (result == NULL) {                                       \
            LOG("Something terrible happened. "                     \
                "Couldn't allocate heap space!!!!");                \
            return NULL;                                            \
        }                                                           \
        j##ty* resf  = env->Get##Ty##ArrayElements(result, 0);      \
        (af_get_data_ptr((void *)resf, ARRAY(ref)));      \
        env->Release##Ty##ArrayElements(result, resf, 0);           \
        return result;                                              \
    }                                                               \

GET_T_FROM_ARRAY(Float, float);
GET_T_FROM_ARRAY(Double, double);
GET_T_FROM_ARRAY(Int, int);
GET_T_FROM_ARRAY(Boolean, boolean);

JNIEXPORT jobjectArray JNICALL DATA_FUNC(getFloatComplexFromArray)(JNIEnv *env, jclass clazz, jlong ref)
{
    jobjectArray result;
    dim_t elements = 0;
    (af_get_elements(&elements, ARRAY(ref)));

    jclass cls = env->FindClass("com/arrayfire/FloatComplex");
    jmethodID id = env->GetMethodID(cls, "<init>", "(FF)V");
    if (id == NULL) return NULL;

    result = env->NewObjectArray(elements, cls, NULL);

    af_cfloat *tmp = (af_cfloat *)malloc(sizeof(af_cfloat) * elements);
    (af_get_data_ptr((void *)tmp, ARRAY(ref)));

    for (int i = 0; i < elements; i++) {
        float re = tmp[i].real;
        float im = tmp[i].imag;
        jobject obj = env->NewObject(cls, id, re, im);
        env->SetObjectArrayElement(result, i, obj);
    }

    free(tmp);
    return result;
}

JNIEXPORT jobjectArray JNICALL DATA_FUNC(getDoubleComplexFromArray)(JNIEnv *env, jclass clazz, jlong ref)
{
    jobjectArray result;
    dim_t elements = 0;
    (af_get_elements(&elements, ARRAY(ref)));

    jclass cls = env->FindClass("com/arrayfire/DoubleComplex");
    jmethodID id = env->GetMethodID(cls, "<init>", "(DD)V");
    if (id == NULL) return NULL;

    result = env->NewObjectArray(elements, cls, NULL);

    af_cdouble *tmp = (af_cdouble*)malloc(sizeof(af_cdouble) * elements);
    (af_get_data_ptr((void *)tmp, ARRAY(ref)));

    for (int i = 0; i < elements; i++) {
        double re = tmp[i].real;
        double im = tmp[i].imag;
        jobject obj = env->NewObject(cls, id, re, im);
        env->SetObjectArrayElement(result, i, obj);
    }

    free(tmp);
    return result;
}

END_EXTERN_C
