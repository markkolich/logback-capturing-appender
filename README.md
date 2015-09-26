# logback-capturing-appender

This repo contains code that demonstrates how to capture [Logback](http://logback.qos.ch/) logging output for unit tests.

Sometimes, you need to verify that a class passes unit tests **and** logs the right message at the correct logging level.

Unsurprisingly, this is easily achievable with a custom [Logback appender](http://logback.qos.ch/manual/appenders.html) in your unit tests.

## TL;DR

The class [CodeWithFancyLogging.java](https://github.com/markkolich/logback-capturing-appender/blob/master/src/main/java/com/kolich/logback/CodeWithFancyLogging.java#L47) is built to log a "fancy" logging message with a given format.  The [LogbackCapturingAppender.java](https://github.com/markkolich/logback-capturing-appender/blob/master/src/test/java/com/kolich/logback/LogbackCapturingAppender.java) "injects" itself into the logger used by *CodeWithFancyLogging.java* such that it can receive and capture logging events.

Corresponding unit tests in [CodeWithFancyLoggingTest.java](https://github.com/markkolich/logback-capturing-appender/blob/master/src/test/java/com/kolich/logback/tests/CodeWithFancyLoggingTest.java) use this custom appender to capture logging output in flight and compare the resulting strings against what is expected.