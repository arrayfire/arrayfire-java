package com.arrayfire;

public class Statistics extends ArrayFire {
    static private native long afMean(long ref, int dim);

    static private native long afMeanWeighted(long ref, long weightsRef, int dim);

    static private native double afMeanAll(long ref, int[] dims);

    static private native FloatComplex afMeanAllFloatComplex(long ref, int[] dims);
    static private native DoubleComplex afMeanAllDoubleComplex(long ref, int[] dims);

    //static private native jlong afMeanAllWeighted(long ref, long weightsRef);

    static public Array mean(final Array in, int dim) {
        return new Array(afMean(in.ref, dim));
    }

    static public Array mean(final Array in, final Array weights, int dim) {
        return new Array(afMeanWeighted(in.ref, weights.ref, dim));
    }

    static public <T> T mean(final Array in, Class<T> type) throws Exception {
        if (type == FloatComplex.class) {
            FloatComplex res = (FloatComplex)afMeanAllFloatComplex(in.ref, in.dims());
            return type.cast(res);
        } else if (type == DoubleComplex.class) {
            DoubleComplex res = (DoubleComplex)afMeanAllDoubleComplex(in.ref, in.dims());
            return type.cast(res);
        }

        double res = afMeanAll(in.ref, in.dims());
        if (type == Float.class) {
            return type.cast(Float.valueOf((float)res));
        } else if (type == Double.class) {
            return type.cast(Double.valueOf((double) res));
        } else if (type == Integer.class) {
            return type.cast(Integer.valueOf((int) res));
        }
        throw new Exception("Unknown type");
    }
}

