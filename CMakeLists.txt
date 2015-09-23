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

FILE(GLOB af_java_src "com/arrayfire/*.java")

ADD_JAR(${AF_JAR} ${af_java_src} MANIFEST "${CMAKE_CURRENT_SOURCE_DIR}/Manifest.txt")

ADD_DEPENDENCIES(${AF_JAR} ${AF_LIB})

INSTALL_JAR(${AF_JAR} ".")

IF(BUILD_EXAMPLES)
    ADD_SUBDIRECTORY(examples)
ENDIF()
