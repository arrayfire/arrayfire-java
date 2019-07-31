package com.arrayfire;

import com.arrayfire.Index;

public class ArrayFire extends AFLibLoader {

  public static final Seq SPAN = Seq.span();

  /* ************* Algorithm ************* */

  /**
   * Sum all the elements in an Array, wraps
   * {@link http://arrayfire.org/docs/group__reduce__func__sum.htm }
   * 
   * @param a the array to be summed
   * @return the sum
   * @throws Exception
   * @see Array
   */
  public static double sumAll(Array a) throws Exception {
    return Algorithm.sumAll(a);
  }

  /**
   * Finds the maximum value in an array, wraps
   * {@link http://arrayfire.org/docs/group__reduce__func__max.htm}
   * 
   * @param a the input array
   * @return the maximum value
   * @throws Exception
   * @see Array
   */
  public static double maxAll(Array a) throws Exception {
    return Algorithm.maxAll(a);
  }

  /**
   * Finds the minimum value in an array, wraps
   * {@link http://arrayfire.org/docs/group__reduce__func__min.htm}.
   * 
   * @param a the input array
   * @return the minimum value
   * @throws Exception
   * @see Array
   */
  public static double minAll(Array a) throws Exception {
    return Algorithm.minAll(a);
  }

  /**
   * Finds the sum of an array across 1 dimension and stores the result in a 2nd array.
   * 
   * @param res the array in which to store the result
   * @param a   the input array
   * @param dim the dimension across which to find the sum
   * @throws Exception
   * @see Array
   */
  public static void sum(Array res, Array a, int dim) throws Exception {
    Algorithm.sum(res, a, dim);
  }

  /**
   * Finds the maximum values of an array across 1 dimension and stores the result in a 2nd array.
   * 
   * @param res the array in which to store the result.
   * @param a   the input array
   * @param dim the dimenstion across which to find the maximum values
   * @throws Exception
   * @see Array
   */
  public static void max(Array res, Array a, int dim) throws Exception {
    Algorithm.max(res, a, dim);
  }

  /**
   * Finds the minimum values in an array across 1 dimenstion and stores the result in a 2nd array.
   * 
   * @param res the array in which to store the result
   * @param a   the input array
   * @param dim the dimension across which to find the maximum values
   * @throws Exception
   * @see Array
   */
  public static void min(Array res, Array a, int dim) throws Exception {
    Algorithm.min(res, a, dim);
  }


  /**
   * Finds the sum of values in an array across all dimenstion and stores the result in a 2nd array.
   * 
   * @param res the array in which to store the result
   * @param a   the input array
   * @throws Exception
   * @see Array
   */
  public static void sum(Array res, Array a) throws Exception {
    Algorithm.sum(res, a);
  }

  /**
   * Finds the max values in an array across all dimenstion and stores the result in a 2nd array.
   * 
   * @param res the array in which to store the result
   * @param a   the input array
   * @throws Exception
   * @see Array
   */
  public static void max(Array res, Array a) throws Exception {
    Algorithm.max(res, a);
  }

  /**
   * Finds the minimum values in an array across all dimenstion and stores the result in a 2nd
   * array.
   * 
   * @param res the array in which to store the result
   * @param a   the input array
   * @throws Exception
   * @see Array
   */
  public static void min(Array res, Array a) throws Exception {
    Algorithm.min(res, a, 0);
  }

  /**
   * Finds the indices of all non-zero values in an input array, wraps
   * {@link http://arrayfire.org/docs/group__scan__func__where.htm}
   * 
   * @param in the input array
   * @return an array containing the indices of all non-zero values in the input array
   * @throws Exception
   */
  public static Array where(final Array in) throws Exception {
    return Algorithm.where(in);
  }

  /* ************* Arith ************* */

  /**
   * Performs element-wise addition between 2 arrays and stores the result in another array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__add.htm}
   * 
   * @param c the resulting array
   * @param a the lhs array
   * @param b the rhs array
   * @throws Exception
   */
  public static void add(Array c, Array a, Array b) throws Exception {
    Arith.add(c, a, b);
  }

  /**
   * Subtracts 2 arrays, storing the result in another array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__sub.htm}
   * 
   * @param c the resulting array
   * @param a the lhs array
   * @param b the rhs array
   * @throws Exception
   */
  public static void sub(Array c, Array a, Array b) throws Exception {
    Arith.sub(c, a, b);
  }

  /**
   * Performs element-wise multiplication between 2 arrays, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__mul.htm}
   * 
   * @param c the resulting array
   * @param a the lhs array
   * @param b the rhs array
   * @throws Exception
   */
  public static void mul(Array c, Array a, Array b) throws Exception {
    Arith.mul(c, a, b);
  }

  /**
   * Divides one array by another array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__div.htm}
   * 
   * @param c the resulting array
   * @param a the lhs array
   * @param b the rhs array
   * @throws Exception
   */
  public static void div(Array c, Array a, Array b) throws Exception {
    Arith.div(c, a, b);
  }

  /**
   * Checks if an array is less than or equal to another, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__le.htm}.
   * 
   * @param c the resulting array
   * @param a the lhs array
   * @param b the rhs array
   * @throws Exception
   */
  public static void le(Array c, Array a, Array b) throws Exception {
    Arith.le(c, a, b);
  }

