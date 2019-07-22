#include "java.h"

namespace java {
enum class JavaType {
  Int,
  Byte,
  Char,
  Double,
  Float,
  Long,
  String,
  Short,
  Void,
  Boolean
};

static const char *mapJavaTypeToString(JavaType type) {
  switch (type) {
    case JavaType::Int: return "I";
    case JavaType::Byte: return "B";
    case JavaType::Char: return "C";
    case JavaType::Double: return "D";
    case JavaType::Float: return "F";
    case JavaType::Long: return "J";
    case JavaType::String: return "Ljava/lang/String;";
    case JavaType::Short: return "S";
    case JavaType::Void: return "V";
    case JavaType::Boolean: return "B";
  }
}

static std::string generateFunctionSignature(
    JavaType returnType, std::initializer_list<JavaType> params) {
  std::string signature = "(";
  for (const auto &param : params) {
    signature += mapJavaTypeToString(param);
  }
  signature += ")";
  signature += mapJavaTypeToString(returnType);
  return signature;
}

void throwArrayFireException(JNIEnv *env, const char *functionName,
                             const char *file, const int line, const int code) {
  // Find and instantiate an ArrayFireException
  jclass exceptionClass = env->FindClass("com/arrayfire/ArrayFireException");
  if (env->ExceptionCheck()) {
    env->ExceptionDescribe();
  }

  const std::string constructorSig = generateFunctionSignature(
      JavaType::Void, {JavaType::Int, JavaType::String});
  jmethodID constructor =env->GetMethodID(exceptionClass, "<init>",
                                          constructorSig.c_str());

  jthrowable exception = reinterpret_cast<jthrowable>(
      env->NewObject(exceptionClass, constructor, code,
                     env->NewStringUTF("Some custom message here.")));

  // Find setLocation method and call it with
  // the function name, file and line parameters
  const std::string setLocationSig = generateFunctionSignature(
      JavaType::Void, {JavaType::String, JavaType::String, JavaType::Int});

  jmethodID setLocationID = env->GetMethodID(exceptionClass, "setLocation",
                                             setLocationSig.c_str());

  env->CallVoidMethod(exception, setLocationID, env->NewStringUTF(functionName),
                      env->NewStringUTF(file), line);

  env->Throw(exception);
  env->DeleteLocalRef(exceptionClass);
}
}  // namespace java
