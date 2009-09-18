gather-alert
============

Building
--------
`mvn clean install`

There are multiple dao implementations which can be selected by specifiying
the appropriate profile. Check the pom.xml for the latest definitions.

Also, to build everything, define build-all to be true:

`mvn clean install -Dbuild-all=true`


### Notes ###

1. When building with Eclipse, the org.gatherdata.alert.detect.bsf project
will not build unless you change the order of the Java Buil path to 
place the maven dependencies ahead of the JVM Library.


Running
-------
`mvn pax:provision`

Dependencies
------------
[gather-commons](http://github.com/akollegger/gather-commons/)



