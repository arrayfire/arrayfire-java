package com.arrayfire;

import com.arrayfire.Index;

public class ArrayFire extends AFLibLoader {

  public static final Seq SPAN = Seq.span();
  /* ************* Algorithm ************* */


  /**
   * Sum all the elements in an Array, wraps {@link http://arrayfire.org/docs/group__reduce__func__sum.htm }
   * @param a the array to be summed
   * @return  the sum
   * @throws  Exception
   * @see     Array
   */
  public static double sumAll(Array a) throws Exception {
    return Algorithm.sumAll(a);
  }

  /**
   * Finds the maximum value in an array, wraps {@link http://arrayfire.org/docs/group__reduce__func__max.htm} 
   * @param a the input array
   * @return  the maximum value
   * @throws  Exception
   * @see     Array
   */
  public static double maxAll(Array a) throws Exception {
    return Algorithm.maxAll(a);
  }

  /**
   * Finds the minimum value in an array, wraps {@link http://arrayfire.org/docs/group__reduce__func__min.htm}.
   * @param a the input array
   * @return  the minimum value
   * @throws  Exception
   * @see     Array
   */
  public static double minAll(Array a) throws Exception {
    return Algorithm.minAll(a);
  }

  /**
   * Finds the sum of an array across 1 dimension and stores the result in a 2nd array.
   * @param res the array in which to store the result
   * @param a   the input array
   * @param dim the dimension across which to find the sum
   * @throws Exception
   * @see       Array
   */
  public static void sum(Array res, Array a, int dim) throws Exception {
    Algorithm.sum(res, a, dim);
  }

  /**
   * Finds the maximum values of an array across 1 dimension and stores the result in a 2nd array.
   * @param res   the array in which to store the result.
   * @param a     the input array
   * @param dim   the dimenstion across which to find the maximum values
   * @throws Exception
   * @see         Array
   */
  public static void max(Array res, Array a, int dim) throws Exception {
    Algorithm.max(res, a, dim);
  }

  /**
   * Finds the minimum values in an array across 1 dimenstion and stores the result in a 2nd array.
   * @param res   the array in which to store the result
   * @param a     the input array
   * @param dim   the dimension across which to find the maximum values
   * @throws Exception
   * @see         Array
   */
  public static void min(Array res, Array a, int dim) throws Exception {
    Algorithm.min(res, a, dim);
  }


  /**
   * Finds the sum of values in an array across all dimenstion and stores the result in a 2nd array.
   * @param res   the array in which to store the result
   * @param a     the input array
   * @throws Exception
   * @see         Array
   */
  public static void sum(Array res, Array a) throws Exception {
    Algorithm.sum(res, a);
  }

  /**
   * Finds the max values in an array across all dimenstion and stores the result in a 2nd array.
   * @param res   the array in which to store the result
   * @param a     the input array
   * @throws Exception
   * @see         Array
   */
  public static void max(Array res, Array a) throws Exception {
    Algorithm.max(res, a);
  }

  /**
   * Finds the minimum values in an array across all dimenstion and stores the result in a 2nd array.
   * @param res   the array in which to store the result
   * @param a     the input array
   * @throws Exception
   * @see         Array
   */
  public static void min(Array res, Array a) throws Exception {
    Algorithm.min(res, a, 0);
  }

  /**
   * Finds the indices of all non-zero values in an input array, wraps {@link http://arrayfire.org/docs/group__scan__func__where.htm}
   * @param in  the input array
   * @return    an array containing the indices of all non-zero values in the input array
   * @throws Exception
   */
  public static Array where(final Array in) throws Exception {
    return Algorithm.where(in);
  }

  /* ************* Arith ************* */

  /**
   * Performs element-wise addition between 2 arrays and stores the result in another array, wraps {@link http://arrayfire.org/docs/group__arith__func__add.htm}
   * @param c   the resulting array
   * @param a   the lhs array
   * @param b   the rhs array
   * @return    the resulting array
   * @throws Exception
   */
  public static void add(Array c, Array a, Array b) throws Exception {
    Arith.add(c, a, b);
  }

