package com.arrayfire;

public class DoubleComplex {
    private double real;
    private double imag;

    public double real() {
        return real;
    }

    public double imag() {
        return imag;
    }

    public void set(double re, double im) {
        real = re;
        imag = im;
    }

    public void setReal(double re) {
        real = re;
    }

    public void setImag(double im) {
        imag = im;
    }

    public DoubleComplex(double re, double im) {
        set(re, im);
    }

    public DoubleComplex() {
        set(0, 0);
    }

    public String toString() {
        String str = String.valueOf(real);

        if (imag < 0) str = str + " - ";
        else          str = str + " + ";

        return str + String.valueOf(Math.abs(imag)) + "i";
    }
}
