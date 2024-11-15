# impact-assignment

## Problem

"Implement code which has the ability to produce a comma delimited list of numbers, grouping the numbers into a range when they are sequential." 

## Requirements
- Java version 17
- Maven 3.9.9 with Junit 3.8.1

## Build main

```bash
cd numberranges
mvn clean install
```

### Run with own input

The app works with command-line arguments and it also works with reading from standard input. The input can either a list of integers comma delimited or a file path.


Using maven directly:

```bash
mvn exec:java 
mvn exec:java -Dexec.args="1,3,6,7,8,12,13,14,15,21,22,23,24,31"
mvn exec:java -Dexec.args="src/test/resources/bigtestInput.txt"
```

Using the target folder:
```bash
java -cp target/classes numberrangesummarizer.App
java -cp target/classes numberrangesummarizer.App "1,3,6,7,8,12,13,14,15,21,22,23,24,31"
java -cp target/classes numberrangesummarizer.App src/test/resources/bigtestInput.txt
```

## Run unit tests

The unit tests can be run as follows, unit tests 8 and 9 make use an input file and were made using [this website](https://texttools.org/random-number-generator). 

```bash
cd numberranges
mvn test
```