  /**
   * Checks if an array is less than another, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__lt.htm}
   * 
   * @param c the resulting array
   * @param a the lhs array
   * @param b the rhs array
   * @throws Exception
   */
  public static void lt(Array c, Array a, Array b) throws Exception {
    Arith.lt(c, a, b);
  }

  /**
   * Checks if an array is greater than or equal to another, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__ge.htm}
   * 
   * @param c the resulting array
   * @param a the lhs array
   * @param b the rhs array
   * @throws Exception
   */
  public static void ge(Array c, Array a, Array b) throws Exception {
    Arith.ge(c, a, b);
  }

  /**
   * Checks if an array is greater than another, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__gt.htm}
   * 
   * @param c the resulting array
   * @param a the lhs array
   * @param b the rhs array
   * @throws Exception
   */
  public static void gt(Array c, Array a, Array b) throws Exception {
    Arith.gt(c, a, b);
  }

  /**
   * Checks if 2 input arrays are equal, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__eq.htm}
   * 
   * @param c the resulting array
   * @param a the lhs array
   * @param b the rhs array
   * @throws Exception
   */
  public static void eq(Array c, Array a, Array b) throws Exception {
    Arith.eq(c, a, b);
  }

  /**
   * Checks if 2 input arrays are not equal, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__neq.htm}
   * 
   * @param c the resulting array
   * @param a the lhs array
   * @param b the rhs array
   * @throws Exception
   */
  public static void neq(Array c, Array a, Array b) throws Exception {
    Arith.neq(c, a, b);
  }

  // Unary operations

  /**
   * Finds the sin of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__sin.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void sin(Array res, Array a) throws Exception {
    Arith.sin(res, a);
  }

  /**
   * Finds the cosine of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__cos.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void cos(Array res, Array a) throws Exception {
    Arith.cos(res, a);
  }

  /**
   * Finds the tan of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__tan.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void tan(Array res, Array a) throws Exception {
    Arith.tan(res, a);
  }

  /**
   * Finds the asin of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__asin.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void asin(Array res, Array a) throws Exception {
    Arith.asin(res, a);
  }

  /**
   * Finds the acos of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__acos.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void acos(Array res, Array a) throws Exception {
    Arith.acos(res, a);
  }

  /**
   * Finds the atan of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__atan.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void atan(Array res, Array a) throws Exception {
    Arith.atan(res, a);
  }

  /**
   * Finds the sinh of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__sinh.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void sinh(Array res, Array a) throws Exception {
    Arith.sinh(res, a);
  }

  /**
   * Finds the cosh of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__cosh.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void cosh(Array res, Array a) throws Exception {
    Arith.cosh(res, a);
  }

  /**
   * Finds the tanh of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__tanh.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void tanh(Array res, Array a) throws Exception {
    Arith.tanh(res, a);
  }

  /**
   * Finds the asinh of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__asinh.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void asinh(Array res, Array a) throws Exception {
    Arith.asinh(res, a);
  }

  /**
   * Finds the acosh of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__acosh.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void acosh(Array res, Array a) throws Exception {
    Arith.acosh(res, a);
  }

  /**
   * Finds the atanh of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__atanh.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void atanh(Array res, Array a) throws Exception {
    Arith.atanh(res, a);
  }

  /**
   * Finds the exponential of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__exp.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void exp(Array res, Array a) throws Exception {
    Arith.exp(res, a);
  }

  /**
   * Finds the log of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__log.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void log(Array res, Array a) throws Exception {
    Arith.log(res, a);
  }

  /**
   * Finds the absolute value of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__abs.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void abs(Array res, Array a) throws Exception {
    Arith.abs(res, a);
  }

  /**
   * Finds the square root of the elements in an array, wraps
   * {@link http://arrayfire.org/docs/group__arith__func__sqrt.htm}
   * 
   * @param res the resulting array
   * @param a   the input array
   * @throws Exception
   */
  public static void sqrt(Array res, Array a) throws Exception {
    Arith.sqrt(res, a);
  }

  // Scalar operations
  /**
   * Adds a broadcasted value to an array.
   * 
   * @param c the resulting array
   * @param a the input array
   * @param b the value to broadcast
   * @throws Exception
   */
  public static void add(Array c, Array a, float b) throws Exception {
    Arith.add(c, a, b);
  }

  /**
   * Subtracts a broadcasted value from an array.
   * 
   * @param c the resulting array
   * @param a the input array
   * @param b the value to broadcast
   * @throws Exception
   */
  public static void sub(Array c, Array a, float b) throws Exception {
    Arith.sub(c, a, b);
  }

  /**
   * Performs element-wise multiplication between an array and a broadcasted value.
   * 
   * @param c the resulting array
   * @param a the input array
   * @param b the value to broadcast
   * @throws Exception
   */
  public static void mul(Array c, Array a, float b) throws Exception {
    Arith.mul(c, a, b);
  }

  /**
   * Divides an array by a broadcasted value.
   * 
   * @param c the resulting array
   * @param a the input array
   * @param b the value to broadcast
   * @throws Exception
   */
  public static void div(Array c, Array a, float b) throws Exception {
    Arith.div(c, a, b);
  }

