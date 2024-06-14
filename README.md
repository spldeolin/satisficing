# satisficing

> 「 *equivalent of the well-known “good enough”* 」

Satisficing is a Spring Boot-based web framework, which provides typical modern web development paradigms, useful starters, and convenient utilities. Statisficing is committed to helping developers develop web applications that are good, maintainable, and scalable enough with less human and time resources.

## Required

- Java 8

- Maven 3

## Quick Start

```shell
git clone git@github.com:spldeolin/satisficing.git
mvn istall -f satisficing/pom.xml

# generate the project based on Satisficing
mvn archetype:generate \
    -DarchetypeGroupId=com.spldeolin.satisficing \
    -DarchetypeArtifactId=satisficing-based-archetype \
    -DarchetypeVersion=0.0.1-SNAPSHOT \
    -DgroupId=com.your.group \
    -DartifactId=your-project  \
    -Dversion=0.0.1-SNAPSHOT \
    -DarchetypeCatalog=local \
    -DinteractiveMode=false
```
