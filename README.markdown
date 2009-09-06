gather-alert
============

Building
--------
`mvn clean install`


### Note ###

When building with Eclipse, the org.gatherdata.alert.detect.bsf project
will not build unless you change the order of the Java Buil path to 
place the maven dependencies ahead of the JVM Library.


Running
-------
`mvn pax:provision`

Dependencies
------------
[gather-commons](http://github.com/akollegger/gather-commons/)



