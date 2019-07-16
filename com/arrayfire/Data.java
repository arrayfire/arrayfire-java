package com.arrayfire;

public class Data extends ArrayFire {

  private native static long createEmptyArray(int[] dims, int type);

  private native static long createArrayFromFloat(int[] dims, float[] elems);

  private native static long createArrayFromDouble(int[] dims, double[] elems);

  private native static long createArrayFromFloatComplex(int[] dims, FloatComplex[] elems);

  private native static long createArrayFromDoubleComplex(int[] dims, DoubleComplex[] elems);

  private native static long createArrayFromInt(int[] dims, int[] elems);

  private native static long createArrayFromBoolean(int[] dims, boolean[] elems);

  private native static long createRanduArray(int[] dims, int type);

  private native static long createRandnArray(int[] dims, int type);

  private native static long createConstantsArray(double val, int[] dims, int type);

  private native static float[] getFloatFromArray(long ref);

  private native static double[] getDoubleFromArray(long ref);

  private native static int[] getIntFromArray(long ref);

  private native static boolean[] getBooleanFromArray(long ref);

  private native static FloatComplex[] getFloatComplexFromArray(long ref);

  private native static DoubleComplex[] getDoubleComplexFromArray(long ref);

  // Below version of constructor
  // allocates space on device and initializes
  // all elemets to zero

  public static void allocate(Array res, int[] dims, int type) throws Exception {
    int[] adims = Array.dim4(dims);
    long ref = createEmptyArray(adims, type);
    if (ref == 0)
      throw new Exception("Failed to create Array");
    res.set(ref);
  }

  public static void createArray(Array res, int[] dims, float[] elems) throws Exception {
    int[] adims = Array.dim4(dims);

    int total_size = 1;
    for (int i = 0; i < adims.length; i++)
      total_size *= adims[i];

    if (elems == null) {
      throw new Exception("Null elems object provided");
    }

    if (elems.length > total_size || elems.length < total_size) {
      throw new Exception("Mismatching dims and array size");
    }

    long ref = createArrayFromFloat(adims, elems);
    if (ref == 0)
      throw new Exception("Failed to create Array");
    res.set(ref);
  }

  public static void createArray(Array res, int[] dims, double[] elems) throws Exception {
    int[] adims = Array.dim4(dims);

    int total_size = 1;
    for (int i = 0; i < adims.length; i++)
      total_size *= adims[i];

    if (elems == null) {
      throw new Exception("Null elems object provided");
    }

    if (elems.length > total_size || elems.length < total_size) {
      throw new Exception("Mismatching dims and array size");
    }

    long ref = createArrayFromDouble(adims, elems);
    if (ref == 0)
      throw new Exception("Failed to create Array");
    res.set(ref);
  }

  public static void createArray(Array res, int[] dims, int[] elems) throws Exception {
    int[] adims = Array.dim4(dims);

    int total_size = 1;
    for (int i = 0; i < adims.length; i++)
      total_size *= adims[i];

    if (elems == null) {
      throw new Exception("Null elems object provided");
    }

    if (elems.length > total_size || elems.length < total_size) {
      throw new Exception("Mismatching dims and array size");
    }

    long ref = createArrayFromInt(adims, elems);
    if (ref == 0)
      throw new Exception("Failed to create Array");
    res.set(ref);
  }

  public static void createArray(Array res, int[] dims, FloatComplex[] elems) throws Exception {
    int[] adims = Array.dim4(dims);

    int total_size = 1;
    for (int i = 0; i < adims.length; i++)
      total_size *= adims[i];

    if (elems == null) {
      throw new Exception("Null elems object provided");
    }

    if (elems.length > total_size || elems.length < total_size) {
      throw new Exception("Mismatching dims and array size");
    }

    long ref = createArrayFromFloatComplex(adims, elems);
    if (ref == 0)
      throw new Exception("Failed to create Array");
    res.set(ref);
  }

  public static void createArray(Array res, int[] dims, DoubleComplex[] elems) throws Exception {
    int[] adims = Array.dim4(dims);

    int total_size = 1;
    for (int i = 0; i < adims.length; i++)
      total_size *= adims[i];

    if (elems == null) {
      throw new Exception("Null elems object provided");
    }

    if (elems.length > total_size || elems.length < total_size) {
      throw new Exception("Mismatching dims and array size");
    }

    long ref = createArrayFromDoubleComplex(adims, elems);
    if (ref == 0)
      throw new Exception("Failed to create Array");
    res.set(ref);
  }

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
    long ref = createRanduArray(adims, type);
    if (ref == 0)
      throw new Exception("Failed to create Array");
    res.set(ref);
  }

  public static void randn(Array res, int[] dims, int type) throws Exception {
    int[] adims = Array.dim4(dims);
    long ref = createRandnArray(adims, type);
    if (ref == 0)
      throw new Exception("Failed to create Array");
    res.set(ref);
  }

  public static void constant(Array res, double val, int[] dims, int type) throws Exception {
    int[] adims = Array.dim4(dims);
    long ref = createConstantsArray(val, adims, type);
    if (ref == 0)
      throw new Exception("Failed to create Array");
    res.set(ref);
  }
}
