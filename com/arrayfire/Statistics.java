package com.arrayfire;

public class Statistics extends ArrayFire {
  static private native long afMean(long ref, int dim);

  static private native long afMeanWeighted(long ref, long weightsRef, int dim);

  static private native double afMeanAll(long ref);

  static private native double afMeanAllWeighted(long ref, long weightsRef);

  static private native FloatComplex afMeanAllFloatComplex(long ref);

  static private native DoubleComplex afMeanAllDoubleComplex(long ref);

  static private native FloatComplex afMeanAllFloatComplexWeighted(long ref, long weightsRef);

  static private native DoubleComplex afMeanAllDoubleComplexWeighted(long ref, long weightsRef);

  static public Array mean(final Array in, int dim) {
    return new Array(afMean(in.ref, dim));
  }

  static public Array mean(final Array in, final Array weights, int dim) {
    return new Array(afMeanWeighted(in.ref, weights.ref, dim));
  }

  static public <T> T mean(final Array in, Class<T> type) throws Exception {
    if (type == FloatComplex.class) {
      FloatComplex res = (FloatComplex) afMeanAllFloatComplex(in.ref);
      return type.cast(res);
    } else if (type == DoubleComplex.class) {
      DoubleComplex res = (DoubleComplex) afMeanAllDoubleComplex(in.ref);
      return type.cast(res);
    }

    double res = afMeanAll(in.ref);
    if (type == Float.class) {
      return type.cast(Float.valueOf((float) res));
    } else if (type == Double.class) {
      return type.cast(Double.valueOf((double) res));
    } else if (type == Integer.class) {
      return type.cast(Integer.valueOf((int) res));
    }
    throw new Exception("Unknown type");
  }

  static public <T> T mean(final Array in, final Array weights, Class<T> type) throws Exception {
    if (type == FloatComplex.class) {
      FloatComplex res = (FloatComplex) afMeanAllFloatComplexWeighted(in.ref, weights.ref);
      return type.cast(res);
    } else if (type == DoubleComplex.class) {
      System.out.println(Long.toString(weights.ref));
      DoubleComplex res = (DoubleComplex) afMeanAllDoubleComplexWeighted(in.ref, weights.ref);
      return type.cast(res);
    }

    double res = afMeanAllWeighted(in.ref, weights.ref);
    if (type == Float.class) {
      return type.cast(Float.valueOf((float) res));
    } else if (type == Double.class) {
      return type.cast(Double.valueOf((double) res));
    } else if (type == Integer.class) {
      return type.cast(Integer.valueOf((int) res));
    }
    throw new Exception("Unknown type");
  }
}
