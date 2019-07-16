CMAKE_MINIMUM_REQUIRED(VERSION 2.8)
PROJECT(ArrayFire-Java)

SET(AF_JAR ArrayFire)
SET(AF_LIB af_java)

OPTION(BUILD_EXAMPLES "Option for building examples" ON)
# Set a default build type if none was specified
IF(NOT CMAKE_BUILD_TYPE AND NOT CMAKE_CONFIGURATION_TYPES)
    SET(CMAKE_BUILD_TYPE Release CACHE STRING "Choose the type of build." FORCE)
    # Set the possible values of build type for cmake-gui
    SET_PROPERTY(CACHE CMAKE_BUILD_TYPE PROPERTY STRINGS "Debug" "Release"
      "MinSizeRel" "RelWithDebInfo")
ENDIF()

FIND_PACKAGE(Java REQUIRED)
INCLUDE(UseJava)

ADD_SUBDIRECTORY(src)

ADD_JAR(${AF_JAR}
  com/arrayfire/Algorithm.java
  com/arrayfire/Arith.java
  com/arrayfire/ArrayFire.java
  com/arrayfire/Array.java
  com/arrayfire/Data.java
  com/arrayfire/DoubleComplex.java
  com/arrayfire/FloatComplex.java
  com/arrayfire/Image.java
  com/arrayfire/Signal.java
  com/arrayfire/Util.java
  com/util/JNIException.java
  com/arrayfire/ArrayFireException.java
  )

ADD_DEPENDENCIES(${AF_JAR} ${AF_LIB})

INSTALL_JAR(${AF_JAR} ".")

IF(BUILD_EXAMPLES)
    ADD_SUBDIRECTORY(examples)
ENDIF()
