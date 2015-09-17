#include "jni_helper.h"

BEGIN_EXTERN_C

#define SIGNAL_FUNC(FUNC) AF_MANGLE(Signal, FUNC)

#define FFT_DEF(func)                           \
    JNIEXPORT jlong JNICALL SIGNAL_FUNC(func)   \
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

END_EXTERN_C
