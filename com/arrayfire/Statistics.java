package com.arrayfire;

import static com.arrayfire.ArrayFire.*;

class Statistics {

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

  static private native long afMedian(long ref, int dim);

  static private native DoubleComplex afMedianAll(long ref);

  static private native long afCov(long ref, long ref2, boolean isBiased);

  static private native DoubleComplex afCorrcoef(long ref, long ref2);

  static private native long[] afTopk(long ref, int k, int dim, int order);

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

  static public Array median(final Array in, int dim) {
    return new Array(afMedian(in.ref, dim));
  }

  static public <T> T median(final Array in, Class<T> type) throws Exception {
    DoubleComplex res = afMedianAll(in.ref);
    return castResult(res, type);
  }

  static public Array cov(Array x, Array y, boolean isBiased) {
    return new Array(afCov(x.ref, y.ref, isBiased));
  }

  static public <T extends Number> T corrcoef(final Array x, final Array y, Class<T> type)
      throws Exception {
    DoubleComplex res = afCorrcoef(x.ref, y.ref);
    return castResult(res, type);
  }

  static public Array[] topk(final Array in, int k, int dim, ArrayFire.TopkOrder order) throws Exception {
    long[] refs = afTopk(in.ref, k, dim, order.getOrder());
    return new Array[] {new Array(refs[0]), new Array(refs[1])};
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