  /**
   * Subtracts 2 arrays, storing the result in another array, wraps {@link http://arrayfire.org/docs/group__arith__func__sub.htm}
   * @param c   the resulting array
   * @param a   the lhs array
   * @param b   the rhs array
   * @return    the resulting array
   * @throws Exception
   */
  public static void sub(Array c, Array a, Array b) throws Exception {
    Arith.sub(c, a, b);
  }

  /**
   * Performs element-wise multiplication between 2 arrays, wraps {@link http://arrayfire.org/docs/group__arith__func__mul.htm}
   * @param c   the resulting array
   * @param a   the lhs array
   * @param b   the rhs array
   * @return    the resulting array
   * @throws Exception
   */
  public static void mul(Array c, Array a, Array b) throws Exception {
    Arith.mul(c, a, b);
  }

  /**
   * Divides one array by another array, wraps {@link http://arrayfire.org/docs/group__arith__func__div.htm}
   * @param c   the resulting array
   * @param a   the lhs array
   * @param b   the rhs array
   * @return    the resulting array
   * @throws Exception
   */
  public static void div(Array c, Array a, Array b) throws Exception {
    Arith.div(c, a, b);
  }

  /**
   * Checks if an array is less than or equal to another, wraps {@link http://arrayfire.org/docs/group__arith__func__le.htm}.
   * @param c   the resulting array
   * @param a   the lhs array
   * @param b   the rhs array
   * @return    the resulting array
   * @throws Exception
   */
  public static void le(Array c, Array a, Array b) throws Exception {
    Arith.le(c, a, b);
  }

  /**
   * Checks if an array is less than another, wraps {@link http://arrayfire.org/docs/group__arith__func__lt.htm}
   * @param c   the resulting array
   * @param a   the lhs array
   * @param b   the rhs array
   * @return    the resulting array
   * @throws Exception
   */
  public static void lt(Array c, Array a, Array b) throws Exception {
    Arith.lt(c, a, b);
  }

  /**
   * Checks if an array is greater than or equal to another, wraps {@link http://arrayfire.org/docs/group__arith__func__ge.htm}
   * @param c   the resulting array
   * @param a   the lhs array
   * @param b   the rhs array
   * @return    the resulting array
   * @throws Exception
   */
  public static void ge(Array c, Array a, Array b) throws Exception {
    Arith.ge(c, a, b);
  }

  /**
   * Checks if an array is greater than another, wraps {@link http://arrayfire.org/docs/group__arith__func__gt.htm}
   * @param c   the resulting array
   * @param a   the lhs array
   * @param b   the rhs array
   * @return    the resulting array
   * @throws Exception
   */
  public static void gt(Array c, Array a, Array b) throws Exception {
    Arith.gt(c, a, b);
  }

  /**
   * Checks if 2 input arrays are equal, wraps {@link http://arrayfire.org/docs/group__arith__func__eq.htm}
   * @param c   the resulting array
   * @param a   the lhs array
   * @param b   the rhs array
   * @return    the resulting array
   * @throws Exception
   */
  public static void eq(Array c, Array a, Array b) throws Exception {
    Arith.eq(c, a, b);
  }

  /**
   * Checks if 2 input arrays are not equal, wraps {@link http://arrayfire.org/docs/group__arith__func__neq.htm}
   * @param c   the resulting array
   * @param a   the lhs array
   * @param b   the rhs array
   * @return    the resulting array
   * @throws Exception
   */
  public static void neq(Array c, Array a, Array b) throws Exception {
    Arith.neq(c, a, b);
  }

  // Unary operations
  public static void sin(Array res, Array a) throws Exception {
    Arith.sin(res, a);
  }

  public static void cos(Array res, Array a) throws Exception {
    Arith.cos(res, a);
  }

  public static void tan(Array res, Array a) throws Exception {
    Arith.tan(res, a);
  }

  public static void asin(Array res, Array a) throws Exception {
    Arith.asin(res, a);
  }

  public static void acos(Array res, Array a) throws Exception {
    Arith.acos(res, a);
  }

  public static void atan(Array res, Array a) throws Exception {
    Arith.atan(res, a);
  }

  public static void sinh(Array res, Array a) throws Exception {
    Arith.sinh(res, a);
  }

  public static void cosh(Array res, Array a) throws Exception {
    Arith.cosh(res, a);
  }

  public static void tanh(Array res, Array a) throws Exception {
    Arith.tanh(res, a);
  }


