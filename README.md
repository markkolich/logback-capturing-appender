# logback-capturing-appender

This repo demonstrates how to capture Logback logging output for unit tests.

Sometimes, you need to verify that a class passes unit tests *and* logs the right message at the correct logging level.

Not surprisingly, this is easily achievable using a custom Logback appender in unit tests.