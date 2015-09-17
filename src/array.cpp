#include "jni_helper.h"

BEGIN_EXTERN_C

#define ARRAY_FUNC(FUNC) AF_MANGLE(Array, FUNC)

JNIEXPORT void JNICALL ARRAY_FUNC(info)(JNIEnv *env, jclass clazz)
{
    try{
        AF_INFO();
    } catch(af::exception& e) {
    } catch(std::exception& e) {
    }
}

using af::cfloat;
using af::cdouble;

JNIEXPORT jlong JNICALL ARRAY_FUNC(createRanduArray)(JNIEnv *env, jclass clazz,
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

JNIEXPORT jlong JNICALL ARRAY_FUNC(createRandnArray)(JNIEnv *env, jclass clazz,
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


JNIEXPORT jlong JNICALL ARRAY_FUNC(createConstantsArray)(JNIEnv *env, jclass clazz,
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

JNIEXPORT jlong JNICALL ARRAY_FUNC(createEmptyArray)(JNIEnv *env, jclass clazz,
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

#define CREATE_ARRAY_T_(Ty, ty, wty)                                    \
    JNIEXPORT jlong JNICALL ARRAY_FUNC(createArrayFrom##Ty)             \
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


#define CREATE_ARRAY_T(Ty, ty) CREATE_ARRAY_T_(Ty, ty, ty)

CREATE_ARRAY_T(Float, float);
CREATE_ARRAY_T(Double, double);
CREATE_ARRAY_T(Int, int);

CREATE_ARRAY_T_(Boolean, boolean, bool);

#undef CREATE_ARRAY_T

JNIEXPORT jlong JNICALL ARRAY_FUNC(createArrayFromFloatComplex)(JNIEnv *env, jclass clazz,
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

JNIEXPORT jlong JNICALL ARRAY_FUNC(createArrayFromDoubleComplex)(JNIEnv *env, jclass clazz,
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


JNIEXPORT void JNICALL ARRAY_FUNC(destroyArray)(JNIEnv *env, jclass clazz, jlong ref)
{
    try{
        af::array *A = (af::array*)(ref);
        delete A;
    } catch(af::exception& e) {
    } catch(std::exception& e) {
    }
}

#define GET_T_FROM_ARRAY(Ty, ty)                                    \
    JNIEXPORT j##ty##Array JNICALL ARRAY_FUNC(get##Ty##FromArray)   \
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

JNIEXPORT jobjectArray JNICALL ARRAY_FUNC(getFloatComplexFromArray)(JNIEnv *env, jclass clazz, jlong ref)
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

JNIEXPORT jobjectArray JNICALL ARRAY_FUNC(getDoubleComplexFromArray)(JNIEnv *env, jclass clazz,
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

JNIEXPORT jintArray JNICALL ARRAY_FUNC(getDims)(JNIEnv *env, jclass clazz, jlong ref)
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

JNIEXPORT jint JNICALL ARRAY_FUNC(getType)(JNIEnv *env, jclass clazz, jlong ref)
{
    af::array *A = (af::array *)(ref);
    jint ty = (jint)((*A).type());
    return ty;
}

#define BINARY_OP_DEF(func, operation)                                  \
    JNIEXPORT jlong JNICALL ARRAY_FUNC(func)(JNIEnv *env,               \
                                             jclass clazz, jlong a, jlong b) \
    {                                                                   \
        jlong ret;                                                      \
        try {                                                           \
            af::array *A = (af::array*)(a);                             \
            af::array *B = (af::array*)(b);                             \
            af::array *res = new af::array();                           \
            (*res) = (*A) operation (*B);                               \
            ret = (jlong)(res);                                         \
        } catch(af::exception& e) {                                     \
            ret = 0;                                                    \
        } catch(std::exception& e) {                                    \
            ret = 0;                                                    \
        }                                                               \
        return ret;                                                     \
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

#define UNARY_OP_DEF(func)                                              \
    JNIEXPORT jlong JNICALL ARRAY_FUNC(func)(JNIEnv *env, jclass clazz, jlong a) \
    {                                                                   \
        jlong ret;                                                      \
        try {                                                           \
            af::array *A = (af::array*)(a);                             \
            af::array *res = new af::array();                           \
            (*res) = af::func( (*A) );                                  \
            ret = (jlong)(res);                                         \
        } catch(af::exception& e) {                                     \
            ret = 0;                                                    \
        } catch(std::exception& e) {                                    \
            ret = 0;                                                    \
        }                                                               \
        return ret;                                                     \
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

#define SCALAR_RET_OP_DEF(func)                     \
    JNIEXPORT jdouble JNICALL ARRAY_FUNC(func##All) \
        (JNIEnv *env, jclass clazz, jlong a)        \
    {                                               \
        try {                                       \
            af::array *A = (af::array*)(a);         \
            if (A->type() == f32)                   \
                return af::func<float>( (*A) );     \
            if (A->type() == s32)                   \
                return af::func<int>( (*A) );       \
            if (A->type() == f64)                   \
                return af::func<double>( (*A) );    \
            if (A->type() == b8)                    \
                return af::func<float>( (*A) );     \
            return af::NaN;                         \
        } catch(af::exception& e) {                 \
            return af::NaN;                         \
        } catch(std::exception& e) {                \
            return af::NaN;                         \
        }                                           \
    }

SCALAR_RET_OP_DEF(sum)
SCALAR_RET_OP_DEF(max)
SCALAR_RET_OP_DEF(min)

#define ARRAY_RET_OP_DEF(func)                          \
    JNIEXPORT jlong JNICALL ARRAY_FUNC(func)            \
        (JNIEnv *env, jclass clazz, jlong a, jint dim)  \
    {                                                   \
        jlong ret = 0;                                  \
        try {                                           \
            af::array *A = (af::array*)(a);             \
            af::array *res = new af::array();           \
            *res = af::func((*A), dim);                 \
            ret = (jlong)res;                           \
        } catch(af::exception& e) {                     \
            return 0;                                   \
        } catch(std::exception& e) {                    \
            return 0;                                   \
        }                                               \
        return ret;                                     \
    }

ARRAY_RET_OP_DEF(sum)
ARRAY_RET_OP_DEF(max)
ARRAY_RET_OP_DEF(min)


#define FFT_DEF(func)                           \
    JNIEXPORT jlong JNICALL ARRAY_FUNC(func)    \
        (JNIEnv *env, jclass clazz, jlong a)    \
    {                                           \
        jlong ret = 0;                          \
        try {                                   \
            af::array *A = (af::array*)(a);     \
            af::array *res = new af::array();   \
            *res = af::func((*A));              \
            ret = (jlong)res;                   \
        } catch(af::exception& e) {             \
            return 0;                           \
        } catch(std::exception& e) {            \
            return 0;                           \
        }                                       \
        return ret;                             \
    }

FFT_DEF(fft)
FFT_DEF(fft2)
FFT_DEF(fft3)
FFT_DEF(ifft)
FFT_DEF(ifft2)
FFT_DEF(ifft3)

#define SCALAR_OP1_DEF(func,operation)                  \
    JNIEXPORT jlong JNICALL ARRAY_FUNC(func)(           \
        JNIEnv *env, jclass clazz, jlong a, jfloat b)   \
    {                                                   \
        jlong ret;                                      \
        try {                                           \
            af::array *A = (af::array*)(a);             \
            af::array *res = new af::array();           \
            (*res) = (*A) operation (b);                \
            ret = (jlong)(res);                         \
        } catch(af::exception& e) {                     \
            ret = 0;                                    \
        } catch(std::exception& e) {                    \
            ret = 0;                                    \
        }                                               \
        return ret;                                     \
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

JNIEXPORT jlong JNICALL ARRAY_FUNC(pow)(JNIEnv *env, jclass clazz, jlong a, jfloat b)
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

#define SCALAR_OP2_DEF(func,operation)                                  \
    JNIEXPORT jlong JNICALL ARRAY_FUNC(func)(JNIEnv *env, jclass clazz, jfloat a, jlong b) \
    {                                                                   \
        jlong ret;                                                      \
        try {                                                           \
            af::array *B = (af::array*)(b);                             \
            af::array *res = new af::array();                           \
            (*res) = (a) operation (*B);                                \
            ret = (jlong)(res);                                         \
        } catch(af::exception& e) {                                     \
            ret = 0;                                                    \
        } catch(std::exception& e) {                                    \
            ret = 0;                                                    \
        }                                                               \
        return ret;                                                     \
    }

SCALAR_OP2_DEF(fsub,-)
SCALAR_OP2_DEF(fdiv,/)
SCALAR_OP2_DEF(fle,<=)
SCALAR_OP2_DEF(flt,<)
SCALAR_OP2_DEF(fge,>=)
SCALAR_OP2_DEF(fgt,>)

END_EXTERN_C
