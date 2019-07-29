package com.arrayfire;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


class ArrayFireException extends JNIException {
  private static final Map<Integer, String> errorCodes;

  static {
    @SuppressWarnings("serial")
    final Map<Integer, String> map = new HashMap<>() {
      {
        put(0, "Success");
        put(101, "AF_ERR_NO_MEM: The system or device ran out of memory.");
        put(102, "AF_ERR_DRIVER: There was an error in the device driver.");
        put(103, "AF_ERR_RUNTIME: There was an error in the runtime environment.");
        put(201, "AF_ERR_INVALID_ARRAY: The input variable is not a valid Array object.");
        put(202, "AF_ERR_ARG: One of the function arguments is incorrect.");
        put(203, "AF_ERR_SIZE: The size is incorrect.");
        put(204, "AF_ERR_TYPE: The type is not supported by this function.");
        put(205, "AF_ERR_DIFF_TYPE: The types of the input arrays are incompatible.");
        put(207, "AF_ERR_BATCH: Function does not support GFOR / batch mode.");
        put(208, "AF_ERR_DEVICE: Input does not belong to the current device.");
        put(301, "AF_ERR_NOT_SUPPORTED: The option is not supported.");
        put(302, "AF_ERR_NOT_CONFIGURED: The build of ArrayFire does not support this feature.");
        put(303, "AF_ERR_NONFREE: The build of ArrayFire is not compiled with \"nonfree\" algorithms");
        put(401, "AF__ERR_NO_DBL: This device does not support double");
        put(402,
            "AF_ERR_NO_GFX: This build of ArrayFire was not built with graphics or this device does not support graphics.");
        put(501, "AF_ERR_LOAD_LIB: There was an error loading the libraries.");
        put(502, "AF_ERR_LOAD_SYM: There was an error when loading the symbols.");
        put(503, "AF_ERR_ARR_BKND_MISMATCH: There was a mismatch between the input array and the active backend.");
        put(998, "AF_ERR_INTERNAL: There was an internal error either in ArrayFire or in a project upstream.");
        put(999, "AF_ERR_UNKNOWN: An unknown error has occured.");
      }
    };
    errorCodes = Collections.unmodifiableMap(map);
  }

  public ArrayFireException(int code, String message) {
    super(String.format("%s %s (code: %d)", errorCodes.get(code), message, code));
  }
}
