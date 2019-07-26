package com.arrayfire;

public class Array extends ArrayFire implements AutoCloseable {

  public enum Type {
      Float(0),
      FloatComplex(1),
      Double(2),
      DoubleComplex(3),
      Boolean(4),
      Int(5);

      private final int type;

      private Type(int type) {
          this.type = type;
      }

      public static Type fromInt(int type) throws Exception {
          switch (type) {
            case 0:
                return Type.Float;
            case 1:
                return Type.FloatComplex;
            case 2:
                return Type.Double;
            case 3:
                return Type.DoubleComplex;
            case 4:
                return Type.Boolean;
            case 5:
                return Type.Int;
            default:
                throw new Exception("Unknown type.");
          }
      }

      public int getType() {
          return type;
      }

      public Type f32() {
          return Type.Float;
      }

      public Type f64() {
          return Type.Double;
      }

      public Type int32() {
          return Type.Int;
      }

      @Override
      public String toString() {
          return this.name();
      }
  }

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

  public Array(int[] dims, float[] elems) throws Exception {
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

    set(createArrayFromFloat(adims, elems));
  }

  public Array(int[] dims, double[] elems) throws Exception {
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

    set(createArrayFromDouble(adims, elems));
  }

  public Array(int[] dims, int[] elems) throws Exception {
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

  public Array(int[] dims, DoubleComplex[] elems) throws Exception {
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

  public Array.Type type() throws Exception {
      return Array.Type.fromInt(getType(ref));
  }

  protected static int[] dim4(int[] dims) throws Exception {

    if (dims == null) {
      throw new Exception("Null dimensions object provided");
    } else if (dims.length > 4) {
      throw new Exception("ArrayFire supports up to 4 dimensions only");
    }

    int[] adims = new int[] { 1, 1, 1, 1 };
    System.arraycopy(dims, 0, adims, 0, dims.length);
    return adims;
  }

  protected void assertType(Array.Type ty) throws Exception {

    Type myType = type();

    if (myType != ty) {
      String str = "Type mismatch: ";
      str = str + "Requested " + ty;
      str = str + ". Found " + myType;
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
