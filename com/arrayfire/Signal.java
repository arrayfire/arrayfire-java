package com.arrayfire;

public class Signal extends Array {

    private native static long fft  (long a);
    private native static long fft2 (long a);
    private native static long fft3 (long a);

    private native static long ifft (long a);
    private native static long ifft2(long a);
    private native static long ifft3(long a);

    public static Array fft(Array a) throws Exception {
        Array ret_val = new Array();
        ret_val.ref = fft(a.ref);
        return ret_val;
    }

    public static Array fft2(Array a) throws Exception {
        Array ret_val = new Array();
        ret_val.ref = fft2(a.ref);
        return ret_val;
    }

    public static Array fft3(Array a) throws Exception {
        Array ret_val = new Array();
        ret_val.ref = fft3(a.ref);
        return ret_val;
    }

    public static Array ifft(Array a) throws Exception {
        Array ret_val = new Array();
        ret_val.ref = ifft(a.ref);
        return ret_val;
    }

    public static Array ifft2(Array a) throws Exception {
        Array ret_val = new Array();
        ret_val.ref = ifft2(a.ref);
        return ret_val;
    }

    public static Array ifft3(Array a) throws Exception {
        Array ret_val = new Array();
        ret_val.ref = ifft3(a.ref);
        return ret_val;
    }
}
