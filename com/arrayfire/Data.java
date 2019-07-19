package com.arrayfire;

public class Data extends ArrayFire {

  private native static long createRanduArray(int[] dims, int type);

  private native static long createRandnArray(int[] dims, int type);

  private native static long createConstantsArray(double val, int[] dims, int type);

  private native static float[] getFloatFromArray(long ref);

  private native static double[] getDoubleFromArray(long ref);

  private native static int[] getIntFromArray(long ref);

  private native static boolean[] getBooleanFromArray(long ref);

  private native static FloatComplex[] getFloatComplexFromArray(long ref);

  private native static DoubleComplex[] getDoubleComplexFromArray(long ref);

  private native static long createIdentityArray(int[] dims, int type);

  public static float[] getFloatArray(Array A) throws Exception {
    A.assertType(Array.FloatType);
    return getFloatFromArray(A.ref);
  }

  public static double[] getDoubleArray(Array A) throws Exception {
    A.assertType(Array.DoubleType);
    return getDoubleFromArray(A.ref);
  }

  public static FloatComplex[] getFloatComplexArray(Array A) throws Exception {
    A.assertType(Array.FloatComplexType);
    return getFloatComplexFromArray(A.ref);
  }

  public static DoubleComplex[] getDoubleComplexArray(Array A) throws Exception {
    A.assertType(Array.DoubleComplexType);
    return getDoubleComplexFromArray(A.ref);
  }

  public static int[] getIntArray(Array A) throws Exception {
    A.assertType(Array.IntType);
    return getIntFromArray(A.ref);
  }

  public static boolean[] getBooleanArray(Array A) throws Exception {
    A.assertType(Array.BooleanType);
    return getBooleanFromArray(A.ref);
  }

  // Binary operations
  public static void randu(Array res, int[] dims, int type) throws Exception {
    int[] adims = Array.dim4(dims);
    res.set(createRanduArray(adims, type));
  }

  public static void randn(Array res, int[] dims, int type) throws Exception {
    int[] adims = Array.dim4(dims);
    res.set(createRandnArray(adims, type));
  }

  public static void constant(Array res, double val, int[] dims, int type) throws Exception {
    int[] adims = Array.dim4(dims);
    res.set(createConstantsArray(val, adims, type));
  }

  public static void identity(Array res, int[] dims, int type) throws Exception {
    int[] adims = Array.dim4(dims);
    res.set(createIdentityArray(adims, type));
  }

  public static void identity(Array res, int[] dims) throws Exception {
    identity(res, dims, Array.FloatType);
  }

}
