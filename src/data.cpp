#include "jni_helper.h"

BEGIN_EXTERN_C

#define DATA_FUNC(FUNC) AF_MANGLE(Data, FUNC)

using af::cfloat;
using af::cdouble;

JNIEXPORT jlong JNICALL DATA_FUNC(createRanduArray)(JNIEnv *env, jclass clazz,
                                                     jintArray dims, jint type)
{
    jlong ret;
    try{
        jint* dimptr = env->GetIntArrayElements(dims,0);
        af::dtype ty = (af::dtype)(type);
        af::array *A = new af::array(dimptr[0],dimptr[1],dimptr[2],dimptr[3], ty);
        *A = af::randu(dimptr[0],dimptr[1],dimptr[2], ty);
        ret = (jlong)(A);
        env->ReleaseIntArrayElements(dims,dimptr,0);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL DATA_FUNC(createRandnArray)(JNIEnv *env, jclass clazz,
                                                     jintArray dims, jint type)
{
    jlong ret;
    try{
        jint* dimptr = env->GetIntArrayElements(dims,0);
        af::dtype ty = (af::dtype)(type);
        af::array *A = new af::array(dimptr[0],dimptr[1],dimptr[2],dimptr[3], ty);
        *A = af::randn(dimptr[0],dimptr[1],dimptr[2], ty);
        ret = (jlong)(A);
        env->ReleaseIntArrayElements(dims,dimptr,0);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}


JNIEXPORT jlong JNICALL DATA_FUNC(createConstantsArray)(JNIEnv *env, jclass clazz,
                                                         jdouble val, jintArray dims, jint type)
{
    jlong ret;
    try{
        jint* dimptr = env->GetIntArrayElements(dims,0);
        af::dtype ty = (af::dtype)(type);
        af::array *A = new af::array(dimptr[0],dimptr[1],dimptr[2],dimptr[3], ty);
        *A = af::constant(val, dimptr[0],dimptr[1],dimptr[2], ty);
        ret = (jlong)(A);
        env->ReleaseIntArrayElements(dims,dimptr,0);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL DATA_FUNC(createEmptyArray)(JNIEnv *env, jclass clazz,
                                                     jintArray dims, jint type)
{
    jlong ret;
    try{
        jint* dimptr = env->GetIntArrayElements(dims,0);
        af::dtype ty = (af::dtype)(type);
        af::array *A = new af::array(dimptr[0],dimptr[1],dimptr[2],dimptr[3], ty);
        *A = af::constant(0.0f,dimptr[0],dimptr[1],dimptr[2], ty);
        ret = (jlong)(A);
        env->ReleaseIntArrayElements(dims,dimptr,0);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

#define CREATE_DATA_T_(Ty, ty, wty)                                     \
    JNIEXPORT jlong JNICALL DATA_FUNC(createArrayFrom##Ty)              \
        (JNIEnv *env, jclass clazz, jintArray dims, j##ty##Array elems) \
    {                                                                   \
        jlong ret;                                                      \
        try{                                                            \
            jint* dimptr = env->GetIntArrayElements(dims,0);            \
            wty* inptr= (wty*)env->Get##Ty##ArrayElements(elems,0);     \
            af::array *A = new af::array(dimptr[0],dimptr[1],           \
                                         dimptr[2],dimptr[3],inptr);    \
                                                                        \
            ret = (jlong)(A);                                           \
            env->ReleaseIntArrayElements(dims,dimptr,0);                \
            env->Release##Ty##ArrayElements(elems,(j##ty*)inptr,0);     \
        } catch(af::exception& e) {                                     \
            ret = 0;                                                    \
        } catch(std::exception& e) {                                    \
            ret = 0;                                                    \
        }                                                               \
        return ret;                                                     \
    }                                                                   \


#define CREATE_DATA_T(Ty, ty) CREATE_DATA_T_(Ty, ty, ty)

CREATE_DATA_T(Float, float);
CREATE_DATA_T(Double, double);
CREATE_DATA_T(Int, int);

CREATE_DATA_T_(Boolean, boolean, bool);

#undef CREATE_DATA_T

JNIEXPORT jlong JNICALL DATA_FUNC(createArrayFromFloatComplex)(JNIEnv *env, jclass clazz,
                                                                jintArray dims, jobjectArray objs)
{
    jlong ret;
    try{
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


        cfloat *tmp = new cfloat[len];

        for (int i = 0; i < len; i++) {
            jobject obj = env->GetObjectArrayElement(objs, i);
            jfloat real = env->GetFloatField(obj, re);
            jfloat imag = env->GetFloatField(obj, im);

            tmp[i].real = real;
            tmp[i].imag = imag;
        }

        af::array *A = new af::array(dimptr[0],dimptr[1],dimptr[2],dimptr[3],tmp);
        delete[] tmp;

        ret = (jlong)(A);
        env->ReleaseIntArrayElements(dims,dimptr,0);

    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL DATA_FUNC(createArrayFromDoubleComplex)(JNIEnv *env, jclass clazz,
                                                                 jintArray dims, jobjectArray objs)
{
    jlong ret;
    try{
        jint* dimptr = env->GetIntArrayElements(dims,0);
        jint len = env->GetArrayLength(objs);

        static jclass cls;
        static jfieldID re, im;
        static bool isfirst = true;

        cdouble *tmp = new cdouble[len];

        for (int i = 0; i < len; i++) {
            jobject obj = env->GetObjectArrayElement(objs, i);

            if (isfirst) {
                cls = env->GetObjectClass(obj);
                re = env->GetFieldID(cls, "real", "F");
                im = env->GetFieldID(cls, "imag", "F");
                isfirst = false;
            }

            jdouble real = env->GetDoubleField(obj, re);
            jdouble imag = env->GetDoubleField(obj, im);

            tmp[i].real = real;
            tmp[i].imag = imag;
        }

        af::array *A = new af::array(dimptr[0],dimptr[1],dimptr[2],dimptr[3],tmp);
        delete[] tmp;

        ret = (jlong)(A);
        env->ReleaseIntArrayElements(dims,dimptr,0);

    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}


#define GET_T_FROM_ARRAY(Ty, ty)                                    \
    JNIEXPORT j##ty##Array JNICALL DATA_FUNC(get##Ty##FromArray)   \
        (JNIEnv *env, jclass clazz, jlong ref)                      \
    {                                                               \
        j##ty##Array result;                                        \
        try {                                                       \
            af::array *A = (af::array*)(ref);                       \
            int size = (*A).elements();                             \
            result = env->New##Ty##Array(size);                     \
            if (result == NULL) {                                   \
                LOG("Something terrible happened. "                 \
                    "Couldn't allocate heap space!!!!");            \
                return NULL;                                        \
            }                                                       \
            j##ty* resf  = env->Get##Ty##ArrayElements(result, 0);  \
            (*A).host(resf);                                        \
            env->Release##Ty##ArrayElements(result, resf, 0);       \
        } catch(af::exception& e) {                                 \
            result = NULL;                                          \
        } catch(std::exception& e) {                                \
            result = NULL;                                          \
        }                                                           \
        return result;                                              \
    }                                                               \

GET_T_FROM_ARRAY(Float, float);
GET_T_FROM_ARRAY(Double, double);
GET_T_FROM_ARRAY(Int, int);
GET_T_FROM_ARRAY(Boolean, boolean);

JNIEXPORT jobjectArray JNICALL DATA_FUNC(getFloatComplexFromArray)(JNIEnv *env, jclass clazz, jlong ref)
{
    jobjectArray result;
    try {
        af::array *A = (af::array *)(ref);
        int size = (*A).elements();

        jclass cls = env->FindClass("com/arrayfire/FloatComplex");
        jmethodID id = env->GetMethodID(cls, "<init>", "(FF)V");
        if (id == NULL) return NULL;

        result = env->NewObjectArray(size, cls, NULL);

        cfloat *tmp = (*A).host<cfloat>();

        for (int i = 0; i < size; i++) {
            float re = tmp[i].real;
            float im = tmp[i].imag;
            jobject obj = env->NewObject(cls, id, re, im);

            env->SetObjectArrayElement(result, i, obj);
        }

        delete[] tmp;
    } catch (af::exception& e) {
        result = NULL;
    } catch (std::exception& e) {
        result = NULL;
    }
    return result;
}

JNIEXPORT jobjectArray JNICALL DATA_FUNC(getDoubleComplexFromArray)(JNIEnv *env, jclass clazz,
                                                                     jlong ref)
{
    jobjectArray result;
    try {
        af::array *A = (af::array *)(ref);
        int size = (*A).elements();

        jclass cls = env->FindClass("com/arrayfire/DoubleComplex");
        jmethodID id = env->GetMethodID(cls, "<init>", "(FF)V");
        if (id == NULL) return NULL;

        result = env->NewObjectArray(size, cls, NULL);

        cdouble *tmp = (*A).host<cdouble>();

        for (int i = 0; i < size; i++) {
            double re = tmp[i].real;
            double im = tmp[i].imag;
            jobject obj = env->NewObject(cls, id, re, im);

            env->SetObjectArrayElement(result, i, obj);
        }

        delete[] tmp;
    } catch (af::exception& e) {
        result = NULL;
    } catch (std::exception& e) {
        result = NULL;
    }
    return result;
}

END_EXTERN_C
