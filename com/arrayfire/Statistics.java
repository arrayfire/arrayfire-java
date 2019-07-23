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

  static private native long afVar(long ref, boolean isBiased, int dim);

  static private native long afVarWeighted(long ref, long weightsRef, int dim);

  static private native double afVarAll(long ref, boolean isBiased);

  static private native double afVarAllWeighted(long ref, long weightsRef);

  static private native FloatComplex afVarAllFloatComplex(long ref, boolean isBiased);

  static private native DoubleComplex afVarAllDoubleComplex(long ref, boolean isBiased);

  static private native FloatComplex afVarAllFloatComplexWeighted(long ref, long weightsRef);

  static private native DoubleComplex afVarAllDoubleComplexWeighted(long ref, long weightsRef);

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

  static public Array var(final Array in, boolean isBiased, int dim) {
    return new Array(afVar(in.ref, isBiased, dim));
  }

  static public Array var(final Array in, final Array weights, int dim) {
    return new Array(afVarWeighted(in.ref, weights.ref, dim));
  }

  static public <T> T var(final Array in, boolean isBiased, Class<T> type) throws Exception {
    if (type == FloatComplex.class) {
      FloatComplex res = (FloatComplex) afVarAllFloatComplex(in.ref, isBiased);
      return type.cast(res);
    } else if (type == DoubleComplex.class) {
      DoubleComplex res = (DoubleComplex) afVarAllDoubleComplex(in.ref, isBiased);
      return type.cast(res);
    }

    double res = afVarAll(in.ref, isBiased);
    if (type == Float.class) {
      return type.cast(Float.valueOf((float) res));
    } else if (type == Double.class) {
      return type.cast(Double.valueOf((double) res));
    } else if (type == Integer.class) {
      return type.cast(Integer.valueOf((int) res));
    }
    throw new Exception("Unknown type");
  }

  static public <T> T var(final Array in, final Array weights, Class<T> type) throws Exception {
    if (type == FloatComplex.class) {
      FloatComplex res = (FloatComplex) afVarAllFloatComplexWeighted(in.ref, weights.ref);
      return type.cast(res);
    } else if (type == DoubleComplex.class) {
      DoubleComplex res = (DoubleComplex) afVarAllDoubleComplexWeighted(in.ref, weights.ref);
      return type.cast(res);
    }

    double res = afVarAllWeighted(in.ref, weights.ref);
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
