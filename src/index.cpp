#include "jni_helper.h"

BEGIN_EXTERN_C

#define INDEX_FUNC(FUNC) AF_MANGLE(Index, FUNC)


JNIEXPORT jlong JNICALL INDEX_FUNC(afLookup)(JNIEnv *env, jclass clazz, jlong in,
                                             jlong index, int dim) {
    af_array ret = 0;
    AF_CHECK(af_lookup(&ret, ARRAY(in), ARRAY(index), dim));
    return JLONG(ret);
}

JNIEXPORT void JNICALL INDEX_FUNC(afCopy)(JNIEnv *env, jclass clazz,
                                          jlong dst ,jlong src,
                                          jint ndims,
                                          jobject idx0,
                                          jobject idx1, jobject idx2,
                                          jobject idx3, int dim) {
  af_index_t indices[] {java::jIndexToCIndex(env, idx0),
          java::jIndexToCIndex(env, idx1),
          java::jIndexToCIndex(env, idx2),
          java::jIndexToCIndex(env, idx3)};

  af_array lhs = ARRAY(dst);
  AF_CHECK_VOID(af_assign_gen(&lhs, lhs, ndims, indices, ARRAY(src)));
}

END_EXTERN_C
