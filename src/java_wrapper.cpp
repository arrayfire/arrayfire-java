#include <vector>
#include <arrayfire.h>
#include <af/utils.h>
#include "java_wrapper.h"

#ifdef ANDROID
#include <android/log.h>
#define  LOG(...)  __android_log_print(ANDROID_LOG_INFO, "ArrayFireJNI", __VA_ARGS__)
#else
#define LOG(msg,...) do {                       \
    printf(__FILE__":%d: " msg "\n",            \
           __LINE__, ##__VA_ARGS__);            \
  } while (0)
#endif

const int MaxDimSupported = 3;

JNIEXPORT void JNICALL Java_com_arrayfire_Array_info(JNIEnv *env, jclass clazz)
{
    try{
#ifndef ANDROID
        af::info();
#endif
    } catch(af::exception& e) {
    } catch(std::exception& e) {
    }
}

JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createRanduArray(JNIEnv *env, jclass clazz, jintArray dims, jint type)
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

JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createRandnArray(JNIEnv *env, jclass clazz, jintArray dims, jint type)
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


JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createConstantsArray(JNIEnv *env, jclass clazz, jdouble val, jintArray dims, jint type)
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

JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createEmptyArray(JNIEnv *env, jclass clazz, jintArray dims, jint type)
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

#define CREATE_ARRAY_T(Ty, ty)                                          \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createArrayFrom##Ty \
    (JNIEnv *env, jclass clazz, jintArray dims, j##ty##Array elems)     \
    {                                                                   \
        jlong ret;                                                      \
        try{                                                            \
            jint* dimptr = env->GetIntArrayElements(dims,0);            \
            j##ty* inptr= env->Get##Ty##ArrayElements(elems,0);         \
                af::array *A = new af::array(dimptr[0],dimptr[1],       \
                                             dimptr[2],dimptr[3],inptr); \
                                                                        \
                ret = (jlong)(A);                                       \
                env->ReleaseIntArrayElements(dims,dimptr,0);            \
                env->Release##Ty##ArrayElements(elems,inptr,0);         \
        } catch(af::exception& e) {                                     \
            ret = 0;                                                    \
        } catch(std::exception& e) {                                    \
            ret = 0;                                                    \
        }                                                               \
        return ret;                                                     \
    }                                                                   \

CREATE_ARRAY_T(Float, float);
CREATE_ARRAY_T(Double, double);
CREATE_ARRAY_T(Int, int);
CREATE_ARRAY_T(Boolean, boolean);

#undef CREATE_ARRAY_T

JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createArrayFromFloatComplex(JNIEnv *env, jclass clazz, jintArray dims, jobjectArray objs)
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

#ifdef AFCL
            tmp[i].s[0] = real;
            tmp[i].s[1] = imag;
#else
            tmp[i].x = real;
            tmp[i].y = imag;
#endif
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

JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_createArrayFromDoubleComplex(JNIEnv *env, jclass clazz, jintArray dims, jobjectArray objs)
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

#ifdef AFCL
            tmp[i].s[0] = real;
            tmp[i].s[1] = imag;
#else
            tmp[i].x = real;
            tmp[i].y = imag;
#endif
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


JNIEXPORT void JNICALL Java_com_arrayfire_Array_destroyArray(JNIEnv *env, jclass clazz, jlong ref)
{
    try{
        af::array *A = (af::array*)(ref);
        delete A;
    } catch(af::exception& e) {
    } catch(std::exception& e) {
    }
}

#define GET_T_FROM_ARRAY(Ty, ty)                                        \
    JNIEXPORT j##ty##Array JNICALL Java_com_arrayfire_Array_get##Ty##FromArray \
    (JNIEnv *env, jclass clazz, jlong ref)                              \
    {                                                                   \
        j##ty##Array result;                                            \
        try {                                                           \
            af::array *A = (af::array*)(ref);                           \
            int size = (*A).elements();                                 \
            result = env->New##Ty##Array(size);                         \
                if (result == NULL) {                                   \
                LOG("Something terrible happened. "                     \
                    "Couldn't allocate heap space!!!!");                \
                return NULL;                                            \
            }                                                           \
            j##ty* resf  = env->Get##Ty##ArrayElements(result, 0);      \
            (*A).host(resf);                                            \
            env->Release##Ty##ArrayElements(result, resf, 0);           \
        } catch(af::exception& e) {                                     \
            result = NULL;                                              \
        } catch(std::exception& e) {                                    \
            result = NULL;                                              \
        }                                                               \
        return result;                                                  \
    }                                                                   \

GET_T_FROM_ARRAY(Float, float);
GET_T_FROM_ARRAY(Double, double);
GET_T_FROM_ARRAY(Int, int);
GET_T_FROM_ARRAY(Boolean, boolean);

JNIEXPORT jobjectArray JNICALL Java_com_arrayfire_Array_getFloatComplexFromArray(JNIEnv *env, jclass clazz, jlong ref)
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
#ifdef AFCL
            float re = tmp[i].s[0];
            float im = tmp[i].s[1];
#else
            float re = tmp[i].x;
            float im = tmp[i].y;
#endif
            jobject obj = env->NewObject(cls, id, re, im);

            env->SetObjectArrayElement(result, i, obj);
        }

        af::array::free(tmp);

    } catch (af::exception& e) {
        result = NULL;
    } catch (std::exception& e) {
        result = NULL;
    }
    return result;
}

