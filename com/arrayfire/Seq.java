package com.arrayfire;

import static com.arrayfire.Util.*;
import static com.arrayfire.ArrayFire.range;
import static com.arrayfire.ArrayFire.add;
import static com.arrayfire.ArrayFire.mul;

public class Seq extends AFLibLoader {
    // Start position of the sequence
    private double begin;
    // End position of the sequence (inclusive)
    private double end;
    // Step size between sequence values
    private double step;

    private long size;
    protected boolean gfor; // always false until implemented

    public Seq() {
        this(0);
    }

    public Seq(double length) {
        if (length < 0) {
            init(0, length, 1);
        } else {
            init(0, length - 1, 1);
        }
    }

    public Seq(double begin, double end) throws Exception {
        this(begin, end, 1);
    }

    public Seq(double begin, double end, double step) throws Exception {
        if (step == 0 && begin != end) { // Span
            throw new IllegalArgumentException("Invalid step size.");
        }
        if ((signbit(end) == signbit(begin)) && (signbit(end - begin) != signbit(step))) {
            throw new Exception("Sequence is invalid");
        }
        init(begin, end, step);
    }

    public Seq(Seq other) {
        set(other);
    }

    protected static Seq span() {
        try {
            return new Seq(1, 1, 0); // will never throw
        } catch (Exception ex) {
            return null;
        }
    }

    public void set(Seq other) {
        init(other.begin, other.end, other.step);
    }

    public Seq negate() throws Exception {
        return new Seq(-begin, -end, -step);
    }

    public Seq addOffset(double offset) throws Exception {
        return new Seq(begin + offset, end + offset, step);
    }

    public Seq subtractOffset(double offset) throws Exception {
        return new Seq(begin - offset, end - offset, step);
    }

    public Seq spaceBy(double factor) throws Exception {
        return new Seq(begin * factor, end * factor, step * factor);
    }

    public Array toArray() throws Exception {
        double diff = end - begin;
        int len = (int)((diff + Math.abs(step) * (signbit(diff) == 0 ? 1 : -1)) / step);
        Array tmp = range(len);
        Array res = new Array();
        mul(res, (float) step, tmp);
        add(res, (float)begin, res);
        return res;
    }

    private void init(double begin, double end, double step) {
        this.begin = begin;
        this.end = end;
        this.step = step;
        this.gfor = false;
        this.size = (long)(step != 0 ? Math.abs((end - begin) / step) + 1 : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Seq seq = (Seq) obj;
        return this.begin == seq.begin && this.end == seq.end &&
            this.step == seq.step && size == seq.size;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ((int)begin ^ ((int)begin >>> 32));
        result = prime * result + (int) ((int) end ^ ((int) end >>> 32));
        result = prime * result + (int) ((int) step ^ ((int) step >>> 32));
        result = prime * result + (int) ((int) size ^ ((int) size >>> 32));
        return result;
    }
}
