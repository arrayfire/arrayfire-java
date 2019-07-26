cmake_minimum_required(VERSION 2.8)

find_package(Java REQUIRED)
include(UseJava)

if(WIN32)
    SET(JAVA_INCLUDE_FLAG_SEP ";")
else(WIN32)
    SET(JAVA_INCLUDE_FLAG_SEP ":")
endif(WIN32)

macro(BUILD_EXAMPLE src)
    # get arrayfire jar file
    get_target_property(_afJar ${AF_JAR} JAR_FILE)
    # extract file name from source file
    get_filename_component(EXAMPLE ${src} NAME_WE)
    # add jar file for given source file
    add_jar(${EXAMPLE} SOURCES ${src} INCLUDE_JARS ${_afJar})
    # udpate the jar file with program entry point
    get_target_property(_jarFile ${EXAMPLE} JAR_FILE)
    add_custom_command(TARGET ${EXAMPLE} COMMAND ${Java_JAR_EXECUTABLE} ufe ${_jarFile} ${EXAMPLE})
    # install examples jar
    install_jar(${EXAMPLE} examples)
    # add target for running examples
    add_custom_target(ex${EXAMPLE} ${Java_JAVA_EXECUTABLE}
        -Djava.library.path="${PROJECT_BINARY_DIR}/src"
        -cp ".${JAVA_INCLUDE_FLAG_SEP}${_afJar}${JAVA_INCLUDE_FLAG_SEP}${_jarFile}" ${EXAMPLE})
endmacro()

build_example(HelloWorld.java)
build_example(WindowEx.java)
build_example(MonteCarloPi.java)
