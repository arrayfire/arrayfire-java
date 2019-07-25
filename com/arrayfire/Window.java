package com.arrayfire;

import com.arrayfire.Graphics.ColorMap;
import com.arrayfire.Graphics.MarkerType;
import com.sun.tools.jdeps.Graph;

import jdk.jfr.Unsigned;

public class Window implements AutoCloseable {

  protected long ref;
  private int r;
  private int c;
  private ColorMap cmap;

  public Window() {
    ref = 0;
    r = c = -1;
    cmap = ColorMap.DEFAULT;
    initWindow(1280, 720, "ArrayFire");
  }

  public Window(String title) {
    ref = 0;
    r = c = -1;
    cmap = ColorMap.DEFAULT;
    initWindow(1280, 720, title);
  }

  public Window(int width, int height, String title) {
    ref = 0;
    r = c = -1;
    cmap = ColorMap.DEFAULT;
    initWindow(width, height, title);
  }

  public Window(int width, int height) {
    ref = 0;
    r = c = -1;
    cmap = ColorMap.DEFAULT;
    initWindow(width, height, "ArrayFire");
  }

  public Window(long ref) {
    this.ref = ref;
    r = c = -1;
    cmap = ColorMap.DEFAULT;
  }

  private void initWindow(int width, int height, String title) {
    ref = Graphics.afInitWindow(width, height, title);
  }

  protected void set(long ref) {
    if (this.ref != 0) {
      Graphics.afDestroyWindow(this.ref);
    }
    this.ref = ref;
  }

  public void setPos(@Unsigned int x, @Unsigned int y) {
    Graphics.afSetPos(ref, x, y);
  }

  public void setTitle(String title) {
    Graphics.afSetTitle(ref, title);
  }

  public void setSize(@Unsigned int w, @Unsigned int h) {
    Graphics.afSetSize(ref, w, h);
  }

  public void setColorMap(ColorMap cmap) {
    this.cmap = cmap;
  }

  public void image(final Array in, String title) {
    Graphics.afDrawImage(ref, in.ref, r, c, title, cmap.getMap());
  }

  public void plot(final Array in, String title) {
    Graphics.afDrawPlotnd(ref, in.ref, r, c, title);
  }

  public void plot(final Array x, final Array y, String title) {
    Graphics.afDrawPlot2d(ref, x.ref, y.ref, r, c, title);
  }

  public void plot(final Array x, final Array y, final Array z, String title) {
    Graphics.afDrawPlot3d(ref, x.ref, y.ref, z.ref, r, c, title);
  }

  public void scatter(final Array in, MarkerType type, String title) {
    Graphics.afDrawScatternd(ref, in.ref, r, c, type.getType(), title);
  }

  public void scatter(final Array x, final Array y, MarkerType type, String title) {
    Graphics.afDrawScatter2d(ref, x.ref, y.ref, c, r, type.getType(), title);
  }

  public void scatter(final Array x, final Array y, final Array z, MarkerType type, String title) {
    Graphics.afDrawScatter3d(ref, x.ref, y.ref, z.ref, c, r, type.getType(), title);
  }

  public void hist(final Array in, double min, double max, String title) {
    Graphics.afDrawHist(ref, in.ref, r, c, min, max, title);
  }

  public void surface(final Array xVals, final Array yVals, final Array s, String title) {
    Graphics.afDrawSurface(ref, s.ref, x.ref, y.ref, r, c, title);
  }

  public void vectorField(final Array points, final Array directions, String title) {
    Graphics.afDrawVectorFieldnd(ref, points.ref, directions.ref, r, c, title);
  }

  public void vectorField(final Array xPoints, final Array yPoints, final Array xDirections, final Array yDirections,
      String title) {
    Graphics.afDrawVectorField2d(ref, xPoints.ref, yPoints.ref, xDirections.ref, yDirections.ref, r, c, title);
  }

  public void vectorField(final Array xPoints, final Array yPoints, final Array zPoints, final Array xDirections,
      final Array yDirections, final Array zDirections, String title) {
    Graphics.afDrawVectorField3d(ref, xPoints.ref, yPoints.ref, zPoints.ref, xDirections.ref, yDirections.ref,
        zDirections.ref, r, c, title);
  }

  public void grid(int rows, int cols) {
    Graphics.afGrid(ref, rows, cols);
  }

  public void setAxesLimits(final Array x, final Array y, boolean isExact) {
    Graphics.afSetAxesLimitsCompute(ref, x.ref, y.ref, 0, isExact, r, c);
  }

  public void setAxesLimits(final Array x, final Array y, final Array z, boolean isExact) {
    Graphics.afSetAxesLimitsCompute(ref, x.ref, y.ref, z.ref, isExact, r, c);
  }

  public void setAxesLimits(float xMin, float xMax, float yMin, float yMax, boolean isExact) {
    Graphics.afSetAxesLimits2d(ref, xMin, xMax, yMin, yMax, isExact, r, c);
  }

  public void setAxesLimits(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax, boolean isExact) {
    Graphics.afSetAxesLimits3d(ref, xMin, xMax, yMin, yMax, zMin, zMax, isExact, r, c);
  }

  public void setAxesTitles(String xTitle, String yTitle, String zTitle) {
    Graphics.afSetAxesTitles(xTitle, yTitle, zTitle);
  }

  public void show() {
    Graphics.afShow(ref);
    r = c = -1;
  }

  public boolean closeWindow() {
    return Graphics.afClose(ref);
  }

  public void setVisibility(boolean isVisible) {
    Graphics.afSetVisibility(ref, isVisible);
  }

  @Override
  public void close() throws Exception {
    if (ref != 0) {
      Graphics.afDestroyWindow(ref);
    }
  }
}