  /**
   * Finds if an array is less than or equal to a broadcasted value.
   * 
   * @param c the resulting array
   * @param a the input array
   * @param b the value
   * @throws Exception
   */
  public static void le(Array c, Array a, float b) throws Exception {
    Arith.le(c, a, b);
  }

  /**
   * Finds if an array is less a broadcasted value.
   * 
   * @param c the resulting array
   * @param a the input array
   * @param b the value
   * @throws Exception
   */
  public static void lt(Array c, Array a, float b) throws Exception {
    Arith.lt(c, a, b);
  }

  /**
   * Finds if an array is greater than or equal to a broadcasted value.
   * 
   * @param c the resulting array
   * @param a the input array
   * @param b the value
   * @throws Exception
   */
  public static void ge(Array c, Array a, float b) throws Exception {
    Arith.ge(c, a, b);
  }

  /**
   * Finds if an array is greater a broadcasted value.
   * 
   * @param c the resulting array
   * @param a the input array
   * @param b the value
   * @throws Exception
   */
  public static void gt(Array c, Array a, float b) throws Exception {
    Arith.gt(c, a, b);
  }

  /**
   * Finds if an array is equal to a broadcasted value.
   * 
   * @param c the resulting array
   * @param a the input array
   * @param b the value
   * @throws Exception
   */
  public static void eq(Array c, Array a, float b) throws Exception {
    Arith.eq(c, a, b);
  }

  /**
   * Finds if an array is not equal to a broadcasted value.
   * 
   * @param c the resulting array
   * @param a the input array
   * @param b the value
   * @throws Exception
   */
  public static void neq(Array c, Array a, float b) throws Exception {
    Arith.neq(c, a, b);
  }

  /**
   * Raises an array to a power.
   * 
   * @param c the resulting array
   * @param a the input array
   * @param b the power to raise the array to
   * @throws Exception
   */
  public static void pow(Array c, Array a, float b) throws Exception {
    Arith.pow(c, a, b);
  }

  /**
   * Adds an array to a broadcasted value.
   * 
   * @param c the resulting array
   * @param a the value to which the array is added
   * @param b the input array
   * @throws Exception
   */
  public static void add(Array c, float a, Array b) throws Exception {
    Arith.add(c, a, b);
  }

  /**
   * Subtracts an array from a broadcasted value.
   * 
   * @param c the resulting array
   * @param a the value from which the array is subtracted
   * @param b the input array
   * @throws Exception
   */
  public static void sub(Array c, float a, Array b) throws Exception {
    Arith.sub(c, a, b);
  }

  /**
   * Performs element-wise multiplication between a broadcasted value and an array
   * 
   * @param c the resulting array
   * @param a the input value
   * @param b the input array
   * @throws Exception
   */
  public static void mul(Array c, float a, Array b) throws Exception {
    Arith.mul(c, a, b);
  }

  /**
   * Divides an array of a broadcasted value by an another array
   * 
   * @param c the resulting array
   * @param a the input value
   * @param b the input array
   * @throws Exception
   */
  public static void div(Array c, float a, Array b) throws Exception {
    Arith.div(c, a, b);
  }

  /**
   * Finds if a value is less than or equal to the values in an array
   * 
   * @param c the resulting array
   * @param a the input value
   * @param b the input array
   * @throws Exception
   */
  public static void le(Array c, float a, Array b) throws Exception {
    Arith.le(c, a, b);
  }

  /**
   * Finds if a value is less than to the values in an array
   * 
   * @param c the resulting array
   * @param a the input value
   * @param b the input array
   * @throws Exception
   */
  public static void lt(Array c, float a, Array b) throws Exception {
    Arith.lt(c, a, b);
  }

  /**
   * Finds if a value is greater than or equal to the values in an array
   * 
   * @param c the resulting array
   * @param a the input value
   * @param b the input array
   * @throws Exception
   */
  public static void ge(Array c, float a, Array b) throws Exception {
    Arith.ge(c, a, b);
  }

  /**
   * Finds if a value is greater than the values in an array
   * 
   * @param c the resulting array
   * @param a the input value
   * @param b the input array
   * @throws Exception
   */
  public static void gt(Array c, float a, Array b) throws Exception {
    Arith.gt(c, a, b);
  }

  /* ************* Data ************* */

  /**
   * Gets a floating-point array from the underlining Array data.
   * 
   * @param A the input array
   * @return a java array containng the input's data
   * @throws Exception
   */
  public static float[] getFloatArray(Array A) throws Exception {
    return Data.getFloatArray(A);
  }

  /**
   * Gets a full precision floating-point array from the underlining Array data.
   * 
   * @param A the input array
   * @return a java array containng the input's data
   * @throws Exception
   */
  public static double[] getDoubleArray(Array A) throws Exception {
    return Data.getDoubleArray(A);
  }

  /**
   * Gets a FloatComplex array from the underlining Array data.
   * 
   * @param A the input array
   * @return a java array containng the input's data
   * @throws Exception
   */
  public static FloatComplex[] getFloatComplexArray(Array A) throws Exception {
    return Data.getFloatComplexArray(A);
  }

