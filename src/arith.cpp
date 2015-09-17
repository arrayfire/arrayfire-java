#include "jni_helper.h"

BEGIN_EXTERN_C

#define ARITH_FUNC(FUNC) AF_MANGLE(Arith, FUNC)

#define BINARY_OP_DEF(func, operation)                                  \
    JNIEXPORT jlong JNICALL ARITH_FUNC(func)(JNIEnv *env,               \
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
    JNIEXPORT jlong JNICALL ARITH_FUNC(func)(JNIEnv *env, jclass clazz, jlong a) \
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

#define SCALAR_OP1_DEF(func,operation)                  \
    JNIEXPORT jlong JNICALL ARITH_FUNC(func)(           \
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

JNIEXPORT jlong JNICALL ARITH_FUNC(pow)(JNIEnv *env, jclass clazz, jlong a, jfloat b)
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
    JNIEXPORT jlong JNICALL ARITH_FUNC(func)(JNIEnv *env, jclass clazz, jfloat a, jlong b) \
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
