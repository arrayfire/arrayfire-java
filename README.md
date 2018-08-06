arrayfire-java
==============

[Slack Channel](https://join.slack.com/t/arrayfire-org/shared_invite/enQtMjI4MjIzMDMzMTczLWM4ODIyZjA3YmY3NWEwMjk2N2Q0YTQyNGMwZmU4ZjkxNGU0MjYzYmUzYTg3ZTM0MDQxOTE2OTJjNGVkOGEwN2M)

This repository contains the files required to use ArrayFire from Java.

Prerequisites
---------------

- The latest version of ArrayFire. You can get ArrayFire using one of the following:
    - [Download binary installers](http://www.arrayfire.com/download)
    - [Build from source](https://github.com/arrayfire/arrayfire)

- The latest version of `JAVA SDK`. It has been tested with OpenJDK 1.7 and OpenJDK 1.8. Make sure there is an environmental variable `JAVA_HOME` pointing to the root directory of java sdk installation.

- CUDA
    - Tested for CUDA 7.5

- CMake, minimum version of 2.8.
	- On Linux/OSX, it defaults to standard makefiles.
	- On Windows, we have tested with `NMake Makefiles`.

Contents
---------------

- `src/`: Contains the source files for the ArrayFire Java wrapper
    - `*.cpp` The JNI wrapper files
    - `jni_helper.h` The JNI API helper functions

- `com/`: Contains the Java source files implementing algorithms

- `examples`: contains a few examples demonstrating the usage

Usage
----------------

After you the necessary pre-requisites, do the following:

- `mkdir build`
- `cd build`
- Configure and generate the platform specific make files.
	- `cmake ..` on Linux/OSX
	- `cmake -G "NMake Makefiles" ..` on Windows from visual studio x64 command prompt.
- Build the project and run helloworld example.
	- `make && make exHelloWorld` on Linux/OSX.
	- `nmake && nmake exHelloWorld` on Windows.

Documentation
---------------
- TODO

License
---------------

- Please check the LICENSE file in the root directory
