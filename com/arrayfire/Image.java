package com.arrayfire;

public class Image extends Array {

    private native static long erode    (long a, long b);
    private native static long dilate   (long a, long b);
    private native static long medfilt  (long a, int w, int h);
    private native static long bilateral(long a, float space, float color);
    private native static long meanshift(long a, float space, float color, int iter);
    private native static long histogram(long a, int nbins);
    private native static long hist_mnmx(long a, int nbins, float min, float max);
    private native static long rotate   (long a, float theta, boolean crop, int method);
    private native static long resize1  (long a, float scale, int method);
    private native static long resize2  (long a, float scalex, float scaley, int method);
    private native static long resize3  (long a, int height, int width, int method);

    public static void erode(Array res, Array a, Array b) throws Exception {
        long ref = erode(a.ref,b.ref);
        res.set(ref);
    }

    public static void dilate(Array res, Array a, Array b) throws Exception {
        long ref = dilate(a.ref,b.ref);
        res.set(ref);
    }

    public static void medianfilter(Array res, Array a, int width, int height) throws Exception {
        long ref = medfilt(a.ref,width,height);
        res.set(ref);
    }

    public static void bilateral(Array res, Array a, float space, float color) throws Exception {
        long ref = bilateral(a.ref,space,color);
        res.set(ref);
    }

    public static void meanshift(Array res, Array a, float space, float color, int iterations) throws Exception {
        long ref = meanshift(a.ref,space,color,iterations);
        res.set(ref);
    }

    public static void histogram(Array res, Array a, int nbins) throws Exception {
        long ref = histogram(a.ref,nbins);
        res.set(ref);
    }

    public static void histogram(Array res, Array a, int nbins, float min, float max) throws Exception {
        long ref = hist_mnmx(a.ref,nbins,min,max);
        res.set(ref);
    }

    public static void rotate(Array res, Array a, float theta, boolean crop) throws Exception {
        long ref = rotate(a.ref,theta,crop,0);
        res.set(ref);
    }

    public static void rotate(Array res, Array a, float theta, boolean crop, int method) throws Exception {
        long ref = rotate(a.ref,theta,crop,method);
        res.set(ref);
    }

    public static void resize(Array res, Array a, float scale) throws Exception {
        long ref = resize1(a.ref,scale,0);
        res.set(ref);
    }

    public static void resize(Array res, Array a, float scale, int method) throws Exception {
        long ref = resize1(a.ref,scale,method);
        res.set(ref);
    }

    public static void resize(Array res, Array a, float scalex, float scaley) throws Exception {
        long ref = resize2(a.ref,scalex,scaley,0);
        res.set(ref);
    }

    public static void resize(Array res, Array a, float scalex, float scaley, int method) throws Exception {
        long ref = resize2(a.ref,scalex,scaley,method);
        res.set(ref);
    }

    public static void resize(Array res, Array a, int height, int width) throws Exception {
        long ref = resize3(a.ref,height,width,0);
        res.set(ref);
    }

    public static void resize(Array res, Array a, int height, int width, int method) throws Exception {
        long ref = resize3(a.ref,height,width,method);
        res.set(ref);
    }
}
