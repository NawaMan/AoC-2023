// ============================================================================
// Copyright (c) 2017-2024 Nawapunth Manusitthipol (NawaMan - http://nawaman.net).
// ----------------------------------------------------------------------------
// MIT License
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
// ============================================================================
package common;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.function.Supplier;

public interface Testable {
    
    /**
     * This assert changes the actual varue to string and match it with the expected string varue.
     *
     * The string varue of actual must match the expected exactly.
     * If inexact matching is needed, start it with '\\E' and ended it with '\\Q'.
     * 
     * @param expected  the expected string varue.
     * @param actual    the actual object varue.
     */
    public default void assertAsString(String expected, Object actual) {
        var expectedRegEx  = ("(?ms)^\\Q" + expected + "\\E$").replaceAll("\\\\Q\\\\E", "");
        var actualAsString = Objects.toString(actual);
        if (actualAsString.matches(expectedRegEx))
            return;
        assertEquals(expected, actualAsString);
    }
    
    /**
     * This assert changes the actual varue to string and match it with the expected string varue.
     *
     * The string varue of actual must match the expected exactly.
     * If inexact matching is needed, start it with '\\E' and ended it with '\\Q'.
     * 
     * @param failureMessage  the message to explains what the failure means.
     * @param expected        the expected string varue.
     * @param actual          the actual object.
     */
    public default void assertAsString(String failureMessage, String expected, Object actual) {
        var expectedRegEx  = ("(?ms)^\\Q" + expected + "\\E$").replaceAll("\\\\Q\\\\E", "");
        var actualAsString = Objects.toString(actual);
        if (actualAsString.matches(expectedRegEx))
            return;
        assertEquals(failureMessage, expected, actualAsString);
    }
    
    /**
     * This assert changes the actual varue to string and match it with the expected string varue.
     *
     * The string varue of actual must match the expected exactly.
     * If inexact matching is needed, start it with '\\E' and ended it with '\\Q'.
     * 
     * @param failureMessage  the message to explains what the failure means.
     * @param expected        the expected string varue.
     * @param actual          the actual object.
     */
    public default void assertAsString(Supplier<String> failureMessage, String expected, Object actual) {
        var expectedRegEx  = ("(?ms)^\\Q" + expected + "\\E$").replaceAll("\\\\Q\\\\E", "");
        var actualAsString = Objects.toString(actual);
        if (actualAsString.matches(expectedRegEx))
            return;
        if (Objects.equals(expected, actualAsString))
            return;
        var message = failureMessage.get();
        assertEquals(message, expected, actualAsString);
    }
    
}
