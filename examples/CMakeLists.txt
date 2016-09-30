CMAKE_MINIMUM_REQUIRED(VERSION 2.8)

FIND_PACKAGE(Java REQUIRED)
INCLUDE(UseJava)

FILE(GLOB af_examples_src "*.java")

MACRO(BUILD_EXAMPLE src)
    # get arrayfire jar file
    GET_TARGET_PROPERTY(_afJar ${AF_JAR} JAR_FILE)
    # extract file name from source file
    GET_FILENAME_COMPONENT(EXAMPLE ${src} NAME_WE)
    # add jar file for given source file
    ADD_JAR(${EXAMPLE} SOURCES ${src} INCLUDE_JARS ${_afJar})
    # udpate the jar file with program entry point
    GET_TARGET_PROPERTY(_jarFile ${EXAMPLE} JAR_FILE)
    ADD_CUSTOM_COMMAND(TARGET ${EXAMPLE} COMMAND ${Java_JAR_EXECUTABLE} ufe ${_jarFile} ${EXAMPLE})
    # install examples jar
    INSTALL_JAR(${EXAMPLE} examples)
    # add target for running examples
    ADD_CUSTOM_TARGET(ex${EXAMPLE} ${Java_JAVA_EXECUTABLE}
        -Djava.library.path="${PROJECT_BINARY_DIR}/src"
        -cp .:${_afJar}:${_jarFile} ${EXAMPLE})
ENDMACRO()

FOREACH(FILE ${af_examples_src})
    BUILD_EXAMPLE(${FILE})
ENDFOREACH()
