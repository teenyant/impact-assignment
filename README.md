# impact-assignment

## Problem

Implement code which has the ability to produce a comma delimited list of numbers, grouping the numbers into a range when they are sequential. 

## Requirements
- Java version 17
- Maven 3.9.9

## Build main

```bash
cd numberranges
mvn clean install
```

### Run with own input

```bash
mvn exec:java -Dexec.args="1,3,6,7,8,12,13,14,15,21,22,23,24,31"
```

## Run unit tests

The unit tests were made using []

```bash
cd numberranges
mvn test
```