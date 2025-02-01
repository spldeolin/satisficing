# satisficing

> 「 *equivalent of the well-known “good enough”* 」

Satisficing is a Spring Boot-based web framework, which provides typical modern web development paradigms, useful starters, and convenient utilities. Satisficing aims to help developers create sufficiently excellent, maintainable, and scalable web applications with fewer investment.

## Required

- Java 8
- Maven 3
- MySQL 5.7 and above
- Redis 4 and above

## Quick Start

```shell
# download and setup Satisficing
git clone git@github.com:spldeolin/satisficing.git
mvn install -f satisficing/pom.xml

# generate the project based on Satisficing with Allison1875
mvn archetype:generate \
    -DarchetypeGroupId=com.spldeolin.satisficing \
    -DarchetypeArtifactId=satisficing-archetype \
    -DarchetypeVersion=0.0.1-SNAPSHOT \
    -DarchetypeCatalog=local \
    -DinteractiveMode=false \
    -DgroupId=com.your.group \
    -DartifactId=your-project  \
    -Dversion=0.0.1-SNAPSHOT \
    -DwithAllison1875=true \
    -Dauthor='Deolin' \
    -DjdbcUrl='jdbc:mysql://localhost:3306' \
    -DjdbcSchema='your-project' \
    -DjdbcUsername='root' \
    -DjdbcPassword='root' \
    -DredisUrl='redis://localhost:6379' \
    -DredisPassword='root'

# launch your project
mvn install -f your-project/pom.xml
mvn spring-boot:start -f your-project/your-project-app/pom.xml
curl --request POST \
    --url 'http://localhost:2333/sampleMethod' \
    --header 'Content-Type: application/json' \
    --data '{ "name": "Satisfied Deolin" }'
```
