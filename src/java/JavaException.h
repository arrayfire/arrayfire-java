#include "ThrownJavaException.h"

namespace java {
//"java/lang/NoSuchFieldException"
//"java/lang/NullPointerException"
//"java/security/InvalidParameterException"
class JavaException : public ThrownJavaException {
 public:
  JavaException(JNIEnv *env, const char *type = "", const char *message = "")
      : ThrownJavaException(type + std::string(" ") + message) {
    jclass exceptionClass = env->FindClass(type);
    if (exceptionClass != NULL) {
      env->ThrowNew(exceptionClass, message);
    }
  }
};

void throwArrayFireException(JNIEnv *env, const char *functionName,
                             const char *file, const int line, const int code);
}  // namespace java

#define CATCH_AND_THROW_JAVA(env)                                          \
  catch (const java::ThrownJavaException &) {                              \
  }                                                                        \
  catch (const std::bad_alloc &rhs) {                                      \
    java::JavaException(env, "java/lang/OutOfMemoryError", rhs.what());    \
  }                                                                        \
  catch (const std::ios_base::failure &rhs) {                              \
    java::JavaException(env, "java/io/IOException", rhs.what());           \
  }                                                                        \
  catch (const std::bad_cast &e) {                                         \
    java::JavaException(env, "java/lang/ClassCastException", e.what());    \
  }                                                                        \
  catch (const std::exception &e) {                                        \
    java::JavaException(env, "java/lang/Error", e.what());                 \
  }                                                                        \
  catch (...) {                                                            \
    java::JavaException(env, "java/lang/Error", "Unknown exception type"); \
  }

#define ARRAYFIRE_THROWS(err)                                        \
  if (err != AF_SUCCESS) {                                           \
    java::throwArrayFireException(env, __func__, __FILE__, __LINE__, \
                                  (int)err);                         \
  }

#define THROWS(fn)       \
  try {                  \
    ARRAYFIRE_THROWS(fn) \
  }                      \
  CATCH_AND_THROW_JAVA(env)
