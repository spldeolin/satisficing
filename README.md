# satisficing

> 「 *equivalent of the well-known “good enough”* 」

Satisficing is a Spring Boot-based web framework, which provides typical modern web development paradigms, useful starters, and convenient utilities. Satisficing aims to help developers create sufficiently excellent, maintainable, and scalable web applications with fewer investment.

## Required

- Java 8

- Maven 3

## Quick Start

```shell
git clone git@github.com:spldeolin/satisficing.git
mvn install -f satisficing/pom.xml

# generate the project based on Satisficing
mvn archetype:generate \
    -DarchetypeGroupId=com.spldeolin.satisficing \
    -DarchetypeArtifactId=satisficing-archetype \
    -DarchetypeVersion=0.0.1-SNAPSHOT \
    -DgroupId=com.your.group \
    -DartifactId=your-project  \
    -Dversion=0.0.1-SNAPSHOT \
    -DwithAllison1875=true \
    -DarchetypeCatalog=local \
    -DinteractiveMode=false
```