  /**
   * Gets a DoubleComplex array from the underlining Array data.
   * 
   * @param A the input array
   * @return a java array containng the input's data
   * @throws Exception
   */
  public static DoubleComplex[] getDoubleComplexArray(Array A) throws Exception {
    return Data.getDoubleComplexArray(A);
  }

  /**
   * Gets an int array from the underlining Array data.
   * 
   * @param A the input array
   * @return a java array containng the input's data
   * @throws Exception
   */
  public static int[] getIntArray(Array A) throws Exception {
    return Data.getIntArray(A);
  }

  /**
   * Gets a boolean array from the underlining Array data.
   * 
   * @param A the input array
   * @return a java array containng the input's data
   * @throws Exception
   */
  public static boolean[] getBooleanArray(Array A) throws Exception {
    return Data.getBooleanArray(A);
  }

  // Binary operations

  /**
   * Create a random array sampled from uniform distribution, wraps
   * {@link http://arrayfire.org/docs/group__random__func__randu.htm}
   * 
   * @param res  the resulting array to populate
   * @param dims the array dimenstions
   * @param type the type of the array
   * @throws Exception
   */
  public static void randu(Array res, int[] dims, Type type) throws Exception {
    Data.randu(res, dims, type);
  }

  /**
   * Create a random array sampled from normal distribution, wraps
   * {@link http://arrayfire.org/docs/group__random__func__randn.htm}
   * 
   * @param res  the resulting array to populate
   * @param dims the array dimenstions
   * @param type the type of the array
   * @throws Exception
   */
  public static void randn(Array res, int[] dims, Type type) throws Exception {
    Data.randn(res, dims, type);
  }

  /**
   * Create an array using a scalar input value, wraps
   * {@link http://arrayfire.org/docs/group__data__func__constant.htm}
   * 
   * @param res  the resulting array to populate
   * @param val  the scalar value used to populate the array
   * @param dims the array dimenstions
   * @param type the type of the array
   * @throws Exception
   */
  public static void constant(Array res, double val, int[] dims, Type type) throws Exception {
    Data.constant(res, val, dims, type);
  }

  /**
   * Create an identity array, wraps
   * {@link http://arrayfire.org/docs/group__data__func__identity.htm}
   * 
   * @param res  the resulting array to populate
   * @param dims the array dimenstions
   * @param type the type of the array
   * @throws Exception
   */
  public static void identity(Array res, int[] dims, Type type) throws Exception {
    Data.identity(res, dims, type);
  }

  /**
   * Create a floating-point identity array, wraps
   * {@link http://arrayfire.org/docs/group__data__func__identity.htm}
   * 
   * @param res  the resulting array to populate
   * @param dims the array dimenstions
   * @throws Exception
   */
  public static void identity(Array res, int[] dims) throws Exception {
    Data.identity(res, dims);
  }

  /**
   * Creates an array with [0, n] values along the seqDim which is tiled across other dimensions.
   * {@link http://arrayfire.org/docs/group__data__func__range.htm}
   * 
   * @param dims   the array dimensions
   * @param seqDim the dimension along which [0, dim[seq_dim] - 1] is generated
   * @param type   the type of the array
   * @return the created array
   */
  public static Array range(int[] dims, int seqDim, Type type) {
    return Data.range(dims, seqDim, type);
  }

  /**
   * Creates an array with [0, n] floating-point values along the seqDim which is tiled across other
   * dimensions. {@link http://arrayfire.org/docs/group__data__func__range.htm}
   * 
   * @param dims   the array dimensions
   * @param seqDim the dimension along which [0, dim[seq_dim] - 1] is generated
   * @return the created array
   */
  public static Array range(int[] dims, int seqDim) {
    return Data.range(dims, seqDim, Type.Float);
  }

  /**
   * Creates an array with [0, n] floating-point values along the seqDim which is tiled across other
   * dimensions. {@link http://arrayfire.org/docs/group__data__func__range.htm}
   * 
   * @param dims   the array dimensions
   * @param seqDim the dimension along which [0, dim[seq_dim] - 1] is generated
   * @return the created array
   */
  public static Array range(int[] dims) {
    return Data.range(dims, -1, Type.Float);
  }

  /**
   * Creates an array with [0, n] floating-point values along the seqDim which is tiled across other
   * dimensions. {@link http://arrayfire.org/docs/group__data__func__range.htm}
   * 
   * @param dim    the size of the first dimension
   * @param seqDim the dimension along which [0, dim[seq_dim] - 1] is generated
   * @return the created array
   */
  public static Array range(int dim) {
    return Data.range(new int[] {dim}, -1, Type.Float);
  }

  /* ************* Image ************* */

  /**
   * Perform erosion (a morphological operation) on an image,
   * {@link http://arrayfire.org/docs/group__image__func__erode.htm}
   * 
   * @param res the eroded image
   * @param a   the input image
   * @param b   the neighborhood window
   * @throws Exception
   */
  public static void erode(Array res, Array a, Array b) throws Exception {
    Image.erode(res, a, b);
  }

  /**
   * Perform dilation (a morphological operation) on an image,
   * {@link http://arrayfire.org/docs/group__image__func__dilate.htm}
   * 
   * @param res the dilated image
   * @param a   the input image
   * @param b   the neighborhood window
   * @throws Exception
   */
  public static void dilate(Array res, Array a, Array b) throws Exception {
    Image.dilate(res, a, b);
  }

