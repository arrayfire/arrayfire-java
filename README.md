arrayfire-java
==============

[![Join the chat at https://gitter.im/arrayfire/arrayfire-java](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/arrayfire/arrayfire-java?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This repository contains the files required to use ArrayFire from Java.

Prerequisites
---------------

- The latest version of ArrayFire. You can get ArrayFire using one of the following:
    - [Download binary installers](http://www.arrayfire.com/download)
    - [Build from source](https://github.com/arrayfire/arrayfire)

- The latest version of `JAVA SDK`. Make sure there is an environmental variable `JAVA_HOME` pointing to the root directory of java sdk installation.

- make
    - `GNU Make` on Linux

- C++ compiler
    - `gcc` or `clang` on Linux

- OSX and Windows support coming soon

Contents
---------------

- `src/`: Contains the source files for the ArrayFire Java wrapper
    - `*.cpp` The JNI wrapper files
    - `jni_helper.h` The JNI API helper functions

- `com/`: Contains the Java source files implementing algorithms

- `lib/`: The location where the JNI library is stored

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

Documentation
---------------
- TODO

License
---------------

- Please check the LICENSE file in the root directory
