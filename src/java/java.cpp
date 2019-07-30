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
  Boolean,
  Seq
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
    case JavaType::Seq: return "Lcom/arrayfire/Seq";
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
  jmethodID constructor =
      env->GetMethodID(exceptionClass, "<init>", constructorSig.c_str());

  jthrowable exception = static_cast<jthrowable>(
      env->NewObject(exceptionClass, constructor, code,
                     env->NewStringUTF("Some custom message here.")));

  // Find setLocation method and call it with
  // the function name, file and line parameters
  const std::string setLocationSig = generateFunctionSignature(
      JavaType::Void, {JavaType::String, JavaType::String, JavaType::Int});

  jmethodID setLocationID =
      env->GetMethodID(exceptionClass, "setLocation", setLocationSig.c_str());

  env->CallVoidMethod(exception, setLocationID, env->NewStringUTF(functionName),
                      env->NewStringUTF(file), line);

  env->Throw(exception);
  env->DeleteLocalRef(exceptionClass);
}

template <typename... Args>
jobject createJavaObject(JNIEnv *env, JavaObjects objectType, Args... args) {
  switch (objectType) {
    case JavaObjects::FloatComplex: {
      jclass cls = env->FindClass("com/arrayfire/FloatComplex");
      std::string sig = generateFunctionSignature(
          JavaType::Void, {JavaType::Float, JavaType::Float});
      jmethodID id = env->GetMethodID(cls, "<init>", sig.c_str());
      jobject obj = env->NewObject(cls, id, args...);
      return obj;

    } break;
    case JavaObjects::DoubleComplex: {
      jclass cls = env->FindClass("com/arrayfire/DoubleComplex");
      std::string sig = generateFunctionSignature(
          JavaType::Void, {JavaType::Double, JavaType::Double});
      jmethodID id = env->GetMethodID(cls, "<init>", sig.c_str());
      jobject obj = env->NewObject(cls, id, args...);
      return obj;
    } break;
  }
}

af_index_t jIndexToCIndex(JNIEnv *env, jobject obj) {
    af_index_t index;
    jclass cls = env->GetObjectClass(obj);
    assert(cls == env->FindClass("com/arrayfire/Index"));

    std::string getIsSeqSig = generateFunctionSignature(JavaType::Boolean, {});
    jmethodID getIsSeqId = env->GetMethodID(cls, "isSeq", getIsSeqSig.c_str());
    assert(getIsSeqId != NULL);
    index.isSeq = env->CallBooleanMethod(obj, getIsSeqId);

    std::string getIsBatchSig = generateFunctionSignature(JavaType::Boolean, {});
    jmethodID getIsBatchId = env->GetMethodID(cls, "isBatch", getIsBatchSig.c_str());
    assert(getIsBatchId != NULL);
    index.isBatch = env->CallBooleanMethod(obj, getIsBatchId);

    if (index.isSeq) {
      // get seq object
      std::string getSeqSig = generateFunctionSignature(JavaType::Seq, {});
      jmethodID getSeqId = env->GetMethodID(cls, "getSeq", getSeqSig.c_str());
      assert(getSeqId != NULL);
      jobject seq = env->CallObjectMethod(obj, getSeqId);

      // get seq fields
      jclass seqCls = env->GetObjectClass(seq);
      assert(seqCls == env->FindClass("com/arrayfire/Seq"));

      jfieldID beginID = env->GetFieldID(seqCls, "begin", mapJavaTypeToString(JavaType::Double));
      assert(beginID != NULL);
      double begin = env->GetDoubleField(seq, beginID);

      jfieldID endID = env->GetFieldID(seqCls, "end", mapJavaTypeToString(JavaType::Double));
      assert(endID != NULL);
      double end = env->GetDoubleField(seq, endID);

      jfieldID stepID = env->GetFieldID(seqCls, "step", mapJavaTypeToString(JavaType::Double));
      assert(stepID != NULL);
      double step = env->GetDoubleField(seq, stepID);

      index.idx.seq = af_make_seq(begin, end, step);
    } else {
      std::string getArrSig = generateFunctionSignature(JavaType::Long, {});
      jmethodID getArrId = env->GetMethodID(cls, "getArrRef", getArrSig.c_str());
      assert(getArrId != NULL);
      long arrRef = env->CallLongMethod(obj, getArrId);
      index.idx.arr = (af_array)arrRef;
    }
    return index;
}

#define INSTANTIATE(type) \
  template jobject createJavaObject<type>(JNIEnv *, JavaObjects, type, type);

INSTANTIATE(float)
INSTANTIATE(double)

#undef INSTANTIATE

}  // namespace java