  /**
   * Finds the median filter of an image, wraps
   * {@link http://arrayfire.org/docs/group__image__func__medfilt.htm}
   * 
   * @param res    the processed image
   * @param a      the input image
   * @param width  the kernel width
   * @param height the kernel height
   * @throws Exception
   */
  public static void medianfilter(Array res, Array a, int width, int height) throws Exception {
    Image.medianfilter(res, a, width, height);
  }

  /**
   * Applies a bilateral filter to an image, wraps
   * {@link http://arrayfire.org/docs/group__image__func__bilateral.htm}
   * 
   * @param res   the processed image
   * @param a     the input image
   * @param space the spacial variance parameter
   * @param color the chromatic variance parameter
   * @throws Exception
   */
  public static void bilateral(Array res, Array a, float space, float color) throws Exception {
    Image.bilateral(res, a, space, color);
  }

  /**
   * Applies a meanshift filter to an image, wraps
   * {@link http://arrayfire.org/docs/group__image__func__mean__shift.htm}
   * 
   * @param res         the processed image
   * @param a           the input image
   * @param space       the spacial variance parameter
   * @param color       the chromatic variance parameter
   * @param interations the number of iterations
   * @throws Exception
   */
  public static void meanshift(Array res, Array a, float space, float color, int iterations)
      throws Exception {
    Image.meanshift(res, a, space, color, iterations);
  }

  /**
   * Create a histogram of some give data, wraps
   * {@link http://arrayfire.org/docs/group__image__func__histogram.htm}
   * 
   * @param res   the created array representing the histogram
   * @param a     the input data
   * @param nbins the number of bins to populate
   * @throws Exception
   */
  public static void histogram(Array res, Array a, int nbins) throws Exception {
    Image.histogram(res, a, nbins);
  }

  /**
   * Create a histogram of some give data, wraps
   * {@link http://arrayfire.org/docs/group__image__func__histogram.htm}
   * 
   * @param res   the created array representing the histogram
   * @param a     the input data
   * @param nbins the number of bins to populate between min and max
   * @param min   the minimum bin value
   * @param max   the maximum bin value
   * @throws Exception
   */
  public static void histogram(Array res, Array a, int nbins, float min, float max)
      throws Exception {
    Image.histogram(res, a, nbins, min, max);
  }

  /**
   * Rotate an input image, wraps
   * {@link http://arrayfire.org/docs/group__transform__func__rotate.htm}
   * 
   * @param res   the rotated image
   * @param a     the input image
   * @param theta the angle by which to rotate
   * @param crop  whether to crop against the original dimensions, otherwise scale according to
   *              theta
   * @throws Exception
   */
  public static void rotate(Array res, Array a, float theta, boolean crop) throws Exception {
    Image.rotate(res, a, theta, crop);
  }

  /**
   * Rotate an input image, wraps
   * {@link http://arrayfire.org/docs/group__transform__func__rotate.htm}
   * 
   * @param res    the rotated image
   * @param a      the input image
   * @param theta  the angle by which to rotate
   * @param crop   whether to crop against the original dimensions, otherwise scale according to
   *               theta
   * @param method the interpolation type
   * @throws Exception
   */
  public static void rotate(Array res, Array a, float theta, boolean crop, int method)
      throws Exception {
    Image.rotate(res, a, theta, crop, method);
  }

  /**
   * Resize an input image, wraps
   * {@link http://arrayfire.org/docs/group__transform__func__resize.htm}
   * 
   * @param res   the resized image
   * @param a     the input image
   * @param scale the scale to resize by
   * @throws Exception
   */
  public static void resize(Array res, Array a, float scale) throws Exception {
    Image.resize(res, a, scale);
  }

  /**
   * Resize an input image, wraps
   * {@link http://arrayfire.org/docs/group__transform__func__resize.htm}
   * 
   * @param res    the resized image
   * @param a      the input image
   * @param scale  the scale to resize by
   * @param method the interpolation type
   * @throws Exception
   */
  public static void resize(Array res, Array a, float scale, int method) throws Exception {
    Image.resize(res, a, scale, method);
  }

  /**
   * Resize an input image, wraps
   * {@link http://arrayfire.org/docs/group__transform__func__resize.htm}
   * 
   * @param res    the resized image
   * @param a      the input image
   * @param scalex the scale to resize by for the 1st dimension
   * @param scaley the scale to resize by for the 2nd dimension
   * @throws Exception
   */
  public static void resize(Array res, Array a, float scalex, float scaley) throws Exception {
    Image.resize(res, a, scalex, scaley);
  }

  /**
   * Resize an input image, wraps
   * {@link http://arrayfire.org/docs/group__transform__func__resize.htm}
   * 
   * @param res    the resized image
   * @param a      the input image
   * @param scalex the scale to resize by for the 1st dimension
   * @param scaley the scale to resize by for the 2nd dimension
   * @param method the interpolation type
   * @throws Exception
   */
  public static void resize(Array res, Array a, float scalex, float scaley, int method)
      throws Exception {
    Image.resize(res, a, scalex, scaley, method);
  }