JNIEXPORT jobjectArray JNICALL Java_com_arrayfire_Array_getDoubleComplexFromArray(JNIEnv *env, jclass clazz, jlong ref)
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
#ifdef AFCL
            double re = tmp[i].s[0];
            double im = tmp[i].s[1];
#else
            double re = tmp[i].x;
            double im = tmp[i].y;
#endif
            jobject obj = env->NewObject(cls, id, re, im);

            env->SetObjectArrayElement(result, i, obj);
        }

        af::array::free(tmp);

    } catch (af::exception& e) {
        result = NULL;
    } catch (std::exception& e) {
        result = NULL;
    }
    return result;
}

JNIEXPORT jintArray JNICALL Java_com_arrayfire_Array_getDims(JNIEnv *env, jclass clazz, jlong ref)
{
  jintArray result;
  try {
      af::array *A = (af::array*)(ref);
      af::dim4 mydims = (*A).dims();
      result = env->NewIntArray(MaxDimSupported);
      if (result == NULL) {
          return NULL;
      }
      jint* dimsf  = env->GetIntArrayElements(result, 0);
      for(int k=0; k<MaxDimSupported; ++k)
          dimsf[k] = mydims[k];
      env->ReleaseIntArrayElements(result, dimsf, 0);
  } catch(af::exception& e) {
      result = NULL;
  } catch(std::exception& e) {
      result = NULL;
  }
  return result;
}

JNIEXPORT jint JNICALL Java_com_arrayfire_Array_getType(JNIEnv *env, jclass clazz, jlong ref)
{
    af::array *A = (af::array *)(ref);
    jint ty = (jint)((*A).type());
    return ty;
}

#define BINARY_OP_DEF(func, operation) \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_##func(JNIEnv *env, jclass clazz, jlong a, jlong b) \
    {                                           \
        jlong ret;                              \
        try {                                   \
            af::array *A = (af::array*)(a);     \
            af::array *B = (af::array*)(b);     \
            af::array *res = new af::array();   \
            (*res) = (*A) operation (*B);       \
            ret = (jlong)(res);                 \
        } catch(af::exception& e) {             \
            ret = 0;                            \
        } catch(std::exception& e) {            \
            ret = 0;                            \
        }                                       \
        return ret;                             \
    }

BINARY_OP_DEF(add,+)
BINARY_OP_DEF(sub,-)
BINARY_OP_DEF(mul,*)
BINARY_OP_DEF(div,/)
BINARY_OP_DEF(le,<=)
BINARY_OP_DEF(lt,<)
BINARY_OP_DEF(ge,>=)
BINARY_OP_DEF(gt,>)
BINARY_OP_DEF(eq,==)
BINARY_OP_DEF(ne,!=)

#define UNARY_OP_DEF(func) \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_##func(JNIEnv *env, jclass clazz, jlong a) \
    {                                           \
        jlong ret;                              \
        try {                                   \
            af::array *A = (af::array*)(a);     \
            af::array *res = new af::array();   \
            (*res) = af::func( (*A) );          \
            ret = (jlong)(res);                 \
        } catch(af::exception& e) {             \
            ret = 0;                            \
        } catch(std::exception& e) {            \
            ret = 0;                            \
        }                                       \
        return ret;                             \
    }

