#include <stdexcept>
#include <jni.h>
#include <arrayfire.h>
#include <assert.h>

class ThrownJavaException : std::runtime_error {

public:
  ThrownJavaException() : std::runtime_error("") {}
  ThrownJavaException(const std::string &msg) : std::runtime_error(msg) {}

  static inline void assert_no_exception(JNIEnv *env) {
    if (env->ExceptionCheck() == JNI_TRUE)
      throw ThrownJavaException("assert_no_exception");
  }
};

// Throws a Java exception using the full path
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
    static void throwArrayFireException(JNIEnv *env, const char *functionName, const char *file,
                                        const int line, const int code);
};

#define CATCH_AND_THROW_JAVA(env)                                       \
      catch (const ThrownJavaException &) {                             \
    } catch (const std::bad_alloc &rhs) {                               \
        JavaException(env, "java/lang/OutOfMemoryError", rhs.what());   \
    } catch (const std::ios_base::failure &rhs) {                       \
        JavaException(env, "java/io/IOException", rhs.what());          \
    } catch(const std::bad_cast &e) {                                   \
        JavaException(env, "java/lang/ClassCastException", e.what());   \
    } catch (const std::exception &e) {                                 \
        JavaException(env, "java/lang/Error", e.what());                \
    } catch (...) {                                                     \
        JavaException(env, "java/lang/Error", "Unknown exception type");\
    }                                                                   \

#define ARRAYFIRE_THROWS(err)                                           \
    if (err != AF_SUCCESS) {                                            \
    JavaException::throwArrayFireException(env, __func__, __FILE__,     \
                                           __LINE__, (int)err);         \
    }

#define THROWS(fn)                                                      \
    try {                                                               \
        ARRAYFIRE_THROWS(fn)                                       \
    }                                                                   \
    CATCH_AND_THROW_JAVA(env)                                           \