  /**
   * Resize an input image, wraps
   * {@link http://arrayfire.org/docs/group__transform__func__resize.htm}
   * 
   * @param res    the resized image
   * @param a      the input image
   * @param height the height of the image
   * @param width  the width of the image
   * @throws Exception
   */
  public static void resize(Array res, Array a, int height, int width) throws Exception {
    Image.resize(res, a, height, width);
  }

  /**
   * Resize an input image, wraps
   * {@link http://arrayfire.org/docs/group__transform__func__resize.htm}
   * 
   * @param res    the resized image
   * @param a      the input image
   * @param height the height of the image
   * @param width  the width of the image
   * @param method the interpolation type
   * @throws Exception
   */
  public static void resize(Array res, Array a, int height, int width, int method)
      throws Exception {
    Image.resize(res, a, height, width, method);
  }

  /* ************* Signal ************* */

  /**
   * Compute the fast fourier transform of an array across 1 dimension, wraps
   * {@link http://arrayfire.org/docs/group__signal__func__fft.htm}
   * @param res   the fft
   * @param a     the input array
   * @throws Exception
   */
  public static void fft(Array res, Array a) throws Exception {
    Signal.fft(res, a);
  }

  /**
   * Compute the fast fourier transform of an array, wraps
   * {@link http://arrayfire.org/docs/group__signal__func__fft.htm}
   * @param res   the fft
   * @param a     the input array
   * @param dim0  the size of the first dimension
   * @throws Exception
   */
  public static void fft(Array res, Array a, int dim0) throws Exception {
    Signal.fft(res, a, dim0);
  }

  /**
   * Compute the fast fourier transform of an array across 2 dimensions, wraps
   * {@link http://arrayfire.org/docs/group__signal__func__fft.htm}
   * @param res   the fft
   * @param a     the input array
   * @throws Exception
   */
  public static void fft2(Array res, Array a) throws Exception {
    Signal.fft2(res, a);
  }

  /**
   * Compute the fast fourier transform of an array, wraps
   * {@link http://arrayfire.org/docs/group__signal__func__fft.htm}
   * @param res   the fft
   * @param a     the input array
   * @param dim0  the size of the first dimension
   * @param dim1  the size of the second dimension
   * @throws Exception
   */
  public static void fft2(Array res, Array a, int dim0, int dim1) throws Exception {
    Signal.fft2(res, a, dim0, dim1);
  }

  /**
   * Compute the fast fourier transform of an array across 3 dimensions, wraps
   * {@link http://arrayfire.org/docs/group__signal__func__fft.htm}
   * @param res   the fft
   * @param a     the input array
   * @throws Exception
   */
  public static void fft3(Array res, Array a) throws Exception {
    Signal.fft3(res, a);
  }

  /**
   * Compute the fast fourier transform of an array, wraps
   * {@link http://arrayfire.org/docs/group__signal__func__fft.htm}
   * @param res   the fft
   * @param a     the input array
   * @param dim0  the size of the first dimension
   * @param dim1  the size of the second dimension
   * @param dim2  the size of the third dimension
   * @throws Exception
   */
  public static void fft3(Array res, Array a, int dim0, int dim1, int dim2) throws Exception {
    Signal.fft3(res, a, dim0, dim1, dim2);
  }

  /**
   * Compute the the inverse fast fourier transform of an array across 1 dimension, wraps
   * {@link http://arrayfire.org/docs/group__signal__func__fft.htm}
   * @param res   the fft
   * @param a     the input array
   * @throws Exception
   */
  public static void ifft(Array res, Array a) throws Exception {
    Signal.ifft(res, a);
  }

  /**
   * Compute the inverse fast fourier transform of an array, wraps
   * {@link http://arrayfire.org/docs/group__signal__func__fft.htm}
   * @param res   the fft
   * @param a     the input array
   * @param dim0  the size of the first dimension
   * @throws Exception
   */
  public static void ifft(Array res, Array a, int dim0) throws Exception {
    Signal.ifft(res, a, dim0);
  }

  /**
   * Compute the the inverse fast fourier transform of an array across 2 dimensions, wraps
   * {@link http://arrayfire.org/docs/group__signal__func__fft.htm}
   * @param res   the fft
   * @param a     the input array
   * @throws Exception
   */
  public static void ifft2(Array res, Array a) throws Exception {
    Signal.ifft2(res, a);
  }

  /**
   * Compute the inverse fast fourier transform of an array, wraps
   * {@link http://arrayfire.org/docs/group__signal__func__fft.htm}
   * @param res   the fft
   * @param a     the input array
   * @param dim0  the size of the first dimension
   * @param dim1  the size of the second dimension
   * @throws Exception
   */
  public static void ifft2(Array res, Array a, int dim0, int dim1) throws Exception {
    Signal.ifft2(res, a, dim0, dim1);
  }

  /**
   * Compute the the inverse fast fourier transform of an array across 3 dimensions, wraps
   * {@link http://arrayfire.org/docs/group__signal__func__fft.htm}
   * @param res   the fft
   * @param a     the input array
   * @throws Exception
   */
  public static void ifft3(Array res, Array a) throws Exception {
    Signal.ifft3(res, a);
  }

