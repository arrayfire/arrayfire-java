
namespace java {
class ThrownJavaException : std::runtime_error {
 public:
  ThrownJavaException() : std::runtime_error("") {}
  ThrownJavaException(const std::string &msg) : std::runtime_error(msg) {}

  static inline void assert_no_exception(JNIEnv *env) {
    if (env->ExceptionCheck() == JNI_TRUE)
      throw ThrownJavaException("assert_no_exception");
  }
};
}  // namespace java
