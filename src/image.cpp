#include "jni_helper.h"

BEGIN_EXTERN_C

#define IMAGE_FUNC(FUNC) AF_MANGLE(Image, FUNC)

#define MORPH_OP_DEF(func)                                              \
    JNIEXPORT jlong JNICALL IMAGE_FUNC(func)(JNIEnv *env, jclass clazz, \
                                             jlong a, jlong b)          \
    {                                                                   \
        jlong ret;                                                      \
        try {                                                           \
            af::array *A = (af::array*)(a);                             \
            af::array *B = (af::array*)(b);                             \
            af::array *res = new af::array();                           \
            (*res) = af::func( (*A) , (*B) );                           \
            ret = (jlong)(res);                                         \
        } catch(af::exception& e) {                                     \
            ret = 0;                                                    \
        } catch(std::exception& e) {                                    \
            ret = 0;                                                    \
        }                                                               \
        return ret;                                                     \
    }


MORPH_OP_DEF(erode)
MORPH_OP_DEF(dilate)

JNIEXPORT jlong JNICALL IMAGE_FUNC(convolve)(JNIEnv *env, jclass clazz, jlong a, jlong b)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *B = (af::array*)(b);
        af::array *res = new af::array();
        (*res) = af::convolve( (*A) , (*B) );
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(medfilt)(JNIEnv *env, jclass clazz, jlong a, jint w, jint h)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::medfilt( (*A) , w, h );
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(bilateral)(JNIEnv *env, jclass clazz, jlong a, jfloat space, jfloat color)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::bilateral( (*A) , space, color );
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(meanshift)(JNIEnv *env, jclass clazz, jlong a, jfloat space, jfloat color, jint iter)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::meanShift( (*A) , space, color, iter );
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(histogram)(JNIEnv *env, jclass clazz, jlong a, jint nbins)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::histogram( (*A) , nbins );
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(hist_mnmx)(JNIEnv *env, jclass clazz, jlong a, jint nbins, jfloat min, jfloat max)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::histogram( (*A) , nbins, min, max );
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(rotate)(JNIEnv *env, jclass clazz, jlong a, jfloat theta, jboolean crop)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::rotate( (*A) , theta, crop );
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(resize1)(JNIEnv *env, jclass clazz, jlong a, jfloat scale, jchar method)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::resize( scale, (*A));
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(resize2)(JNIEnv *env, jclass clazz, jlong a, jfloat scalex, jfloat scaley, jchar method)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::resize( scalex, scaley, (*A));
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL IMAGE_FUNC(resize3)(JNIEnv *env, jclass clazz, jlong a, jint height, jint width, jchar method)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::resize( (unsigned int)height, (unsigned int)width, (*A));
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

END_EXTERN_C
