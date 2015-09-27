/**
 * Copyright (c) 2015 Mark S. Kolich
 * http://mark.koli.ch
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.kolich.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A class which demonstrates how to inject yourself into a {@link Logger} used by a specific
 * class under test.  This allows you to intercept logging events (level and message) to verify
 * that a specific class under test is logging the correct information and in the way that you'd
 * expect.
 */
public final class LogbackCapturingAppender extends AppenderBase<ILoggingEvent> {

    public static final class Factory {

        private static final List<LogbackCapturingAppender> ALL = Lists.newLinkedList();

        public static LogbackCapturingAppender attach(final org.slf4j.Logger slf4jLogger) {
            final LogbackCapturingAppender appender = new LogbackCapturingAppender(slf4jLogger);
            ALL.add(appender);
            return appender;
        }

        public static void detach() {
            ALL.forEach(LogbackCapturingAppender::detach);
        }

    }

    private final Logger logger_;
    private final List<ILoggingEvent> events_;

    private LogbackCapturingAppender(final org.slf4j.Logger slf4jLogger) {
        logger_ = (Logger) slf4jLogger;
        logger_.setLevel(Level.ALL);
        logger_.addAppender(this);
        events_ = Lists.newLinkedList();
        start();
    }

    /**
     * Returns the {@link List} of all captured logging messages since this appender was
     * attached to the logger.  Note the returned list is not immutable, although the caller
     * shouldn't be doing anything stupid with it (like modifying it, in flight).
     *
     * Will return an empty {@link List} if no logging events have been captured.
     */
    public List<String> getMessages() {
        return events_.stream().map(ILoggingEvent::getMessage).collect(Collectors.toList());
    }

    /**
     * Returns the first logger event message in the {@link List} of captured logging messages.
     *
     * Will return null if no logging events have been captured.
     */
    public String getFirstMessage() {
        return Iterables.getFirst(getMessages(), null);
    }

    /**
     * Returns the {@link List} of all captured logging levels since this appender was
     * attached to the logger.  Note the returned list is not immutable, although the caller
     * shouldn't be doing anything stupid with it (like modifying it, in flight).
     *
     * Will return an empty {@link List} if no logging events have been captured.
     */
    public List<Level> getLevels() {
        return events_.stream().map(ILoggingEvent::getLevel).collect(Collectors.toList());
    }

    /**
     * Returns the first logger event level in the {@link List} of captured logging messages.
     *
     * Will return null if no logging events have been captured.
     */
    public Level getFirstLevel() {
        if (!events_.isEmpty()) {
            return events_.stream().findFirst().get().getLevel();
        }
        return null;
    }

    @Override
    protected void append(final ILoggingEvent event) {
        events_.add(event);
    }

    private void detach() {
        logger_.detachAppender(this);
        events_.clear();
    }

}