  /**
   * Compute the inverse fast fourier transform of an array, wraps
   * {@link http://arrayfire.org/docs/group__signal__func__fft.htm}
   * @param res   the fft
   * @param a     the input array
   * @param dim0  the size of the first dimension
   * @param dim1  the size of the second dimension
   * @param dim2  the size of the third dimension
   * @throws Exception
   */
  public static void ifft3(Array res, Array a, int dim0, int dim1, int dim2) throws Exception {
    Signal.ifft3(res, a, dim0, dim1, dim2);
  }

  /**
   * Find the convolutional intergral for 1 dimensional data, wraps 
   * {@link http://arrayfire.org/docs/group__signal__func__convolve.htm}
   * @param res the convolved array
   * @param a   the signal
   * @param b   the filter
   * @throws Exception
   */
  public static void convolve1(Array res, Array a, Array b) throws Exception {
    Signal.convolve1(res, a, b);
  }

  /**
   * Find the convolutional intergral for 2 dimensional data, wraps 
   * {@link http://arrayfire.org/docs/group__signal__func__convolve.htm}
   * @param res the convolved array
   * @param a   the signal
   * @param b   the filter
   * @throws Exception
   */
  public static void convolve2(Array res, Array a, Array b) throws Exception {
    Signal.convolve2(res, a, b);
  }

  /**
   * Find the convolutional intergral for 3 dimensional data, wraps 
   * {@link http://arrayfire.org/docs/group__signal__func__convolve.htm}
   * @param res the convolved array
   * @param a   the signal
   * @param b   the filter
   * @throws Exception
   */
  public static void convolve3(Array res, Array a, Array b) throws Exception {
    Signal.convolve3(res, a, b);
  }

  /* ************* Statistics ************* */

  /**
   * Find the mean of the values in th input across 1 dimension, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__mean.htm}
   * @param in  the input array
   * @param dim the dimension
   * @return    the array containing the mean
   */
  public static Array mean(final Array in, int dim) {
    return Statistics.mean(in, dim);
  }

  /**
   * Find the weighted mean of the values in th input across 1 dimension, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__mean.htm}
   * @param in      the input array
   * @param dim     the dimension
   * @param weights the weights of the input
   * @return        the array containing the mean
   */
  public static Array mean(final Array in, final Array weights, int dim) {
    return Statistics.mean(in, weights, dim);
  }

  /**
   * Find the mean of all the values in the input, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__mean.htm}
   * @param in    the input array
   * @param type  the type of the input
   * @return      the array containing the mean
   */
  public static <T> T mean(final Array in, Class<T> type) throws Exception {
    return Statistics.mean(in, type);
  }

  /**
   * Find the weighted mean of all the values in the input, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__mean.htm}
   * @param in      the input array
   * @param weights the weights of the input
   * @param type    the type of the input
   * @return        the array containing the mean
   */
  public static <T> T mean(final Array in, final Array weights, Class<T> type) throws Exception {
    return Statistics.mean(in, weights, type);
  }

  /**
   * Find the variance of the values in an input across 1 dimension, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__var.htm}
   * @param in        the input array
   * @param isBiased  whether to use sample variance
   * @param dim       the dimension across which to find the variance
   * @return          the resulting array
   */
  public static Array var(final Array in, boolean isBiased, int dim) {
    return Statistics.var(in, isBiased, dim);
  }

  /**
   * Find the weighted variance of the values in an input across 1 dimension, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__var.htm}
   * @param in        the input array
   * @param weights   weights of the input
   * @param dim       the dimension across which to find the variance
   * @return          the resulting array
   */
  public static Array var(final Array in, final Array weights, int dim) {
    return Statistics.var(in, weights, dim);
  }

  /**
   * Find the variance of the values in an input across all dimensions, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__var.htm}
   * @param in        the input array
   * @param isBiased  whether to use sample variance
   * @param type      the type of the input array
   * @return          the variance across all dimensions
   */
  public static <T> T var(final Array in, boolean isBiased, Class<T> type) throws Exception {
    return Statistics.var(in, isBiased, type);
  }

  /**
   * Find the weighted variance of the values in an input across all dimensions, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__var.htm}
   * @param in        the input array
   * @param weights   the weights of th input
   * @param type      the type of the input array
   * @return          the variance across all dimensions
   */
  public static <T> T var(final Array in, final Array weights, Class<T> type) throws Exception {
    return Statistics.var(in, weights, type);
  }

  /**
   * Find the standard deviation of an input across 1 dimension, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__stdev.htm}
   * @param in    the input array
   * @param dim   the dimension across which to find the standard deviation
   * @return      the resulting array containing the standard deviations
   */
  public static Array stdev(final Array in, int dim) {
    return Statistics.stdev(in, dim);
  }

  /**
   * Find the standard deviation of an input across all dimensions, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__stdev.htm}
   * @param in    the input array
   * @param type  the type of the input array
   * @return      the total standard deviation
   */
  public static <T> T stdev(final Array in, Class<T> type) throws Exception {
    return Statistics.stdev(in, type);
  }

  /**
   * Find the median of values in an input across 1 dimension, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__median.htm}
   * @param in    the input array
   * @param dim   the dimension across which to find the median
   * @return      the resulting array containing the medians
   */
  public static Array median(final Array in, int dim) {
    return Statistics.median(in, dim);
  }

