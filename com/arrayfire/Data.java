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
  public static Array randu(int[] dims, int type) throws Exception {
      int[] adims = Array.dim4(dims);
      long ref = createRanduArray(adims, type);
      if (ref == 0)
          throw new Exception("Failed to create Array");
      return new Array(ref);
  }

  public static Array randn(int[] dims, int type) throws Exception {
      int[] adims = Array.dim4(dims);
      long ref = createRandnArray(adims, type);
      if (ref == 0)
          throw new Exception("Failed to create Array");
      return new Array(ref);
  }

  public static Array constant(double val, int[] dims, int type) throws Exception {
      int[] adims = Array.dim4(dims);
      long ref = createConstantsArray(val, adims, type);
      if (ref == 0)
          throw new Exception("Failed to create Array");
      return new Array(ref);
  }

  public static Array identity(int[] dims, int type) throws Exception {
      int[] adims = Array.dim4(dims);
      long ref = createIdentityArray(adims, type);
      if (ref == 0) {
          throw new Exception("Failed to create Array");
      }
      return new Array(ref);
  }

  public static Array identity(int[] dims) throws Exception {
      int[] adims = Array.dim4(dims);
      long ref = createIdentityArray(adims, Array.FloatType);
      return new Array(ref);
  }

}
