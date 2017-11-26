Desktop applications pack
=========================

A set of UI applications that were written in Java/SWING, as the programming
course illustration for my students. It includes the following applications:

 - Calculator `calc`
 - File manager `filemgr`
 - Raster graphics editor `graphed`
 - Picture viewer `picview`
 - Text editor `texted`


Status
======

Still in development. Some applications are not written/functioning properly


Setup/Run
=========

At first you should have Gradle installed. Gradle is a build system, that
works just like Maven and even better. When you have Gradle installed, you
should run:

    gradle clean build

To build the project

To run specific application, you should run:

    gradle :appname:run

So, if you want to run calculator, you should do the following:

    gradle :calc:run

If you want to run text editor replace `:calc` with `texted`. As you may have
noticed project names described above.

That's it. Happy hacking.

