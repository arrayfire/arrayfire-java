package com.arrayfire;

class Signal extends ArrayFire {

  private native static long fft(long a, int dim0);

  private native static long fft2(long a, int dim0, int dim1);

  private native static long fft3(long a, int dim0, int dim1, int dim2);

  private native static long ifft(long a, int dim0);

  private native static long ifft2(long a, int dim0, int dim1);

  private native static long ifft3(long a, int dim0, int dim1, int dim2);

  private native static long convolve1(long a, long b, int type);

  private native static long convolve2(long a, long b, int type);

  private native static long convolve3(long a, long b, int type);

  public static void fft(Array res, Array a) throws Exception {
    long ref = fft(a.ref, 0);
    res.set(ref);
  }

  public static void fft(Array res, Array a, int dim0) throws Exception {
    long ref = fft(a.ref, dim0);
    res.set(ref);
  }

  public static void fft2(Array res, Array a) throws Exception {
    long ref = fft2(a.ref, 0, 0);
    res.set(ref);
  }

  public static void fft2(Array res, Array a, int dim0, int dim1) throws Exception {
    long ref = fft2(a.ref, dim0, dim1);
    res.set(ref);
  }

  public static void fft3(Array res, Array a) throws Exception {
    long ref = fft3(a.ref, 0, 0, 0);
    res.set(ref);
  }

  public static void fft3(Array res, Array a, int dim0, int dim1, int dim2) throws Exception {
    long ref = fft3(a.ref, dim0, dim1, dim2);
    res.set(ref);
  }

  public static void ifft(Array res, Array a) throws Exception {
    long ref = ifft(a.ref, 0);
    res.set(ref);
  }

  public static void ifft(Array res, Array a, int dim0) throws Exception {
    long ref = ifft(a.ref, dim0);
    res.set(ref);
  }

  public static void ifft2(Array res, Array a) throws Exception {
    long ref = ifft2(a.ref, 0, 0);
    res.set(ref);
  }

  public static void ifft2(Array res, Array a, int dim0, int dim1) throws Exception {
    long ref = ifft2(a.ref, dim0, dim1);
    res.set(ref);
  }

  public static void ifft3(Array res, Array a) throws Exception {
    long ref = ifft3(a.ref, 0, 0, 0);
    res.set(ref);
  }

  public static void ifft3(Array res, Array a, int dim0, int dim1, int dim2) throws Exception {
    long ref = ifft3(a.ref, dim0, dim1, dim2);
    res.set(ref);
  }

  public static void convolve1(Array res, Array a, Array b) throws Exception {
    long ref = convolve1(a.ref, b.ref, 0);
    res.set(ref);
  }

  public static void convolve2(Array res, Array a, Array b) throws Exception {
    long ref = convolve2(a.ref, b.ref, 0);
    res.set(ref);
  }

  public static void convolve3(Array res, Array a, Array b) throws Exception {
    long ref = convolve3(a.ref, b.ref, 0);
    res.set(ref);
  }
}
