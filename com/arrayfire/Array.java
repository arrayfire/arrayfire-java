package com.arrayfire;

public class Array implements AutoCloseable {

    public static final int FloatType = 0;
    public static final int FloatComplexType = 1;
    public static final int DoubleType = 2;
    public static final int DoubleComplexType = 3;
    public static final int BooleanType = 4;
    public static final int IntType = 5;

    static {
        System.loadLibrary("af_java");
    }

    public native static void info();

    private native static void destroyArray(long ref);
    private native static int[] getDims(long ref);
    private native static int   getType(long ref);

    // Global reference to JVM object
    // to persist between JNI calls
    protected long ref;

    public Array() {
        ref = 0;
    }

    protected Array(long other_ref) {
        ref = other_ref;
    }

    protected void set(long other_ref) {
        if (ref != 0) destroyArray(ref);
        ref = other_ref;
    }

    public int[] dims() {
        return getDims(ref);
    }

    public int type() {
        return getType(ref);
    }

    public String typeName(int ty) throws Exception {
        if (ty == FloatType) return "float";
        if (ty == DoubleType) return "double";
        if (ty == IntType) return "int";
        if (ty == BooleanType) return "boolean";
        if (ty == FloatComplexType) return "FloatComplex";
        if (ty == DoubleComplexType) return "DoubleComplex";
        throw new Exception("Unknown type");
    }

    protected static int[] dim4(int[] dims) throws Exception {

        if( dims == null ) {
            throw new Exception("Null dimensions object provided");
        } else if ( dims.length > 4 ) {
            throw new Exception("ArrayFire supports up to 4 dimensions only");
        }

        int[] adims;
        adims = new int[] {1, 1, 1, 1};
        for (int i = 0; i < dims.length; i++) adims[i] = dims[i];

        return adims;
    }

    protected void assertType(int ty) throws Exception {

        int myType = type();

        if( myType != ty ) {
            String str = "Type mismatch: ";
            str = str + "Requested " + typeName(ty);
            str = str + ". Found " + typeName(myType);
            throw new Exception(str);
        }
        return;
    }

    @Override
    public void close() throws Exception {
        if (ref != 0) destroyArray(ref);
    }

}
