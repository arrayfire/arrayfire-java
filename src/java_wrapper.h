#include <jni.h>
#include <vector>

#ifndef __WRAPPER_H__
#define __WRAPPER_H__
#ifdef __cplusplus
extern "C" {
#endif

// Simply display info
JNIEXPORT void JNICALL Java_com_arrayfire_Array_info(JNIEnv *env, jclass clazz);

// Loader methods
JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createEmptyArray(JNIEnv *env, jclass clazz, jintArray dims, jint type);

JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createArrayFromFloat(JNIEnv *env, jclass clazz, jintArray dims, jfloatArray elems);
JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createArrayFromDouble(JNIEnv *env, jclass clazz, jintArray dims, jdoubleArray elems);
JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createArrayFromInt(JNIEnv *env, jclass clazz, jintArray dims, jintArray elems);
JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createArrayFromBoolean(JNIEnv *env, jclass clazz, jintArray dims, jbooleanArray elems);
JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createArrayFromFloatComplex(JNIEnv *env, jclass clazz, jintArray dims, jobjectArray objs);
JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createArrayFromDoubleComplex(JNIEnv *env, jclass clazz, jintArray dims, jobjectArray objs);
// Unloader methods
JNIEXPORT void JNICALL Java_com_arrayfire_Array_destroyArray(JNIEnv *env, jclass clazz, jlong ref);

// Data pull back methods
JNIEXPORT jfloatArray JNICALL Java_com_arrayfire_Array_getFloatFromArray(JNIEnv *env, jclass clazz, jlong ref);
JNIEXPORT jdoubleArray JNICALL Java_com_arrayfire_Array_getDoubleFromArray(JNIEnv *env, jclass clazz, jlong ref);
JNIEXPORT jintArray JNICALL Java_com_arrayfire_Array_getIntFromArray(JNIEnv *env, jclass clazz, jlong ref);
JNIEXPORT jbooleanArray JNICALL Java_com_arrayfire_Array_getBooleanFromArray(JNIEnv *env, jclass clazz, jlong ref);
JNIEXPORT jobjectArray JNICALL Java_com_arrayfire_Array_getFloatComplexFromArray(JNIEnv *env, jclass clazz, jlong ref);
JNIEXPORT jobjectArray JNICALL Java_com_arrayfire_Array_getDoubleComplexFromArray(JNIEnv *env, jclass clazz, jlong ref);

JNIEXPORT jintArray JNICALL Java_com_arrayfire_Array_getDims(JNIEnv *env, jclass clazz, jlong ref);
JNIEXPORT jint JNICALL Java_com_arrayfire_Array_getType(JNIEnv *env, jclass clazz, jlong ref);

// Library Methods

#define BINARY_OP(func) \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_##func(JNIEnv *env, jclass clazz, jlong a, jlong b);

BINARY_OP(add)
BINARY_OP(sub)
BINARY_OP(mul)
BINARY_OP(div)
BINARY_OP(le)
BINARY_OP(lt)
BINARY_OP(ge)
BINARY_OP(gt)
BINARY_OP(eq)
BINARY_OP(ne)

#define UNARY_OP(func) \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_##func(JNIEnv *env, jclass clazz, jlong a);

UNARY_OP(sin)
UNARY_OP(cos)
UNARY_OP(tan)
UNARY_OP(asin)
UNARY_OP(acos)
UNARY_OP(atan)
UNARY_OP(sinh)
UNARY_OP(cosh)
UNARY_OP(tanh)
UNARY_OP(asinh)
UNARY_OP(acosh)
UNARY_OP(atanh)
UNARY_OP(exp)
UNARY_OP(log)
UNARY_OP(abs)
UNARY_OP(sqrt)

#define SCALAR_RET_OP(func) \
    JNIEXPORT jfloat JNICALL Java_com_arrayfire_Array_##func(JNIEnv *env, jclass clazz, jlong a);

SCALAR_RET_OP(sum)
SCALAR_RET_OP(max)
SCALAR_RET_OP(min)

#define SCALAR_OP1(func) \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_##func(JNIEnv *env, jclass clazz, jlong a, jfloat b);

SCALAR_OP1(addf)
SCALAR_OP1(subf)
SCALAR_OP1(mulf)
SCALAR_OP1(divf)
SCALAR_OP1(lef)
SCALAR_OP1(ltf)
SCALAR_OP1(gef)
SCALAR_OP1(gtf)
SCALAR_OP1(eqf)
SCALAR_OP1(nef)
SCALAR_OP1(pow)

#define SCALAR_OP2(func) \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_##func(JNIEnv *env, jclass clazz, jfloat a, jlong b);

SCALAR_OP2(fsub)
SCALAR_OP2(fdiv)
SCALAR_OP2(fle)
SCALAR_OP2(flt)
SCALAR_OP2(fge)
SCALAR_OP2(fgt)

#define MORPH_OP(func) \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_##func(JNIEnv *env, jclass clazz, jlong a, jlong b);

MORPH_OP(erode)
MORPH_OP(dilate)

JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_convolve(JNIEnv *env, jclass clazz, jlong a, jlong b);

#define IMAGE_OP(func,...) \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_##func(JNIEnv *env, jclass clazz, jlong a, __VA_ARGS__);

IMAGE_OP(medfilt, jint w, jint h)
IMAGE_OP(bilateral, jfloat space, jfloat color)
IMAGE_OP(meanshift, jfloat space, jfloat color, jint iter)
IMAGE_OP(histogram, jint nbins)
IMAGE_OP(hist_mnmx, jint nbins, jfloat min, jfloat max)
IMAGE_OP(rotate, jfloat theta, jboolean crop)
IMAGE_OP(resize1, jfloat scale, jchar method)
IMAGE_OP(resize2, jfloat scalex, jfloat scaley, jchar method)
IMAGE_OP(resize3, jint height, jint width, jchar method)


#ifdef __cplusplus
}
#endif
#endif //__WRAPPER_H__