  /**
   * Find the median of values in an input across all dimensions, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__median.htm}
   * @param in    the input array
   * @param type  the type of the input array data
   * @return      the median across all dimensions
   */
  public static <T> T median(final Array in, Class<T> type) throws Exception {
    return Statistics.median(in, type);
  }

  /**
   * Find the covariance of values in an input, wraps 
   * {@link http://arrayfire.org/docs/group__stat__func__cov.htm}
   * @param x         the first input array
   * @param y         the second input array
   * @param isBiased  whether a biased estimate should be made
   * @return          array containing the covariances of the input
   */
  public static Array cov(Array x, Array y, boolean isBiased) {
    return Statistics.cov(x, y, isBiased);
  }

  /**
   * Find the correlation coefficient of values in an input, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__corrcoef.htm}
   * @param <T>   the type, should be a number
   * @param x     the first input array
   * @param y     the seconds input array
   * @param type  the type of data in the arrays
   * @return      the correlation coefficent of the two arrays
   * @throws Exception
   */
  public static <T extends Number> T corrcoef(final Array x, final Array y, Class<T> type)
      throws Exception {
    return Statistics.corrcoef(x, y, type);
  }

  /**
   * Find the top k values along a given dimension on an input array, wraps
   * {@link http://arrayfire.org/docs/group__stat__func__topk.htm}
   * @param in    the input array
   * @param k     the number of elements to be retrieved along the given dimension
   * @param dim   the dimension along which top k values are extracted
   * @param order the order by which to retrieve the values
   * @return      an array of array scontaining the top k values
   * @see         TopkOrder
   */
  public static Array[] topk(final Array in, int k, int dim, TopkOrder order) throws Exception {
    return Statistics.topk(in, k, dim, order);
  }

  static <T> T castResult(DoubleComplex res, Class<T> type) throws Exception {
    return Statistics.castResult(res, type);
  }

  /* ************* Index ************* */

  /**
   * Look up values in an array by indexing another array, wraps
   * {@link http://arrayfire.org/docs/group__index__func__lookup.htm}
   * @param in    the input array
   * @param idx   the indexing array
   * @param dim   the dimension used for indexing
   * @return      the array containing the indexed input array
   * @throws Exception
   */
  public static Array lookup(final Array in, final Array idx, int dim) throws Exception {
    return Index.lookup(in, idx, dim);
  }

  /**
   * Look up values in an array by indexing another array, wraps
   * {@link http://arrayfire.org/docs/group__index__func__lookup.htm}
   * @param in    the input array
   * @param idx   the indexing array
   * @param dim   the dimension used for indexing
   * @return      the array containing the indexed input array
   * @throws Exception
   */
  public static Array lookup(final Array in, final Array idx) throws Exception {
    return Index.lookup(in, idx, 0);
  }

  /**
   * Copy the values of an input array based on an index, wraps
   * {@link http://arrayfire.org/docs/group__index__func__index.htm}
   * @param dst   the array to copy into
   * @param src   the array to copy from
   * @param idx0  the first index
   * @param idx1  the second index
   * @param idx2  the third index
   * @param idx3  the fourth index
   * @throws Exception
   */
  public static void copy(Array dst, final Array src, Index idx0, Index idx1, Index idx2,
      Index idx3) throws Exception {
    Index.copy(dst, src, idx0, idx1, idx2, idx3);
  }

  /**
   * Copy the values of an input array based on an index, wraps
   * {@link http://arrayfire.org/docs/group__index__func__index.htm}
   * @param dst   the array to copy into
   * @param src   the array to copy from
   * @param idx0  the first index
   * @param idx1  the second index
   * @param idx2  the third index
   * @throws Exception
   */
  public static void copy(Array dst, final Array src, Index idx0, Index idx1, Index idx2)
      throws Exception {
    Index.copy(dst, src, idx0, idx1, idx2, new Index());
  }

  /**
   * Copy the values of an input array based on an index, wraps
   * {@link http://arrayfire.org/docs/group__index__func__index.htm}
   * @param dst   the array to copy into
   * @param src   the array to copy from
   * @param idx0  the first index
   * @param idx1  the second index
   * @throws Exception
   */
  public static void copy(Array dst, final Array src, Index idx0, Index idx1) throws Exception {
    Index.copy(dst, src, idx0, idx1, new Index(), new Index());
  }

  /**
   * Copy the values of an input array based on an index, wraps
   * {@link http://arrayfire.org/docs/group__index__func__index.htm}
   * @param dst   the array to copy into
   * @param src   the array to copy from
   * @param idx0  the first index
   * @throws Exception
   */
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
    Float(0), FloatComplex(1), Double(2), DoubleComplex(3), Boolean(4), Int(5);

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
    DEFAULT(0), SPECTRUM(1), COLORS(2), RED(3), MOOD(4), HEAT(5), BLUE(6), INFERNO(7), MAGMA(
        8), PLASMA(9), VIRIDIS(10);

    private final int map;

    private ColorMap(int map) {
      this.map = map;
    }

    public int getMap() {
      return map;
    }
  }

  public static enum MarkerType {
    NONE(0), POINT(1), CIRCLE(2), SQUARE(3), TRIANGLE(4), CROSS(5), PLUS(6), STAR(7);

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
