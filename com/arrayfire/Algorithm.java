package com.arrayfire;

public class Algorithm extends Array {

    // Scalar return operations
    private native static double sumAll(long a);
    private native static double maxAll(long a);
    private native static double minAll(long a);

    private native static long sum(long a, int dim);
    private native static long max(long a, int dim);
    private native static long min(long a, int dim);

    // Scalar return operations
    public static double sumAll(Array a) throws Exception { return sumAll(a.ref); }
    public static double maxAll(Array a) throws Exception { return maxAll(a.ref); }
    public static double minAll(Array a) throws Exception { return minAll(a.ref); }

    public static Array sum(Array a, int dim) throws Exception {
        Array ret_val = new Array();
        ret_val.ref = sum(a.ref, dim);
        return ret_val;
    }

    public static Array max(Array a, int dim) throws Exception {
        Array ret_val = new Array();
        ret_val.ref = max(a.ref, dim);
        return ret_val;
    }

    public static Array min(Array a, int dim) throws Exception {
        Array ret_val = new Array();
        ret_val.ref = min(a.ref, dim);
        return ret_val;
    }

    public static Array sum(Array a) throws Exception {
        return sum(a, -1);
    }

    public static Array max(Array a) throws Exception {
        return max(a, -1);
    }

    public static Array min(Array a) throws Exception {
        return min(a, -1);
    }
}
