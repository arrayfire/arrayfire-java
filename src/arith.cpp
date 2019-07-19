#include "jni_helper.h"

BEGIN_EXTERN_C

#define ARITH_FUNC(FUNC) AF_MANGLE(Arith, FUNC)

#define BINARY_OP_DEF(func)                                                \
  JNIEXPORT jlong JNICALL ARITH_FUNC(func)(JNIEnv * env, jclass clazz,     \
                                           jlong a, jlong b) {             \
    af_array ret = 0;                                                      \
    AF_CHECK(af_##func(&ret, ARRAY(a), ARRAY(b), false));                    \
    return JLONG(ret);                                                     \
  }                                                                        \
                                                                           \
  JNIEXPORT jlong JNICALL ARITH_FUNC(func##f)(JNIEnv * env, jclass clazz,  \
                                              jlong a, jfloat b) {         \
    af_array ret = 0;                                                      \
    af_array tmp = 0;                                                      \
    dim_t dims[4];                                                         \
    af_dtype ty = f32;                                                     \
    AF_CHECK(af_get_dims(dims + 0, dims + 1, dims + 2, dims + 3, ARRAY(a))); \
    AF_CHECK(af_get_type(&ty, ARRAY(a)));                                    \
    AF_CHECK(af_constant(&tmp, b, 4, dims, ty));                             \
    AF_CHECK(af_##func(&ret, ARRAY(a), tmp, false));                         \
    AF_CHECK(af_release_array(tmp));                                         \
    return JLONG(ret);                                                     \
  }                                                                        \
                                                                           \
  JNIEXPORT jlong JNICALL ARITH_FUNC(f##func)(JNIEnv * env, jclass clazz,  \
                                              float a, jlong b) {          \
    af_array ret = 0;                                                      \
    af_array tmp = 0;                                                      \
    dim_t dims[4];                                                         \
    af_dtype ty = f32;                                                     \
    AF_CHECK(af_get_dims(dims + 0, dims + 1, dims + 2, dims + 3, ARRAY(b))); \
    AF_CHECK(af_get_type(&ty, ARRAY(b)));                                    \
    AF_CHECK(af_constant(&tmp, a, 4, dims, ty));                             \
    AF_CHECK(af_##func(&ret, tmp, ARRAY(b), false));                         \
    AF_CHECK(af_release_array(tmp));                                         \
    return JLONG(ret);                                                     \
  }

BINARY_OP_DEF(add)
BINARY_OP_DEF(sub)
BINARY_OP_DEF(mul)
BINARY_OP_DEF(div)
BINARY_OP_DEF(le)
BINARY_OP_DEF(lt)
BINARY_OP_DEF(ge)
BINARY_OP_DEF(gt)
BINARY_OP_DEF(eq)
BINARY_OP_DEF(neq)
BINARY_OP_DEF(pow)

#define UNARY_OP_DEF(func)                                             \
  JNIEXPORT jlong JNICALL ARITH_FUNC(func)(JNIEnv * env, jclass clazz, \
                                           jlong a) {                  \
    af_array ret = 0;                                                  \
    AF_CHECK(af_##func(&ret, ARRAY(a)));                                 \
    return JLONG(ret);                                                 \
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

END_EXTERN_C
