#include <arrayfire.h>
#include <assert.h>
#include <jni.h>
#include <stdexcept>

namespace java {

void throwArrayFireException(JNIEnv *env, const char *functionName,
                             const char *file, const int line, const int code);
}  // namespace java

#define AF_CHECK(err)                                                \
  if (err != AF_SUCCESS) {                                           \
    java::throwArrayFireException(env, __func__, __FILE__, __LINE__, \
                                  (int)err);                         \
    return 0;                                                        \
  }

#define AF_CHECK_VOID(err)                                              \
    if (err != AF_SUCCESS) {                                            \
      java::throwArrayFireException(env, __func__, __FILE__, __LINE__,  \
                                  (int)err);                            \
    return;                                                             \
  }
