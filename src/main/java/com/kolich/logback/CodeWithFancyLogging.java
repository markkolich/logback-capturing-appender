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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CodeWithFancyLogging {

    private static final Logger log = LoggerFactory.getLogger(CodeWithFancyLogging.class);

    private static final class LazyHolder {
        private static final CodeWithFancyLogging instance = new CodeWithFancyLogging();
    }
    public static final CodeWithFancyLogging getInstance() {
        return LazyHolder.instance;
    }

    private CodeWithFancyLogging() {}

    public void doStuff(Integer... input) {
        // ... do stuff, then log some result (in a special format)
        log.info(String.format("%-12d%-12d%07d", input));
    }

    public void doError() {
        log.error("OUCH!");
    }

}
