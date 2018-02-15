# DEER
[![Build Status](https://travis-ci.org/dice-group/DEER.svg?branch=master)](https://travis-ci.org/dice-group/DEER) [![Chat on Gitter](https://badges.gitter.im/deer-rdf.png)](https://gitter.im/deer-rdf)

The RDF Dataset Enrichment Framework (DEER), is a modular, extensible software system for efficient
computation of arbitrary operations on RDF datasets.  
The atomic operations involved in this process, dubbed *enrichment operators*, 
are configured using RDF, making DEER a native semantic web citizen.  
Enrichment operators are mapped to nodes of a directed acyclic graphs to build complex enrichment
models, in which the connections between two nodes represent intermediary datasets.

## Running DEER

To bundle DEER as a single jar file, do

```
mvn clean package shade:shade -Dmaven.test.skip=true
```

Then execute it using

```
java -jar path_to_config.ttl
```

## Maven

```
<dependencies>
  <dependency>
    <groupId>com.github.dice-group</groupId>
    <artifactId>deer</artifactId>
    <version>1.0-alpha</version>
  </dependency>
</dependencies>

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```


## Documentation

For more detailed information about how to run or extend DEER, please read the
[manual](https://dice-group.github.io/deer/) and consult the
[Javadoc](https://dice-group.github.io/deer/javadoc/)
