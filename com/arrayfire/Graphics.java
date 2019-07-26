package com.arrayfire;

class Graphics extends ArrayFire {

  public enum ColorMap {
    DEFAULT(0),
    SPECTRUM(1),
    COLORS(2),
    RED(3),
    MOOD(4),
    HEAT(5),
    BLUE(6),
    INFERNO(7),
    MAGMA(8),
    PLASMA(9),
    VIRIDIS(10);

    private final int map;

    private ColorMap(int map) {
      this.map = map;
    }

    public int getMap() {
      return map;
    }
  }

  public enum MarkerType {
    NONE(0),
    POINT(1),
    CIRCLE(2),
    SQUARE(3),
    TRIANGLE(4),
    CROSS(5),
    PLUS(6),
    STAR(7);

    private final int type;

    private MarkerType(int type) {
      this.type = type;
    }

    public int getType() {
      return type;
    }
  }

  public static native long afInitWindow(int width, int height, String name);

  public static native void afDestroyWindow(long wnd);

  public static native void afSetPos(long wnd, int x, int y);

  public static native void afSetTitle(long wnd, String title);

  public static native void afSetSize(long wnd, int w, int h);

  public static native void afDrawImage(long wnd, long arr, int r, int c,
                                        String title, int cmap);

  public static native void afDrawPlotnd(long wnd, long arr, int r, int c,
                                         String title);

  public static native void afDrawPlot2d(long wnd, long arrX, long arrY,
                                         int r, int c, String title);

  public static native void afDrawPlot3d(long wnd, long arrX, long arrY,
                                         long arrZ, int r, int c, String title);

  public static native void afDrawScatternd(long wnd, long arr, int r, int c,
                                            int markerType, String title);

  public static native void afDrawScatter2d(long wnd, long arrX, long arrY,
                                            int r, int c,
                                            int markerType, String title);

  public static native void afDrawScatter3d(long wnd, long arrX, long arrY,
                                            long arrZ, int r, int c,
                                            int markerType, String title);

  public static native void afDrawHist(long wnd, long arr, int r, int c,
                                       double min, double max, String title);

  public static native void afDrawSurface(long wnd, long arr, long xVals,
                                          long yVals, int r, int c,
                                          String title);

  public static native void afDrawVectorFieldnd(long wnd, long points,
                                                long directions, int r,
                                                int c, String title);

  public static native void afDrawVectorField2d(long wnd, long xPoints,
                                                long yPoints, long xDirections,
                                                long yDirections, int r,
                                                int c, String title);

  public static native void afDrawVectorField3d(long wnd, long xPoints,
                                                long yPoints, long zPoints,
                                                long xDirections,
                                                long yDirections,
                                                long zDirections, int r, int c,
                                                String title);

  public static native void afGrid(long wnd, int rows, int columns);

  public static native void afSetAxesLimitsCompute(long wnd, long arrX,
                                                   long arrY, long arrZ,
                                                   boolean isExact, int r,
                                                   int c);

  public static native void afSetAxesLimits2d(long wnd, float xMin, float xMax,
                                              float yMin, float yMax,
                                              boolean isExact,
                                              int r, int c);

  public static native void afSetAxesLimits3d(long wnd, float xMin, float xMax,
                                              float yMin, float yMax,
                                              float zMin,
                                              float zMax, boolean isExact,
                                              int r, int c);

  public static native void afSetAxesTitles(String xTitle, String yTitle, String zTitle);

  public static native void afShow(long wnd);

  public static native boolean afClose(long wnd);

  public static native void afSetVisibility(long wnd, boolean isVisible);
}