UNARY_OP_DEF(sin)
UNARY_OP_DEF(cos)
UNARY_OP_DEF(tan)
UNARY_OP_DEF(asin)
UNARY_OP_DEF(acos)
UNARY_OP_DEF(atan)
UNARY_OP_DEF(sinh)
UNARY_OP_DEF(cosh)
UNARY_OP_DEF(tanh)
UNARY_OP_DEF(asinh)
UNARY_OP_DEF(acosh)
UNARY_OP_DEF(atanh)
UNARY_OP_DEF(exp)
UNARY_OP_DEF(log)
UNARY_OP_DEF(abs)
UNARY_OP_DEF(sqrt)

#define SCALAR_RET_OP_DEF(func)                                     \
    JNIEXPORT jdouble JNICALL Java_com_arrayfire_Array_##func##All  \
    (JNIEnv *env, jclass clazz, jlong a)                            \
    {                                                               \
        try {                                                       \
            af::array *A = (af::array*)(a);                         \
            if (A->type() == af::f32)                               \
                return af::func<float>( (*A) );                     \
            if (A->type() == af::s32)                               \
                return af::func<int>( (*A) );                       \
            if (A->type() == af::f64)                               \
                return af::func<double>( (*A) );                    \
            if (A->type() == af::b8)                                \
                return af::func<float>( (*A) );                     \
            return af::NaN;                                         \
        } catch(af::exception& e) {                                 \
            return af::NaN;                                         \
        } catch(std::exception& e) {                                \
            return af::NaN;                                         \
        }                                                           \
    }

SCALAR_RET_OP_DEF(sum)
SCALAR_RET_OP_DEF(max)
SCALAR_RET_OP_DEF(min)

#define ARRAY_RET_OP_DEF(func)                              \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_##func \
    (JNIEnv *env, jclass clazz, jlong a, jint dim)          \
    {                                                       \
        jlong ret = 0;                                      \
        try {                                               \
            af::array *A = (af::array*)(a);                 \
            af::array *res = new af::array();               \
            *res = af::func((*A), dim);                     \
            ret = (jlong)res;                               \
        } catch(af::exception& e) {                         \
            return 0;                                       \
        } catch(std::exception& e) {                        \
            return 0;                                       \
        }                                                   \
        return ret;                                         \
    }

ARRAY_RET_OP_DEF(sum)
ARRAY_RET_OP_DEF(max)
ARRAY_RET_OP_DEF(min)

#define SCALAR_OP1_DEF(func,operation) \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_##func(JNIEnv *env, jclass clazz, jlong a, jfloat b) \
    {                                           \
        jlong ret;                              \
        try {                                   \
            af::array *A = (af::array*)(a);     \
            af::array *res = new af::array();   \
            (*res) = (*A) operation (b);        \
            ret = (jlong)(res);                 \
        } catch(af::exception& e) {             \
            ret = 0;                            \
        } catch(std::exception& e) {            \
            ret = 0;                            \
        }                                       \
        return ret;                             \
    }

SCALAR_OP1_DEF(addf,+)
SCALAR_OP1_DEF(subf,-)
SCALAR_OP1_DEF(mulf,*)
SCALAR_OP1_DEF(divf,/)
SCALAR_OP1_DEF(lef,<=)
SCALAR_OP1_DEF(ltf,<)
SCALAR_OP1_DEF(gef,>=)
SCALAR_OP1_DEF(gtf,>)
SCALAR_OP1_DEF(eqf,==)
SCALAR_OP1_DEF(nef,!=)

JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_pow(JNIEnv *env, jclass clazz, jlong a, jfloat b)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::pow((*A),b);
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

#define SCALAR_OP2_DEF(func,operation) \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Array_##func(JNIEnv *env, jclass clazz, jfloat a, jlong b) \
    {                                           \
        jlong ret;                              \
        try {                                   \
            af::array *B = (af::array*)(b);     \
            af::array *res = new af::array();   \
            (*res) = (a) operation (*B);        \
            ret = (jlong)(res);                 \
        } catch(af::exception& e) {             \
            ret = 0;                            \
        } catch(std::exception& e) {            \
            ret = 0;                            \
        }                                       \
        return ret;                             \
    }

