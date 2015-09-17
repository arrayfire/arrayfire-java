#include "jni_helper.h"

BEGIN_EXTERN_C

#define ALGO_FUNC(FUNC) AF_MANGLE(Algorithm, FUNC)

#define SCALAR_RET_OP_DEF(func)                     \
    JNIEXPORT jdouble JNICALL ALGO_FUNC(func##All)  \
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
    JNIEXPORT jlong JNICALL ALGO_FUNC(func)             \
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

END_EXTERN_C
