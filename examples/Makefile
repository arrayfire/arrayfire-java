ifeq ($(shell uname -m), x86_64)
  LIB:=lib64
else
  LIB:=lib
endif

AF_JAVA_PATH?=../
AF_JAVA_JAR?=$(AF_JAVA_PATH)/ArrayFire.jar
AF_JAVA_LIB_PATH?=$(AF_JAVA_PATH)/$(LIB)

CLASSES=$(patsubst %.java, %.class, $(shell ls *.java))
BINS=$(patsubst %.class, %, $(CLASSES)) 

all: $(CLASSES)

run: $(BINS)

%: %.class
	LD_LIBRARY_PATH=$(AF_JAVA_LIB_PATH) java -cp .:$(AF_JAVA_JAR) $@

%.class: %.java
	javac -cp $(AF_JAVA_PATH)/ArrayFire.jar $<
