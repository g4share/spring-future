
[![Build Status](https://travis-ci.org/g4share/spring-future.svg?branch=master)](https://travis-ci.org/g4share/spring-future)

Testing CompletableFuture with Spring Controllers

run **./gradlew build** to build

run **./gradlew bootRun** to build and start application

run **java -jar spring-future-0.1.0.jar** to start application


| Link        | Description   |
| ------------- |-------------|
| **http://localhost:8080/** | load UI |


| Messages API | Description   |
| ------------- |-------------|
| **http://localhost:8080/m10/synch/** | load 10 records from the **synch** method |
| **http://localhost:8080/m10/combine/** | load 10 records from the **combine** method |
| **http://localhost:8080/m10/stream/** | load 10 records from the **stream** method |
| **http://localhost:8080/m10/parallelstream/** | load 10 records from the **parallelstream** method |
