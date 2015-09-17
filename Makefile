AF_PATH ?= /opt/arrayfire

ifneq ($(shell uname), Linux)
$(error Only Linux supported for Java)
endif

LIB:=lib

AF_CFLAGS	= -I$(AF_PATH)/include
AF_CFLAGS      += -I$(JAVA_HOME)/include
AF_CFLAGS      += -I$(JAVA_HOME)/include/linux
AF_LIB_PATH	= $(AF_PATH)/$(LIB)/
AF_JAVA_PATH	= $(shell pwd)
AF_JAVA_LIB	= $(AF_JAVA_PATH)/$(LIB)/libaf_java.so
AF_JAVA_JAR	= $(AF_JAVA_PATH)/ArrayFire.jar
AF_JAVA_MANIFEST= $(AF_JAVA_PATH)/Manifest.txt
AF_JAVA_COM     = $(shell ls com/arrayfire/*.java)
AF_JAVA_CLASSES = $(patsubst %.java, %.class, $(AF_JAVA_COM))
AF_JNI_SRC      = $(shell ls $(AF_JAVA_PATH)/src/*.cpp)

ifeq ($(findstring opencl, $(MAKECMDGOALS)), opencl)
	AF=afopencl
	AF_JAVA_LIB_EXT=$(patsubst %.so, %_ocl.so, $(AF_JAVA_LIB))
else
	AF=afcuda
	AF_JAVA_LIB_EXT=$(patsubst %.so, %_cuda.so, $(AF_JAVA_LIB))
endif

run: all
	AF_JAVA_PATH=$(AF_JAVA_PATH) make -C examples run

cuda: all

opencl: all

all: $(AF_JAVA_JAR)

$(AF_JAVA_JAR): $(AF_JAVA_LIB) $(AF_JAVA_CLASSES)
	jar cfm $@ $(AF_JAVA_MANIFEST) $(AF_JAVA_CLASSES)

%.class: %.java
	javac $<

$(AF_JAVA_LIB): $(AF_JAVA_LIB_EXT)
	cp $(AF_JAVA_LIB_EXT) $(AF_JAVA_LIB)
	cp $(AF_LIB_PATH)/lib$(AF).so $(AF_JAVA_PATH)/$(LIB)

$(AF_JAVA_LIB_EXT): $(AF_JNI_SRC)
	gcc -shared -fPIC $(AF_JNI_SRC) $(AF_CFLAGS) -L$(AF_LIB_PATH) -l$(AF) -o $@

clean:
	rm -f lib/*.so $(AF_JAVA_JAR)
	rm -f $(AF_JAVA_CLASSES)