  public static void asinh(Array res, Array a) throws Exception {
    Arith.asinh(res, a);
  }

  public static void acosh(Array res, Array a) throws Exception {
    Arith.acosh(res, a);
  }

  public static void atanh(Array res, Array a) throws Exception {
    Arith.atanh(res, a);
  }

  public static void exp(Array res, Array a) throws Exception {
    Arith.exp(res, a);
  }

  public static void log(Array res, Array a) throws Exception {
    Arith.log(res, a);
  }

  public static void abs(Array res, Array a) throws Exception {
    Arith.abs(res, a);
  }

  public static void sqrt(Array res, Array a) throws Exception {
    Arith.sqrt(res, a);
  }

  // Scalar operations
  public static void add(Array c, Array a, float b) throws Exception {
    Arith.add(c, a, b);
  }

  public static void sub(Array c, Array a, float b) throws Exception {
    Arith.sub(c, a, b);
  }

  public static void mul(Array c, Array a, float b) throws Exception {
    Arith.mul(c, a, b);
  }

  public static void div(Array c, Array a, float b) throws Exception {
    Arith.div(c, a, b);
  }

  public static void le(Array c, Array a, float b) throws Exception {
    Arith.le(c, a, b);
  }

  public static void lt(Array c, Array a, float b) throws Exception {
    Arith.lt(c, a, b);
  }

  public static void ge(Array c, Array a, float b) throws Exception {
    Arith.ge(c, a, b);
  }

  public static void gt(Array c, Array a, float b) throws Exception {
    Arith.gt(c, a, b);
  }

  public static void eq(Array c, Array a, float b) throws Exception {
    Arith.eq(c, a, b);
  }

  public static void neq(Array c, Array a, float b) throws Exception {
    Arith.neq(c, a, b);
  }

  public static void pow(Array c, Array a, float b) throws Exception {
    Arith.pow(c, a, b);
  }

  public static void add(Array c, float a, Array b) throws Exception {
    Arith.add(c, a, b);
  }

  public static void sub(Array c, float a, Array b) throws Exception {
    Arith.sub(c, a, b);
  }

  public static void mul(Array c, float a, Array b) throws Exception {
    Arith.mul(c, a, b);
  }

  public static void div(Array c, float a, Array b) throws Exception {
    Arith.div(c, a, b);
  }

  public static void le(Array c, float a, Array b) throws Exception {
    Arith.le(c, a, b);
  }

  public static void lt(Array c, float a, Array b) throws Exception {
    Arith.lt(c, a, b);
  }

  public static void ge(Array c, float a, Array b) throws Exception {
    Arith.ge(c, a, b);
  }

  public static void gt(Array c, float a, Array b) throws Exception {
    Arith.gt(c, a, b);
  }


  /* ************* Data ************* */

  public static float[] getFloatArray(Array A) throws Exception {
    return Data.getFloatArray(A);
  }

  public static double[] getDoubleArray(Array A) throws Exception {
    return Data.getDoubleArray(A);
  }

  public static FloatComplex[] getFloatComplexArray(Array A) throws Exception {
    return Data.getFloatComplexArray(A);
  }

  public static DoubleComplex[] getDoubleComplexArray(Array A) throws Exception {
    return Data.getDoubleComplexArray(A);
  }

  public static int[] getIntArray(Array A) throws Exception {
    return Data.getIntArray(A);
  }

  public static boolean[] getBooleanArray(Array A) throws Exception {
    return Data.getBooleanArray(A);
  }

  // Binary operations
  public static void randu(Array res, int[] dims, Type type) throws Exception {
    Data.randu(res, dims, type);
  }

  public static void randn(Array res, int[] dims, Type type) throws Exception {
    Data.randn(res, dims, type);
  }

  public static void constant(Array res, double val, int[] dims, Type type) throws Exception {
    Data.constant(res, val, dims, type);
  }

  public static void identity(Array res, int[] dims, Type type) throws Exception {
    Data.identity(res, dims, type);
  }

  public static void identity(Array res, int[] dims) throws Exception {
    Data.identity(res, dims);
  }

  public static Array range(int[] dims, int seqDim, Type type) {
      return Data.range(dims, seqDim, type);
  }


  public static Array range(int[] dims, int seqDim) {
      return Data.range(dims, seqDim, Type.Float);
  }

