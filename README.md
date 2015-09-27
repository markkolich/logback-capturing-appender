# logback-capturing-appender

This repo contains code that demonstrates how to capture [Logback](http://logback.qos.ch/) logging output for unit tests.

Sometimes, unit testing involves verifying that code logs the right message at the correct logging level.

Unsurprisingly, this is easily achievable with a custom [Logback appender](http://logback.qos.ch/manual/appenders.html) in your unit tests.

## TL;DR

The class [CodeWithFancyLogging.java](https://github.com/markkolich/logback-capturing-appender/blob/master/src/main/java/com/kolich/logback/CodeWithFancyLogging.java#L47) is built to log a "fancy" logging message with a given format.  The [LogbackCapturingAppender.java](https://github.com/markkolich/logback-capturing-appender/blob/master/src/test/java/com/kolich/logback/LogbackCapturingAppender.java) "injects" itself into the logger used by `CodeWithFancyLogging.java` such that it can receive and capture logging events.

Corresponding unit tests in [CodeWithFancyLoggingTest.java](https://github.com/markkolich/logback-capturing-appender/blob/master/src/test/java/com/kolich/logback/tests/CodeWithFancyLoggingTest.java) use this custom appender to capture logging output in flight and compare the resulting strings against what is expected.

## Running

Clone the repo.

```
git clone https://github.com/markkolich/logback-capturing-appender.git
cd logback-capturing-appender
```

From your favorite command line, invoke:

```
mvn clean test
```

You can also run the unit tests from within IntelliJ using the usual dance &mdash; right click on the `test` package and select "Run tests in...".

## Licensing

Copyright (c) 2015 <a href="http://mark.koli.ch">Mark S. Kolich</a>

All code in this project is freely available for use and redistribution under the <a href="http://opensource.org/comment/991">MIT License</a>.

See <a href="https://github.com/markkolich/logback-capturing-appender/blob/master/LICENSE">LICENSE</a> for details.
