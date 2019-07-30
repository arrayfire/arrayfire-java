package com.arrayfire;

public class Index {
    private Array arr;
    private Seq seq;
    private boolean isSeq;
    private boolean isBatch;

    private static native long afLookup(long in, long index, int dim);
    private static native void afCopy(long dst, long src, Object idx0, Object idx1,
                                      Object idx2, Object idx3);

    public Index() {
        seq = ArrayFire.SPAN;
        isSeq = true;
        isBatch = false;
    }

    public Index(int idx) throws Exception {
        seq = new Seq(idx, idx, 1);
        isSeq = true;
        isBatch = false;
    }

    public Index(final Seq seq) throws Exception {
        this.seq = new Seq(seq);
        isSeq = true;
        isBatch = this.seq.gfor;
    }

    public Index(final Array idx) throws Exception {
        arr = idx.isBool() ? Algorithm.where(idx) : new Array(idx.ref);
        isSeq = false;
        isBatch = false;
    }

    public Index(final Index other) {
        arr = new Array(other.arr.ref);
        seq = new Seq(other.seq);
        isSeq = other.isSeq;
        isBatch = other.isBatch;
    }

    public long getArrayRef() {
        return arr.ref;
    }

    public Seq getSeq() {
        return seq;
    }

    public boolean isSeq() {
        return isSeq;
    }

    public boolean isBatch() {
        return isBatch;
    }

    public boolean isSpan() {
        return isSeq && seq == ArrayFire.SPAN;
    }

    static Array lookup(final Array in, final Array idx, int dim) throws Exception {
        return new Array(afLookup(in.ref, idx.ref, dim));
    }

    static void copy(Array dst, final Array src, Index idx0, Index idx1,
                     Index idx2, Index idx3) throws Exception{
        afCopy(dst.ref, src.ref, idx0, idx1, idx2, idx3);
    }
}