  public static Array range(int[] dims) {
      return Data.range(dims, -1, Type.Float);
  }


  public static Array range(int dim) {
      return Data.range(new int[] {dim}, -1, Type.Float);
  }

  /* ************* Image ************* */

  public static void erode(Array res, Array a, Array b) throws Exception {
    Image.erode(res, a, b);
  }

  public static void dilate(Array res, Array a, Array b) throws Exception {
    Image.dilate(res, a, b);
  }

  public static void medianfilter(Array res, Array a, int width, int height) throws Exception {
    Image.medianfilter(res, a, width, height);
  }

  public static void bilateral(Array res, Array a, float space, float color) throws Exception {
    Image.bilateral(res, a, space, color);
  }

  public static void meanshift(Array res, Array a, float space, float color, int iterations)
      throws Exception {
    Image.meanshift(res, a, space, color, iterations);
  }

  public static void histogram(Array res, Array a, int nbins) throws Exception {
    Image.histogram(res, a, nbins);
  }

  public static void histogram(Array res, Array a, int nbins, float min, float max)
      throws Exception {
    Image.histogram(res, a, nbins, min, max);
  }

  public static void rotate(Array res, Array a, float theta, boolean crop) throws Exception {
    Image.rotate(res, a, theta, crop);
  }

  public static void rotate(Array res, Array a, float theta, boolean crop, int method)
      throws Exception {
    Image.rotate(res, a, theta, crop, method);
  }

  public static void resize(Array res, Array a, float scale) throws Exception {
    Image.resize(res, a, scale);
  }

  public static void resize(Array res, Array a, float scale, int method) throws Exception {
    Image.resize(res, a, scale, method);
  }

  public static void resize(Array res, Array a, float scalex, float scaley) throws Exception {
    Image.resize(res, a, scalex, scaley);
  }

  public static void resize(Array res, Array a, float scalex, float scaley, int method)
      throws Exception {
    Image.resize(res, a, scalex, scaley, method);
  }

  public static void resize(Array res, Array a, int height, int width) throws Exception {
    Image.resize(res, a, height, width);
  }

  public static void resize(Array res, Array a, int height, int width, int method)
      throws Exception {
    Image.resize(res, a, height, width, method);
  }

  /* ************* Signal ************* */

  public static void fft(Array res, Array a) throws Exception {
    Signal.fft(res, a);
  }

  public static void fft(Array res, Array a, int dim0) throws Exception {
    Signal.fft(res, a, dim0);
  }

  public static void fft2(Array res, Array a) throws Exception {
    Signal.fft2(res, a);
  }

  public static void fft2(Array res, Array a, int dim0, int dim1) throws Exception {
    Signal.fft2(res, a, dim0, dim1);
  }

  public static void fft3(Array res, Array a) throws Exception {
    Signal.fft3(res, a);
  }

  public static void fft3(Array res, Array a, int dim0, int dim1, int dim2) throws Exception {
    Signal.fft3(res, a, dim0, dim1, dim2);
  }

  public static void ifft(Array res, Array a) throws Exception {
    Signal.ifft(res, a);
  }

  public static void ifft(Array res, Array a, int dim0) throws Exception {
    Signal.ifft(res, a, dim0);
  }

  public static void ifft2(Array res, Array a) throws Exception {
    Signal.ifft2(res, a);
  }

  public static void ifft2(Array res, Array a, int dim0, int dim1) throws Exception {
    Signal.ifft2(res, a, dim0, dim1);
  }

  public static void ifft3(Array res, Array a) throws Exception {
    Signal.ifft3(res, a);
  }

  public static void ifft3(Array res, Array a, int dim0, int dim1, int dim2) throws Exception {
    Signal.ifft3(res, a, dim0, dim1, dim2);
  }

  public static void convolve1(Array res, Array a, Array b) throws Exception {
    Signal.convolve1(res, a, b);
  }

  public static void convolve2(Array res, Array a, Array b) throws Exception {
    Signal.convolve2(res, a, b);
  }

  public static void convolve3(Array res, Array a, Array b) throws Exception {
    Signal.convolve3(res, a, b);
  }

  /* ************* Statistics ************* */


  static public Array mean(final Array in, int dim) {
    return Statistics.mean(in, dim);
  }

