arrayfire_java
==============

This repository contains the files required to use ArrayFire from Java.

Prerequisites
---------------

- The latest version of ArrayFire. You can [download here](http://www.accelereyes.com/download_arrayfire)
    - All the pre-requisites for ArrayFire still apply.

- The latest version of `JAVA SDK`. Make sure there is an environmental variable `JAVA_HOME` pointing to the root directory of java sdk installation.

- make
    - `GNU Make` on `Linux`
    - `nmake` on `Windows`

- C++ compiler
    - `gcc/g++` on Linux
    - `Visual Studio 2012` for `Windows`.

- OSX support coming soon.

Contents
---------------

- `src/`: Contains the source files for the ArrayFire Java wrapper
    - `java_wrapper.cpp` The JNI wrapper file
    - `java_wrapper.h` The JNI API definitions

- `com/`: Contains the Java source files implementing `Array` and `Image` classes

- `lib/`, `lib64/`: The location where the JNI library is stored

- `examples`: contains a few examples demonstrating the usage

Usage
----------------

After you the necessary pre-requisites, edit the following paramets

- Open `Makefile` and change `AF_PATH` to the right location


### Linux

- To build the JNI Wrapper for ArrayFire use
    - `make cuda`   to build using CUDA
    - `make opencl` to build using OpenCL

- To build the examples do one of the following from the examples directory
    - `make cuda run  ` to use build and run examples using CUDA
    - `make opencl run` to use build and run examples using OpenCL


### Windows

- To build the JNI Wrapper for ArrayFire
    - Open the project `ArrayFireJavaWrapper.vcxproj` in Visual Studio 2012 and build it.
    - OpenCL support coming soon.

- To build and run the examples
    - Launch `Developer command prompt for Visual Studio 2012`.
    - `nmake /F Makefile.Windows` to build and run all examples.
    - `nmake /F Makefile.Windows [HelloWorld | MonteCarloPi]` to run a specific example.


Documentation
---------------
- TODO

License
---------------

- Please check the LICENSE file in the root directory

