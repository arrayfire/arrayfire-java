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

JNIEXPORT void JNICALL ARRAY_FUNC(destroyArray)(JNIEnv *env, jclass clazz, jlong ref)
{
    try{
        af::array *A = (af::array*)(ref);
        delete A;
    } catch(af::exception& e) {
    } catch(std::exception& e) {
    }
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

END_EXTERN_C
