#include "jni_helper.h"

BEGIN_EXTERN_C

#define GRAPHICS_FUNC(FUNC) AF_MANGLE(Graphics, FUNC)
#define WND(ref) (void *)ref

JNIEXPORT jlong JNICALL GRAPHICS_FUNC(afInitWindow)(JNIEnv *env, jclass clazz,
                                                    jint width, jint height,
                                                    jstring name) {
  af_window wnd;
  const char *cName = env->GetStringUTFChars(name, 0);
  AF_CHECK(af_create_window(&wnd, width, height, cName));
  env->ReleaseStringUTFChars(name, cName);
  return JLONG(wnd);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDestroyWindow)(JNIEnv *env, jclass clazz,
                                                      jlong wnd) {
  AF_CHECK_VOID(af_destroy_window(WND(wnd)));
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afSetPos)(JNIEnv *env, jclass clazz,
                                               jlong wnd, jint x, jint y) {
  AF_CHECK_VOID(af_set_position(WND(wnd), x, y));
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afSetTitle)(JNIEnv *env, jclass clazz,
                                                 jlong wnd, jstring title) {
  const char *cTitle = env->GetStringUTFChars(title, 0);
  AF_CHECK_VOID(af_set_title(WND(wnd), cTitle));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afSetSize)(JNIEnv *env, jclass clazz,
                                                jlong wnd, jint w, jint h) {
  AF_CHECK_VOID(af_set_size(WND(wnd), w, h));
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDrawImage)(JNIEnv *env, jclass clazz,
                                                  jlong wnd, jlong arr, jint r,
                                                  jint c, jstring title,
                                                  int cmap) {
  const char *cTitle = env->GetStringUTFChars(title, 0);
  af_cell props{r, c, cTitle, static_cast<af_colormap>(cmap)};
  AF_CHECK_VOID(af_draw_image(WND(wnd), ARRAY(arr), &props));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDrawPlotnd)(JNIEnv *env, jclass clazz,
                                                   jlong wnd, jlong arr, jint r,
                                                   jint c, jstring title) {
  const char *cTitle = env->GetStringUTFChars(title, 0);
  af_cell props{r, c, cTitle, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(af_draw_plot_nd(WND(wnd), ARRAY(arr), &props));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDrawPlot2d)(JNIEnv *env, jclass clazz,
                                                   jlong wnd, jlong arrX,
                                                   jlong arrY, jint r, jint c,
                                                   jstring title) {
  const char *cTitle = env->GetStringUTFChars(title, 0);
  af_cell props{r, c, cTitle, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(af_draw_plot_2d(WND(wnd), ARRAY(arrX), ARRAY(arrY), &props));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDrawPlot3d)(JNIEnv *env, jclass clazz,
                                                   jlong wnd, jlong arrX,
                                                   jlong arrY, jlong arrZ,
                                                   jint r, jint c,
                                                   jstring title) {
  const char *cTitle = env->GetStringUTFChars(title, 0);
  af_cell props{r, c, cTitle, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(
      af_draw_plot_3d(WND(wnd), ARRAY(arrX), ARRAY(arrY), ARRAY(arrZ), &props));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDrawScatternd)(JNIEnv *env, jclass clazz,
                                                      jlong wnd, jlong arr,
                                                      jint r, jint c,
                                                      jint markerType,
                                                      jstring title) {
  const char *cTitle = env->GetStringUTFChars(title, 0);
  af_cell props{r, c, cTitle, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(af_draw_scatter_nd(
      WND(wnd), ARRAY(arr), static_cast<af_marker_type>(markerType), &props));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDrawScatter2d)(JNIEnv *env, jclass clazz,
                                                      jlong wnd, jlong arrX,
                                                      jlong arrY, jint r,
                                                      jint c, jint markerType,
                                                      jstring title) {
  const char *cTitle = env->GetStringUTFChars(title, 0);
  af_cell props{r, c, cTitle, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(af_draw_scatter_2d(WND(wnd), ARRAY(arrX), ARRAY(arrY),
                                   static_cast<af_marker_type>(markerType),
                                   &props));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDrawScatter3d)(
    JNIEnv *env, jclass clazz, jlong wnd, jlong arrX, jlong arrY, jlong arrZ,
    jint r, jint c, jint markerType, jstring title) {
  const char *cTitle = env->GetStringUTFChars(title, 0);
  af_cell props{r, c, cTitle, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(
      af_draw_scatter_3d(WND(wnd), ARRAY(arrX), ARRAY(arrY), ARRAY(arrZ),
                         static_cast<af_marker_type>(markerType), &props));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDrawHist)(JNIEnv *env, jclass clazz,
                                                 jlong wnd, jlong arr, jint r,
                                                 jint c, jdouble min,
                                                 jdouble max, jstring title) {
  const char *cTitle = env->GetStringUTFChars(title, 0);
  af_cell props{r, c, cTitle, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(af_draw_hist(WND(wnd), ARRAY(arr), min, max, &props));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDrawSurface)(JNIEnv *env, jclass clazz,
                                                    jlong wnd, jlong arr,
                                                    jlong xVals, jlong yVals,
                                                    jint r, jint c,
                                                    jstring title) {
  const char *cTitle = env->GetStringUTFChars(title, 0);
  af_cell props{r, c, cTitle, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(af_draw_surface(WND(wnd), ARRAY(xVals), ARRAY(yVals),
                                ARRAY(arr), &props));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDrawVectorFieldnd)(
    JNIEnv *env, jclass clazz, jlong wnd, jlong points, jlong directions,
    jint r, jint c, jstring title) {

  const char *cTitle = env->GetStringUTFChars(title, 0);
  af_cell props{r, c, cTitle, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(af_draw_vector_field_nd(WND(wnd), ARRAY(points),
                                        ARRAY(directions), &props));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDrawVectorField2d)(
    JNIEnv *env, jclass clazz, jlong wnd, jlong xPoints, jlong yPoints,
    jlong xDirections, jlong yDirections, jint r, jint c, jstring title) {

  const char *cTitle = env->GetStringUTFChars(title, 0);
  af_cell props{r, c, cTitle, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(af_draw_vector_field_2d(WND(wnd), ARRAY(xPoints),
                                        ARRAY(yPoints), ARRAY(xDirections),
                                        ARRAY(yDirections), &props));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afDrawVectorField3d)(
    JNIEnv *env, jclass clazz, jlong wnd, jlong xPoints, jlong yPoints,
    jlong zPoints, jlong xDirections, jlong yDirections, jlong zDirections,
    jint r, jint c, jstring title) {

  const char *cTitle = env->GetStringUTFChars(title, 0);
  af_cell props{r, c, cTitle, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(af_draw_vector_field_3d(
      WND(wnd), ARRAY(xPoints), ARRAY(yPoints), ARRAY(zPoints),
      ARRAY(xDirections), ARRAY(yDirections), ARRAY(zDirections), &props));
  env->ReleaseStringUTFChars(title, cTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afGrid)(JNIEnv *env, jclass clazz,
                                             jlong wnd, jint rows,
                                             jint columns) {
  AF_CHECK_VOID(af_grid(WND(wnd), rows, columns));
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afSetAxesLimitsCompute)(
    JNIEnv *env, jclass clazz, jlong wnd, jlong arrX, jlong arrY, jlong arrZ,
    jboolean isExact, jint r, jint c) {

  af_cell props{r, c, NULL, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(af_set_axes_limits_compute(WND(wnd), ARRAY(arrX), ARRAY(arrY),
                                           arrZ > 0 ? ARRAY(arrZ) : NULL,
                                           isExact, &props));
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afSetAxesLimits2d)(
    JNIEnv *env, jclass clazz, jlong wnd, jfloat xMin, jfloat xMax, jfloat yMin,
    jfloat yMax, jboolean isExact, jint r, jint c) {

  af_cell props{r, c, NULL, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(
      af_set_axes_limits_2d(WND(wnd), xMin, xMax, yMin, yMax, isExact, &props));
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afSetAxesLimits3d)(
    JNIEnv *env, jclass clazz, jlong wnd, jfloat xMin, jfloat xMax, jfloat yMin,
    jfloat yMax, jfloat zMin, jfloat zMax, jboolean isExact, jint r, jint c) {

  af_cell props{r, c, NULL, AF_COLORMAP_DEFAULT};
  AF_CHECK_VOID(af_set_axes_limits_3d(WND(wnd), xMin, xMax, yMin, yMax, zMin,
                                      zMax, isExact, &props));
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afSetAxesTitles)(JNIEnv *env, jclass clazz,
                                                      jlong wnd, jstring xTitle,
                                                      jstring yTitle,
                                                      jstring zTitle, jint r,
                                                      jint c) {
  af_cell props{r, c, NULL, AF_COLORMAP_DEFAULT};
  const char *cXTitle = env->GetStringUTFChars(xTitle, 0);
  const char *cYTitle = env->GetStringUTFChars(yTitle, 0);
  const char *cZTitle = env->GetStringUTFChars(zTitle, 0);
  AF_CHECK_VOID(
      af_set_axes_titles(WND(wnd), cXTitle, cYTitle, cZTitle, &props));
  env->ReleaseStringUTFChars(xTitle, cXTitle);
  env->ReleaseStringUTFChars(yTitle, cYTitle);
  env->ReleaseStringUTFChars(zTitle, cZTitle);
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afShow)(JNIEnv *env, jclass clazz,
                                             jlong wnd) {
  AF_CHECK_VOID(af_show(WND(wnd)));
}

JNIEXPORT jboolean JNICALL GRAPHICS_FUNC(afClose)(JNIEnv *env, jclass clazz,
                                             jlong wnd) {
    bool closed = true;
    AF_CHECK(af_is_window_closed(&closed, WND(wnd)));
    return (jboolean)closed;
}

JNIEXPORT void JNICALL GRAPHICS_FUNC(afSetVisibility)(JNIEnv *env, jclass clazz,
                                                      jlong wnd,
                                                      jboolean isVisible) {
  AF_CHECK_VOID(af_set_visibility(WND(wnd), isVisible));
}

END_EXTERN_C
