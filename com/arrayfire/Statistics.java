package com.arrayfire;

public class Statistics extends ArrayFire {
  static private native long afMean(long ref, int dim);

  static private native long afMeanWeighted(long ref, long weightsRef, int dim);

  static private native DoubleComplex afMeanAll(long ref);

  static private native DoubleComplex afMeanAllWeighted(long ref, long weightsRef);

  static private native long afVar(long ref, boolean isBiased, int dim);

  static private native long afVarWeighted(long ref, long weightsRef, int dim);

  static private native DoubleComplex afVarAll(long ref, boolean isBiased);

  static private native DoubleComplex afVarAllWeighted(long ref, long weightsRef);

  static private native long afStdev(long ref, int dim);

  static private native DoubleComplex afStdevAll(long ref);

  static public Array mean(final Array in, int dim) {
    return new Array(afMean(in.ref, dim));
  }

  static public Array mean(final Array in, final Array weights, int dim) {
    return new Array(afMeanWeighted(in.ref, weights.ref, dim));
  }

  static public <T> T mean(final Array in, Class<T> type) throws Exception {
    DoubleComplex res = afMeanAll(in.ref);
    return castResult(res, type);
  }

  static public <T> T mean(final Array in, final Array weights, Class<T> type) throws Exception {
    DoubleComplex res = afMeanAllWeighted(in.ref, weights.ref);
    return castResult(res, type);
  }

  static public Array var(final Array in, boolean isBiased, int dim) {
    return new Array(afVar(in.ref, isBiased, dim));
  }

  static public Array var(final Array in, final Array weights, int dim) {
    return new Array(afVarWeighted(in.ref, weights.ref, dim));
  }

  static public <T> T var(final Array in, boolean isBiased, Class<T> type) throws Exception {
    DoubleComplex res = afVarAll(in.ref, isBiased);
    return castResult(res, type);
  }

  static public <T> T var(final Array in, final Array weights, Class<T> type) throws Exception {
    DoubleComplex res = afVarAllWeighted(in.ref, weights.ref);
    return castResult(res, type);
  }

  static public Array stdev(final Array in, int dim) {
    return new Array(afStdev(in.ref, dim));
  }

  static public <T> T stdev(final Array in, Class<T> type) throws Exception {
    DoubleComplex res = afStdevAll(in.ref);
    return castResult(res, type);
  }

  static public <T> T castResult(DoubleComplex res, Class<T> type) throws Exception {
    Object ret;
    if (type == Float.class) {
      ret = Float.valueOf((float) res.real());
    } else if (type == Double.class) {
      ret = Double.valueOf((double) res.real());
    } else if (type == Integer.class) {
      ret = Integer.valueOf((int) res.real());
    } else if (type == FloatComplex.class) {
      ret = new FloatComplex((float) res.real(), (float) res.imag());
    } else if (type == DoubleComplex.class) {
      ret = res;
    } else {
      throw new Exception("Unknown type");
    }

    return type.cast(ret);
  }
}
