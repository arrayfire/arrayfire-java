package com.arrayfire;

import android.util.Log;

public class Array implements AutoCloseable {

	static {
		System.loadLibrary("af_java");
	}

	private native static long createArray(int[] dims);
    private native static long createArrayElems(int[] dims, float[] elems);
	private native static void destroyArray(long ref);
	private native static float[] host(long ref);
    private native static int[] getDims(long ref);

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
	private native static long ne (long a, long b);

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

    // Scalar return operations
    private native static float sum(long a);
    private native static float max(long a);
    private native static float min(long a);

    // Scalar operations
    private native static long addf(long a, float b);
   	private native static long subf(long a, float b);
	private native static long mulf(long a, float b);
	private native static long divf(long a, float b);
	private native static long lef (long a, float b);
	private native static long ltf (long a, float b);
	private native static long gef (long a, float b);
	private native static long gtf (long a, float b);
	private native static long eqf (long a, float b);
	private native static long nef (long a, float b);
   	private native static long pow (long a, float b);
    private native static long fsub(float a, long b);
	private native static long fdiv(float a, long b);
	private native static long fle (float a, long b);
	private native static long flt (float a, long b);
	private native static long fge (float a, long b);
	private native static long fgt (float a, long b);

    // Global reference to JVM object
    // to persist between JNI calls
	long ref;

    public Array() {
        ref = 0;
    }

    // Below version of constructor
    // allocates space on device and initializes
    // all elemets to zero
    public Array(int[] dims) throws Exception {
        if( dims == null ) {
            throw new Exception("Null dimensions object provided");
        } else if ( dims.length > 3 ) {
            throw new Exception("Upto 3 dimensions only supported for now.");
        }
		this.ref = createArray(dims);
	}

    public Array(int[] dims, float[] elems) throws Exception {
        if( dims == null || elems == null ) {
            throw new Exception("Null object provided");
        } else if ( dims.length > 3 ) {
            throw new Exception("Upto 3 dimensions only supported for now.");
        }
        int total_size = 1;
        for (int i = 0; i < dims.length; i++) total_size *= dims[i];

        if( elems.length > total_size || elems.length < total_size ) {
            throw new Exception("Mismatching dims and array size");
        }
        Log.i("Array", "============ Array class =============== ");
		this.ref = createArrayElems(dims,elems);
	}

	public int[] dims() {
		return getDims(ref);
	}

    public float[] host() throws Exception {
        return host(ref);
    }

    // Binary operations
	public static Array add(Array a, Array b) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = add(a.ref,b.ref);
		return ret_val;
	}

    public static Array sub(Array a, Array b) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = sub(a.ref,b.ref);
		return ret_val;
	}

    public static Array mul(Array a, Array b) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = mul(a.ref,b.ref);
		return ret_val;
	}

    public static Array div(Array a, Array b) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = div(a.ref,b.ref);
		return ret_val;
	}

    public static Array le(Array a, Array b) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = le(a.ref,b.ref);
		return ret_val;
	}

    public static Array lt(Array a, Array b) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = lt(a.ref,b.ref);
		return ret_val;
	}

    public static Array ge(Array a, Array b) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = ge(a.ref,b.ref);
		return ret_val;
	}

    public static Array gt(Array a, Array b) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = gt(a.ref,b.ref);
		return ret_val;
	}

    public static Array eq(Array a, Array b) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = eq(a.ref,b.ref);
		return ret_val;
	}

    public static Array ne(Array a, Array b) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = ne(a.ref,b.ref);
		return ret_val;
	}

    // Unary operations
    public static Array sin(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = sin(a.ref);
		return ret_val;
	}

    public static Array cos(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = cos(a.ref);
		return ret_val;
	}

    public static Array tan(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = tan(a.ref);
		return ret_val;
	}

    public static Array asin(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = asin(a.ref);
		return ret_val;
	}

    public static Array acos(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = acos(a.ref);
		return ret_val;
	}

    public static Array atan(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = atan(a.ref);
		return ret_val;
	}

    public static Array sinh(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = sinh(a.ref);
		return ret_val;
	}

    public static Array cosh(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = cosh(a.ref);
		return ret_val;
	}

    public static Array tanh(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = tanh(a.ref);
		return ret_val;
	}

    public static Array asinh(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = asinh(a.ref);
		return ret_val;
	}
    public static Array acosh(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = acosh(a.ref);
		return ret_val;
	}
    public static Array atanh(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = atanh(a.ref);
		return ret_val;
	}
    public static Array exp(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = exp(a.ref);
		return ret_val;
	}
    public static Array log(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = log(a.ref);
		return ret_val;
	}
    public static Array abs(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = abs(a.ref);
		return ret_val;
	}
    public static Array sqrt(Array a) throws Exception {
		Array ret_val = new Array();
		ret_val.ref = sqrt(a.ref);
		return ret_val;
	}

    // Scalar return operations
    public static float sum(Array a) throws Exception { return sum(a.ref); }

    public static float max(Array a) throws Exception { return max(a.ref); }

    public static float min(Array a) throws Exception { return min(a.ref); }

    // Scalar operations
    public static Array add(Array a, float b) throws Exception {
        Array res = new Array();
        res.ref = addf(a.ref,b);
        return res;
    }

    public static Array sub(Array a, float b) throws Exception {
        Array res = new Array();
        res.ref = subf(a.ref,b);
        return res;
    }

    public static Array mul(Array a, float b) throws Exception {
        Array res = new Array();
        res.ref = mulf(a.ref,b);
        return res;
    }

    public static Array div(Array a, float b) throws Exception {
        Array res = new Array();
        res.ref = divf(a.ref,b);
        return res;
    }

    public static Array le(Array a, float b) throws Exception {
        Array res = new Array();
        res.ref = lef(a.ref,b);
        return res;
    }

    public static Array lt(Array a, float b) throws Exception {
        Array res = new Array();
        res.ref = ltf(a.ref,b);
        return res;
    }

    public static Array ge(Array a, float b) throws Exception {
        Array res = new Array();
        res.ref = gef(a.ref,b);
        return res;
    }

    public static Array gt(Array a, float b) throws Exception {
        Array res = new Array();
        res.ref = gtf(a.ref,b);
        return res;
    }

    public static Array eq(Array a, float b) throws Exception {
        Array res = new Array();
        res.ref = eqf(a.ref,b);
        return res;
    }

    public static Array ne(Array a, float b) throws Exception {
        Array res = new Array();
        res.ref = nef(a.ref,b);
        return res;
    }

    public static Array pow(Array a, float b) throws Exception {
        Array res = new Array();
        res.ref = pow(a.ref,b);
        return res;
    }

    public static Array sub(float a, Array b) throws Exception {
        Array res = new Array();
        res.ref = fsub(a,b.ref);
        return res;
    }

    public static Array div(float a, Array b) throws Exception {
        Array res = new Array();
        res.ref = fdiv(a,b.ref);
        return res;
    }

    public static Array le(float a, Array b) throws Exception {
        Array res = new Array();
        res.ref = fle(a,b.ref);
        return res;
    }

    public static Array lt(float a, Array b) throws Exception {
        Array res = new Array();
        res.ref = flt(a,b.ref);
        return res;
    }

    public static Array ge(float a, Array b) throws Exception {
        Array res = new Array();
        res.ref = fge(a,b.ref);
        return res;
    }

    public static Array gt(float a, Array b) throws Exception {
        Array res = new Array();
        res.ref = fgt(a,b.ref);
        return res;
    }

	@Override
	public void close() throws Exception {
		if (ref != 0) destroyArray(ref);
	}

}
