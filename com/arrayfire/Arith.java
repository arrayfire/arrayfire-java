package com.arrayfire;

public class Arith extends Array {

    // Binary operations
    private native static long add(long a, long b);
    private native static long sub(long a, long b);
    private native static long mul(long a, long b);
    private native static long div(long a, long b);
    private native static long le (long a, long b);
    private native static long lt (long a, long b);
    private native static long ge (long a, long b);
    private native static long gt (long a, long b);
    private native static long eq (long a, long b);
    private native static long neq(long a, long b);
    //private native static long pow(long a, float b);

    // Scalar Binary operations
    private native static long addf(long a, float b);
    private native static long subf(long a, float b);
    private native static long mulf(long a, float b);
    private native static long divf(long a, float b);
    private native static long lef (long a, float b);
    private native static long ltf (long a, float b);
    private native static long gef (long a, float b);
    private native static long gtf (long a, float b);
    private native static long eqf (long a, float b);
    private native static long neqf(long a, float b);
    private native static long powf(long a, float b);

    private native static long fsub(float a, long b);
    private native static long fdiv(float a, long b);
    private native static long fle (float a, long b);
    private native static long flt (float a, long b);
    private native static long fge (float a, long b);
    private native static long fgt (float a, long b);
    //private native static long fpow(long a, float b);

    // Unary operations
    private native static long sin  (long a);
    private native static long cos  (long a);
    private native static long tan  (long a);
    private native static long asin (long a);
    private native static long acos (long a);
    private native static long atan (long a);
    private native static long sinh (long a);
    private native static long cosh (long a);
    private native static long tanh (long a);
    private native static long asinh(long a);
    private native static long acosh(long a);
    private native static long atanh(long a);
    private native static long exp  (long a);
    private native static long log  (long a);
    private native static long abs  (long a);
    private native static long sqrt (long a);


    public static void add(Array c, Array a, Array b) throws Exception {
        long ref = add(a.ref,b.ref);
        c.set(ref);
    }

    public static void sub(Array c, Array a, Array b) throws Exception {
        long ref = sub(a.ref,b.ref);
        c.set(ref);
    }

    public static void mul(Array c, Array a, Array b) throws Exception {
        long ref = mul(a.ref,b.ref);
        c.set(ref);
    }

    public static void div(Array c, Array a, Array b) throws Exception {
        long ref = div(a.ref,b.ref);
        c.set(ref);
    }

    public static void le(Array c, Array a, Array b) throws Exception {
        long ref = le(a.ref,b.ref);
        c.set(ref);
    }

    public static void lt(Array c, Array a, Array b) throws Exception {
        long ref = lt(a.ref,b.ref);
        c.set(ref);
    }

    public static void ge(Array c, Array a, Array b) throws Exception {
        long ref = ge(a.ref,b.ref);
        c.set(ref);
    }

    public static void gt(Array c, Array a, Array b) throws Exception {
        long ref = gt(a.ref,b.ref);
        c.set(ref);
    }

    public static void eq(Array c, Array a, Array b) throws Exception {
        long ref = eq(a.ref,b.ref);
        c.set(ref);
    }

    public static void neq(Array c, Array a, Array b) throws Exception {
        long ref = neq(a.ref,b.ref);
        c.set(ref);
    }

    // Unary operations
    public static void sin(Array res, Array a) throws Exception {
        long ref = sin(a.ref);
        res.set(ref);
    }

    public static void cos(Array res, Array a) throws Exception {
        long ref = cos(a.ref);
        res.set(ref);
    }

    public static void tan(Array res, Array a) throws Exception {
        long ref = tan(a.ref);
        res.set(ref);
    }

    public static void asin(Array res, Array a) throws Exception {
        long ref = asin(a.ref);
        res.set(ref);
    }

    public static void acos(Array res, Array a) throws Exception {
        long ref = acos(a.ref);
        res.set(ref);
    }

