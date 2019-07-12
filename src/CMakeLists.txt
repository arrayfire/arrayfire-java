ENABLE_LANGUAGE(CXX)

FIND_PACKAGE(JNI REQUIRED)
FIND_PACKAGE(ArrayFire QUIET)

IF(NOT ArrayFire_FOUND)
    MESSAGE(FATAL_ERROR "Please point environment variable ArrayFire_DIR to ArrayFire installation
    directory")
ENDIF()

INCLUDE_DIRECTORIES(${JNI_INCLUDE_DIRS} ${ArrayFire_INCLUDE_DIRS})

ADD_LIBRARY(${AF_LIB} SHARED
  jni_helper.h
  algorithm.cpp
  arith.cpp
  array.cpp
  data.cpp
  image.cpp
  signal.cpp
  util.cpp
  )

TARGET_LINK_LIBRARIES(${AF_LIB} ${JNI_LIBRARIES} ${ArrayFire_LIBRARIES})

IF(${UNIX})
    SET(CMAKE_CXX_FLAGS "-fPIC")
    SET_TARGET_PROPERTIES(${AF_LIB} PROPERTIES CMAKE_SHARED_LINKER_FLAGS "-fPIC")
ELSEIF(${WINDOWS})
    SET(CMAKE_CXX_FLAGS "/MP /EHsc")
ENDIF(${UNIX})

INSTALL(TARGETS ${AF_LIB} DESTINATION lib)
