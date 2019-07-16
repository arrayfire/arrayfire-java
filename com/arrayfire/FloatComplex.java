package com.arrayfire;

public class FloatComplex {
  private float real;
  private float imag;

  public float real() {
    return real;
  }

  public float imag() {
    return imag;
  }

  public void set(float re, float im) {
    real = re;
    imag = im;
  }

  public void setReal(float re) {
    real = re;
  }

  public void setImag(float im) {
    imag = im;
  }

  public FloatComplex(float re, float im) {
    set(re, im);
  }

  public FloatComplex() {
    set(0, 0);
  }

  public String toString() {
    String str = String.valueOf(real);

    if (imag < 0)
      str = str + " - ";
    else
      str = str + " + ";

    return str + String.valueOf(Math.abs(imag)) + "i";
  }
}