    public static void atan(Array res, Array a) throws Exception {
        long ref = atan(a.ref);
        res.set(ref);
    }

    public static void sinh(Array res, Array a) throws Exception {
        long ref = sinh(a.ref);
        res.set(ref);
    }

    public static void cosh(Array res, Array a) throws Exception {
        long ref = cosh(a.ref);
        res.set(ref);
    }

    public static void tanh(Array res, Array a) throws Exception {
        long ref = tanh(a.ref);
        res.set(ref);
    }

    public static void asinh(Array res, Array a) throws Exception {
        long ref = asinh(a.ref);
        res.set(ref);
    }

    public static void acosh(Array res, Array a) throws Exception {
        long ref = acosh(a.ref);
        res.set(ref);
    }

    public static void atanh(Array res, Array a) throws Exception {
        long ref = atanh(a.ref);
        res.set(ref);
    }

    public static void exp(Array res, Array a) throws Exception {
        long ref = exp(a.ref);
        res.set(ref);
    }

    public static void log(Array res, Array a) throws Exception {
        long ref = log(a.ref);
        res.set(ref);
    }

    public static void abs(Array res, Array a) throws Exception {
        long ref = abs(a.ref);
        res.set(ref);
    }

    public static void sqrt(Array res, Array a) throws Exception {
        long ref = sqrt(a.ref);
        res.set(ref);
    }

    // Scalar operations
    public static void add(Array c, Array a, float b) throws Exception {
        long ref = addf(a.ref,b);
        c.set(ref);
    }

    public static void sub(Array c, Array a, float b) throws Exception {
        long ref = subf(a.ref,b);
        c.set(ref);
    }

    public static void mul(Array c, Array a, float b) throws Exception {
        long ref = mulf(a.ref,b);
        c.set(ref);
    }

    public static void div(Array c, Array a, float b) throws Exception {
        long ref = divf(a.ref,b);
        c.set(ref);
    }

    public static void le(Array c, Array a, float b) throws Exception {
        long ref = lef(a.ref,b);
        c.set(ref);
    }

    public static void lt(Array c, Array a, float b) throws Exception {
        long ref = ltf(a.ref,b);
        c.set(ref);
    }

    public static void ge(Array c, Array a, float b) throws Exception {
        long ref = gef(a.ref,b);
        c.set(ref);
    }

    public static void gt(Array c, Array a, float b) throws Exception {
        long ref = gtf(a.ref,b);
        c.set(ref);
    }

    public static void eq(Array c, Array a, float b) throws Exception {
        long ref = eqf(a.ref,b);
        c.set(ref);
    }

    public static void neq(Array c, Array a, float b) throws Exception {
        long ref = neqf(a.ref,b);
        c.set(ref);
    }

    public static void pow(Array c, Array a, float b) throws Exception {
        long ref = powf(a.ref,b);
        c.set(ref);
    }

    public static void add(Array c, float a, Array b) throws Exception {
        long ref = addf(b.ref,a);
        c.set(ref);
    }

    public static void sub(Array c, float a, Array b) throws Exception {
        long ref = fsub(a,b.ref);
        c.set(ref);
    }

    public static void mul(Array c, float a, Array b) throws Exception {
        long ref = mulf(b.ref,a);
        c.set(ref);
    }

    public static void div(Array c, float a, Array b) throws Exception {
        long ref = fdiv(a,b.ref);
        c.set(ref);
    }

    public static void le(Array c, float a, Array b) throws Exception {
        long ref = fle(a,b.ref);
        c.set(ref);
    }

    public static void lt(Array c, float a, Array b) throws Exception {
        long ref = flt(a,b.ref);
        c.set(ref);
    }

    public static void ge(Array c, float a, Array b) throws Exception {
        long ref = fge(a,b.ref);
        c.set(ref);
    }

    public static void gt(Array c, float a, Array b) throws Exception {
        long ref = fgt(a,b.ref);
        c.set(ref);
    }
}