  static public Array mean(final Array in, final Array weights, int dim) {
    return Statistics.mean(in, weights, dim);
  }

  static public <T> T mean(final Array in, Class<T> type) throws Exception {
    return Statistics.mean(in, type);
  }

  static public <T> T mean(final Array in, final Array weights, Class<T> type) throws Exception {
    return Statistics.mean(in, weights, type);
  }

  static public Array var(final Array in, boolean isBiased, int dim) {
    return Statistics.var(in, isBiased, dim);
  }

  static public Array var(final Array in, final Array weights, int dim) {
    return Statistics.var(in, weights, dim);
  }

  static public <T> T var(final Array in, boolean isBiased, Class<T> type) throws Exception {
    return Statistics.var(in, isBiased, type);
  }

  static public <T> T var(final Array in, final Array weights, Class<T> type) throws Exception {
    return Statistics.var(in, weights, type);
  }

  static public Array stdev(final Array in, int dim) {
    return Statistics.stdev(in, dim);
  }

  static public <T> T stdev(final Array in, Class<T> type) throws Exception {
    return Statistics.stdev(in, type);
  }

  static public Array median(final Array in, int dim) {
    return Statistics.median(in, dim);
  }

  static public <T> T median(final Array in, Class<T> type) throws Exception {
    return Statistics.median(in, type);
  }

  static public Array cov(Array x, Array y, boolean isBiased) {
    return Statistics.cov(x, y, isBiased);
  }

  static public <T extends Number> T corrcoef(final Array x, final Array y, Class<T> type)
      throws Exception {
    return Statistics.corrcoef(x, y, type);
  }

  static public Array[] topk(final Array in, int k, int dim, TopkOrder order) throws Exception {
    return Statistics.topk(in, k, dim, order);
  }

  static public <T> T castResult(DoubleComplex res, Class<T> type) throws Exception {
    return Statistics.castResult(res, type);
  }

  /* ************* Index ************* */


  public static Array lookup(final Array in, final Array idx, int dim) throws Exception {
      return Index.lookup(in, idx, dim);
  }

  public static Array lookup(final Array in, final Array idx) throws Exception {
      return Index.lookup(in, idx, 0);
  }

  public static void copy(Array dst, final Array src, Index idx0, Index idx1, Index idx2, Index idx3) throws Exception {
      Index.copy(dst, src, idx0, idx1, idx2, idx3);
  }

  public static void copy(Array dst, final Array src, Index idx0, Index idx1, Index idx2) throws Exception {
      Index.copy(dst, src, idx0, idx1, idx2, new Index());
  }

  public static void copy(Array dst, final Array src, Index idx0, Index idx1) throws Exception {
      Index.copy(dst, src, idx0, idx1, new Index(), new Index());
  }

  public static void copy(Array dst, final Array src, Index idx0) throws Exception {
      Index.copy(dst, src, idx0, new Index(), new Index(), new Index());
  }

  // Utils

  public static String toString(Array a, String delim) {
      return Util.toString(a, delim);
  }

  public static float[] toFloatArray(String text, String delim) {
      return Util.toFloatArray(text, delim);
  }

  public static void info() {
      Util.info();
  }

  // Enums

  public static enum Type {
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

      public static Type fromInt(int type) throws IllegalArgumentException {
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
                throw new IllegalArgumentException("Unknown type.");
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

    public static enum ColorMap {
        DEFAULT(0),
        SPECTRUM(1),
        COLORS(2),
        RED(3),
        MOOD(4),
        HEAT(5),
        BLUE(6),
        INFERNO(7),
        MAGMA(8),
        PLASMA(9),
        VIRIDIS(10);

        private final int map;

        private ColorMap(int map) {
            this.map = map;
        }

        public int getMap() {
            return map;
        }
    }

    public static enum MarkerType {
        NONE(0),
        POINT(1),
        CIRCLE(2),
        SQUARE(3),
        TRIANGLE(4),
        CROSS(5),
        PLUS(6),
        STAR(7);

        private final int type;

        private MarkerType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }

    public static enum TopkOrder {
        DEFAULT(0), MIN(1), MAX(2);

        private final int order;

        TopkOrder(int order) {
            this.order = order;
        }

        public int getOrder() {
            return order;
        }
    }
}
