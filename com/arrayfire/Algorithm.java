package com.arrayfire;

class Algorithm extends ArrayFire {

  // Scalar return operations
  private native static double sumAll(long a);

  private native static double maxAll(long a);

  private native static double minAll(long a);

  private native static long sum(long a, int dim);

  private native static long max(long a, int dim);

  private native static long min(long a, int dim);

  // Scalar return operations
  public static double sumAll(Array a) throws Exception {
    return sumAll(a.ref);
  }

  public static double maxAll(Array a) throws Exception {
    return maxAll(a.ref);
  }

  public static double minAll(Array a) throws Exception {
    return minAll(a.ref);
  }

  public static void sum(Array res, Array a, int dim) throws Exception {
    long ref = sum(a.ref, dim);
    res.set(ref);
  }

  public static void max(Array res, Array a, int dim) throws Exception {
    long ref = max(a.ref, dim);
    res.set(ref);
  }

  public static void min(Array res, Array a, int dim) throws Exception {
    long ref = min(a.ref, dim);
    res.set(ref);
  }

  public static void sum(Array res, Array a) throws Exception {
    sum(res, a, 0);
  }

  public static void max(Array res, Array a) throws Exception {
    max(res, a, 0);
  }

  public static void min(Array res, Array a) throws Exception {
    min(res, a, 0);
  }
}
