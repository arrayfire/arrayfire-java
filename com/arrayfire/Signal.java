package com.arrayfire;

public class Signal extends Array {

    private native static long fft  (long a);
    private native static long fft2 (long a);
    private native static long fft3 (long a);

    private native static long ifft (long a);
    private native static long ifft2(long a);
    private native static long ifft3(long a);

    private native static long convolve (long a, long b);

    public static void fft(Array res, Array a) throws Exception {
        long ref = fft(a.ref);
        res.set(ref);
    }

    public static void fft2(Array res, Array a) throws Exception {
        long ref = fft2(a.ref);
        res.set(ref);
    }

    public static void fft3(Array res, Array a) throws Exception {
        long ref = fft3(a.ref);
        res.set(ref);
    }

    public static void ifft(Array res, Array a) throws Exception {
        long ref = ifft(a.ref);
        res.set(ref);
    }

    public static void ifft2(Array res, Array a) throws Exception {
        long ref = ifft2(a.ref);
        res.set(ref);
    }

    public static void ifft3(Array res, Array a) throws Exception {
        long ref = ifft3(a.ref);
        res.set(ref);
    }

    public static void convolve(Array res, Array a, Array b) throws Exception {
        long ref = convolve(a.ref, b.ref);
        res.set(ref);
    }
}
