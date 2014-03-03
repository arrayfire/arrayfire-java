package com.arrayfire;

public class Image extends Array {

    static {
        System.loadLibrary("arrayfire");
    }

    private native static long erode    (long a, long b);
    private native static long dilate   (long a, long b);
    private native static long convolve (long a, long b);
    private native static long medfilt  (long a, int w, int h);
    private native static long bilateral(long a, float space, float color);
    private native static long meanshift(long a, float space, float color, int iter);
    private native static long histogram(long a, int nbins);
    private native static long hist_mnmx(long a, int nbins, float min, float max);
    private native static long rotate   (long a, float theta, boolean crop);
    private native static long resize1  (long a, float scale, char method);
    private native static long resize2  (long a, float scalex, float scaley, char method);
    private native static long resize3  (long a, int height, int width, char method);
    //private native static long fft      (long a, long b);
    //private native static long fft2     (long a, long b);
    //private native static long fft3     (long a, long b);

    public Image() throws Exception { super(); }

    public Image(int[] dims) throws Exception { super(dims); }

    public Image(int[] dims, float[] elems) throws Exception { super(dims,elems); }

    public static Image erode(Image a, Image b) throws Exception {
        Image ret_val = new Image();
        ret_val.ref = erode(a.ref,b.ref);
        return ret_val;
    }

    public static Image dilate(Image a, Image b) throws Exception {
        Image ret_val = new Image();
        ret_val.ref = dilate(a.ref,b.ref);
        return ret_val;
    }

    public static Image convolve(Image a, Image b) throws Exception {
        Image ret_val = new Image();
        ret_val.ref = convolve(a.ref,b.ref);
        return ret_val;
    }

    public static Image medianfilter(Image a, int width, int height) throws Exception {
        Image ret_val = new Image();
        ret_val.ref = medfilt(a.ref,width,height);
        return ret_val;
    }

    public static Image bilateral(Image a, float space, float color) throws Exception {
        Image ret_val = new Image();
        ret_val.ref = bilateral(a.ref,space,color);
        return ret_val;
    }

    public static Image meanshift(Image a, float space, float color, int iterations) throws Exception {
        Image ret_val = new Image();
        ret_val.ref = meanshift(a.ref,space,color,iterations);
        return ret_val;
    }

    public static Image histogram(Image a, int nbins) throws Exception {
        Image ret_val = new Image();
        ret_val.ref = histogram(a.ref,nbins);
        return ret_val;
    }

    public static Image histogram(Image a, int nbins, float min, float max) throws Exception {
        Image ret_val = new Image();
        ret_val.ref = hist_mnmx(a.ref,nbins,min,max);
        return ret_val;
    }

    public static Image rotate(Image a, float theta, boolean crop) throws Exception {
        Image ret_val = new Image();
        ret_val.ref = rotate(a.ref,theta,crop);
        return ret_val;
    }

    // method ='L' - Linear interpolation
    // or 'N' - Nearest neighbor
    public static Image resize(Image a, float scale, char method) throws Exception {
        Image ret_val = new Image();
        ret_val.ref = resize1(a.ref,scale,method);
        return ret_val;
    }

    public static Image resize(Image a, float scalex, float scaley, char method) throws Exception {
        Image ret_val = new Image();
        ret_val.ref = resize2(a.ref,scalex,scaley,method);
        return ret_val;
    }

    public static Image resize(Image a, int height, int width, char method) throws Exception {
        Image ret_val = new Image();
        ret_val.ref = resize3(a.ref,height,width,method);
        return ret_val;
    }

}
