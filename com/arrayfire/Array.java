package com.arrayfire;

public class Array extends ArrayFire implements AutoCloseable {

  public static final int FloatType = 0;
  public static final int FloatComplexType = 1;
  public static final int DoubleType = 2;
  public static final int DoubleComplexType = 3;
  public static final int BooleanType = 4;
  public static final int IntType = 5;

  private native static void destroyArray(long ref);

  private native static int[] getDims(long ref);

  private native static int getType(long ref);

  private native static long createIdentityArray(int[] dims, int type);

  // Global reference to JVM object
  // to persist between JNI calls
  protected long ref;

  public Array() {
    ref = 0;
  }

  protected Array(long other_ref) {
    ref = other_ref;
  }

  public Array(int[] dims, int type) throws Exception {
    Data.allocate(this, dims, type);
  }

  public Array(int[] dims, float[] elems) throws Exception {
    Data.createArray(this, dims, elems);
  }

  public Array(int[] dims, int[] elems) throws Exception {
    Data.createArray(this, dims, elems);
  }

  public Array(int[] dims, FloatComplex[] elems) throws Exception {
    Data.createArray(this, dims, elems);
  }

  public Array(int[] dims, DoubleComplex[] elems) throws Exception {
    Data.createArray(this, dims, elems);
  }

  protected void set(long other_ref) {
    if (ref != 0)
      destroyArray(ref);
    ref = other_ref;
  }

  public int[] dims() {
    return getDims(ref);
  }

  public int type() {
    return getType(ref);
  }

  public String typeName(int ty) throws Exception {
    switch (ty) {
    case FloatType: return "float";
    case DoubleType: return "double";
    case IntType: return "int";
    case BooleanType: return "boolean";
    case FloatComplexType: return "FloatComplex";
    case DoubleComplexType: return "DoubleComplex";
    default: throw new Exception("Unknown type");
    }
  }

  protected static int[] dim4(int[] dims) throws Exception {

    if (dims == null) {
      throw new Exception("Null dimensions object provided");
    } else if (dims.length > 4) {
      throw new Exception("ArrayFire supports up to 4 dimensions only");
    }

    int[] adims;
    adims = new int[] { 1, 1, 1, 1 };
    for (int i = 0; i < dims.length; i++)
      adims[i] = dims[i];

    return adims;
  }

  protected void assertType(int ty) throws Exception {

    int myType = type();

    if (myType != ty) {
      String str = "Type mismatch: ";
      str = str + "Requested " + typeName(ty);
      str = str + ". Found " + typeName(myType);
      throw new Exception(str);
    }
    return;
  }

  public float[] getFloatArray() throws Exception {
    return Data.getFloatArray(this);
  }

  public double[] getDoubleArray() throws Exception {
    return Data.getDoubleArray(this);
  }

  public FloatComplex[] getFloatComplexArray() throws Exception {
    return Data.getFloatComplexArray(this);
  }

  public DoubleComplex[] getDoubleComplexArray() throws Exception {
    return Data.getDoubleComplexArray(this);
  }

  public int[] getIntArray() throws Exception {
    return Data.getIntArray(this);
  }

  public boolean[] getBooleanArray() throws Exception {
    return Data.getBooleanArray(this);
  }

  // Binary operations
  public static Array randu(int[] dims, int type) throws Exception {
    Array array = new Array();
    Data.randu(array, dims, type);
    return array;
  }

  public static Array randn(int[] dims, int type) throws Exception {
    Array array = new Array();
    Data.randn(array, dims, type);
    return array;
  }

  public static Array constant(double val, int[] dims, int type) throws Exception {
    Array array = new Array();
    Data.constant(array, val, dims, type);
    return array;
  }

  public static Array identity(int[] dims, int type) throws Exception {
    int[] adims = Array.dim4(dims);
    long ref = createIdentityArray(adims, FloatType);
    if (ref == 0) {
      throw new Exception("Failed to create Array");
    }
    return new Array(ref);
  }

  public static Array identity(int[] dims) throws Exception {
    int[] adims = Array.dim4(dims);
    long ref = createIdentityArray(adims, FloatType);
    return new Array(ref);
  }

  @Override
  public void close() throws Exception {
    if (ref != 0)
      destroyArray(ref);
  }
}
