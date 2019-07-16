package com.arrayfire;

public class Util extends ArrayFire {

  public native static void info();

  public static String toString(Array a, String delim) {
    String ret_txt = "";
    try {
      float[] fary = Data.getFloatArray(a);
      for (int k = 0; k < fary.length - 1; ++k) {
        String temp = Float.toString(fary[k]) + delim;
        ret_txt += temp;
      }
      ret_txt += Float.toString(fary[fary.length - 1]);
    } catch (Exception e) {
      ret_txt = "Failed to convert to string";
    }
    return ret_txt;
  }

  public static float[] toFloatArray(String text, String delim) {
    String[] list = text.split(delim);
    float[] ret_ary = new float[list.length];
    for (int k = 0; k < list.length; ++k) {
      ret_ary[k] = Float.parseFloat(list[k]);
    }
    return ret_ary;
  }
}
