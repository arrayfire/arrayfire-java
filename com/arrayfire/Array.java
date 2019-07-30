package com.arrayfire;

public class Array extends AFLibLoader implements AutoCloseable {

  private native static void destroyArray(long ref);

  private native static int[] getDims(long ref);

  private native static int getType(long ref);

  private native static long createEmptyArray(int[] dims, int type);

  private native static long createArrayFromFloat(int[] dims, float[] elems);

  private native static long createArrayFromDouble(int[] dims, double[] elems);

  private native static long createArrayFromFloatComplex(int[] dims, FloatComplex[] elems);

  private native static long createArrayFromDoubleComplex(int[] dims, DoubleComplex[] elems);

  private native static long createArrayFromInt(int[] dims, int[] elems);

  private native static long createArrayFromBoolean(int[] dims, boolean[] elems);

  private native static String afToString(long ref, String name);

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
    int[] adims = Array.dim4(dims);
    set(createEmptyArray(adims, type));
  }

  public Array(int[] dims, float[] elems) throws IllegalArgumentException {
    int[] adims = Array.dim4(dims);

    int total_size = 1;
    for (int dim : adims) {
      total_size *= dim;
    }

    if (elems == null) {
      throw new IllegalArgumentException("Null elems object provided");
    }

    if (elems.length > total_size || elems.length < total_size) {
      throw new IllegalArgumentException("Mismatching dims and array size");
    }

    set(createArrayFromFloat(adims, elems));
  }

  public Array(int[] dims, double[] elems) throws IllegalArgumentException {
    int[] adims = Array.dim4(dims);

    int total_size = 1;
    for (int dim : adims) {
      total_size *= dim;
    }

    if (elems == null) {
      throw new IllegalArgumentException("Null elems object provided");
    }

    if (elems.length > total_size || elems.length < total_size) {
      throw new IllegalArgumentException("Mismatching dims and array size");
    }

    set(createArrayFromDouble(adims, elems));
  }

  public Array(int[] dims, int[] elems) throws IllegalArgumentException {
    int[] adims = Array.dim4(dims);

    int total_size = 1;
    for (int dim : adims) {
      total_size *= dim;
    }
    if (elems == null) {
      throw new IllegalArgumentException("Null elems object provided");
    }

    if (elems.length > total_size || elems.length < total_size) {
      throw new IllegalArgumentException("Mismatching dims and array size");
    }

    set(createArrayFromInt(adims, elems));
  }

  public Array(int[] dims, FloatComplex[] elems) throws Exception {
    int[] adims = Array.dim4(dims);

    int total_size = 1;
    for (int dim : adims) {
      total_size *= dim;
    }

    if (elems == null) {
      throw new Exception("Null elems object provided");
    }

    if (elems.length > total_size || elems.length < total_size) {
      throw new Exception("Mismatching dims and array size");
    }

    set(createArrayFromFloatComplex(adims, elems));
  }

  public Array(int[] dims, DoubleComplex[] elems) throws IllegalArgumentException {
    int[] adims = Array.dim4(dims);

    int total_size = 1;
    for (int dim : adims) {
      total_size *= dim;
    }

    if (elems == null) {
      throw new IllegalArgumentException("Null elems object provided");
    }

    if (elems.length > total_size || elems.length < total_size) {
      throw new IllegalArgumentException("Mismatching dims and array size");
    }

    set(createArrayFromDoubleComplex(adims, elems));
  }

  protected void set(long other_ref) {
    if (ref != 0)
      destroyArray(ref);
    ref = other_ref;
  }

  public int[] dims() {
    return getDims(ref);
  }

  public ArrayFire.Type type() {
      return ArrayFire.Type.fromInt(getType(ref));
  }

  protected static int[] dim4(int[] dims) throws IllegalArgumentException {

    if (dims == null) {
      throw new IllegalArgumentException("Null dimensions object provided");
    } else if (dims.length > 4) {
      throw new IllegalArgumentException("ArrayFire supports up to 4 dimensions only");
    }

    int[] adims = new int[] { 1, 1, 1, 1 };
    System.arraycopy(dims, 0, adims, 0, dims.length);
    return adims;
  }

  protected void assertType(ArrayFire.Type ty) throws Exception {

    ArrayFire.Type myType = type();

    if (myType != ty) {
      String str = "Type mismatch: ";
      str = str + "Requested " + ty;
      str = str + ". Found " + myType;
      throw new IllegalArgumentException(str);
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

    public boolean isBool() {
        return type() == ArrayFire.Type.Boolean;
    }

  @Override
  public String toString() {
      return afToString(ref, "No name");
  }

  public String toString(String name) {
      return afToString(ref, name);
  }

  @Override
  public void close() throws Exception {
    if (ref != 0)
      destroyArray(ref);
  }
}