SCALAR_OP2_DEF(fsub,-)
SCALAR_OP2_DEF(fdiv,/)
SCALAR_OP2_DEF(fle,<=)
SCALAR_OP2_DEF(flt,<)
SCALAR_OP2_DEF(fge,>=)
SCALAR_OP2_DEF(fgt,>)

void convert_uchar2float(float** out,unsigned char* in, int size, int chnls)
{
    float *temp = *out;
    int coff = size;

    for (int k = 0; k < size; k++) {
        for (int j = 0; j < chnls; j++) {
            temp[j * coff + k] = (float)(in[k * chnls + j])/255.0f;
        }
    }
}

void convert_float2uchar(unsigned char* out, float* in, int size, int chnls)
{
    int coff = size;
    for (int k = 0; k < size; k++) {
        for (int j = 0; j < chnls; j++) {
            out[k * chnls + j] = (unsigned char)(255*in[j * coff + k]);
        }
        out[k * chnls + 3] = 255;
    }
}

void blur_logic(unsigned char* bufIn, unsigned char* bufOut, int* info)
{
    int width = info[0];
    int height = info[1];
    int chnls = info[2];
    unsigned int imgsz = width*height;

    float* inptr = new float[imgsz * chnls];
    float* outptr = new float[imgsz * chnls];
    convert_uchar2float(&inptr,bufIn,imgsz, chnls);

    af::array img(width,height,chnls,inptr);
    af::array ker = af::gaussiankernel(5,5);
    af::array res = af::convolve(img, ker);
    res(af::span, af::span, 3) = 1;
    res.host(outptr);
    convert_float2uchar(bufOut, outptr, imgsz, chnls);
}

#define MORPH_OP_DEF(func) \
    JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_##func(JNIEnv *env, jclass clazz, jlong a, jlong b) \
    {                                           \
        jlong ret;                              \
        try {                                   \
            af::array *A = (af::array*)(a);     \
            af::array *B = (af::array*)(b);     \
            af::array *res = new af::array();   \
            (*res) = af::func( (*A) , (*B) );   \
            ret = (jlong)(res);                 \
        } catch(af::exception& e) {             \
            ret = 0;                            \
        } catch(std::exception& e) {            \
            ret = 0;                            \
        }                                       \
        return ret;                             \
    }


MORPH_OP_DEF(erode)
MORPH_OP_DEF(dilate)

JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_convolve(JNIEnv *env, jclass clazz, jlong a, jlong b)
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

JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_medfilt(JNIEnv *env, jclass clazz, jlong a, jint w, jint h)
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

JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_bilateral(JNIEnv *env, jclass clazz, jlong a, jfloat space, jfloat color)
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

JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_meanshift(JNIEnv *env, jclass clazz, jlong a, jfloat space, jfloat color, jint iter)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::meanshift( (*A) , space, color, iter );
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_histogram(JNIEnv *env, jclass clazz, jlong a, jint nbins)
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

JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_hist_mnmx(JNIEnv *env, jclass clazz, jlong a, jint nbins, jfloat min, jfloat max)
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

JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_rotate(JNIEnv *env, jclass clazz, jlong a, jfloat theta, jboolean crop)
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

JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_resize1(JNIEnv *env, jclass clazz, jlong a, jfloat scale, jchar method)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::resize( scale, (*A) , method );
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_resize2(JNIEnv *env, jclass clazz, jlong a, jfloat scalex, jfloat scaley, jchar method)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::resize( scalex, scaley, (*A) , method );
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}

JNIEXPORT jlong JNICALL Java_com_arrayfire_Image_resize3(JNIEnv *env, jclass clazz, jlong a, jint height, jint width, jchar method)
{
    jlong ret;
    try {
        af::array *A = (af::array*)(a);
        af::array *res = new af::array();
        (*res) = af::resize( (unsigned int)height, (unsigned int)width, (*A) , method );
        ret = (jlong)(res);
    } catch(af::exception& e) {
        ret = 0;
    } catch(std::exception& e) {
        ret = 0;
    }
    return ret;
}
