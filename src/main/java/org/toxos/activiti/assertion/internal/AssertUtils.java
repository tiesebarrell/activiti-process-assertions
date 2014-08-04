/*******************************************************************************
 * Copyright 2014 Tiese Barrell
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.toxos.activiti.assertion.internal;

/**
 * Provides utilities for process assertions.
 * 
 * @author Tiese Barrell
 */
public final class AssertUtils {

    private static final String NULL_VALUE = "<null>";
    private static final String ARRAY_START = "{";
    private static final String ARRAY_END = "}";
    private static final String ARRAY_SEPARATOR = ", ";

    private static final int INDEX_NOT_FOUND = -1;

    private AssertUtils() {
        super();
    }

    /**
     * Checks if the provided String is blank, meaning it is empty or contains whitespace only.
     *
     * @param string
     *            the String to check, may be null
     * @return {@code true} if the String is blank, false otherwise
     */
    public static final boolean stringIsBlank(final String string) {

        if (string == null || string.length() == 0) {
            return true;
        }

        int stringLength = string.length();
        for (int i = 0; i < stringLength; i++) {
            if (Character.isWhitespace(string.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Provides a string representation of the provided array of objects.
     * 
     * @param array
     *            the array of objects to represent
     * @return a string representation of the objects
     */
    public static final String arrayToString(final Object[] array) {
        final StringBuilder builder = new StringBuilder();
        builder.append(ARRAY_START);
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                final String toAppend = array[i] == null ? NULL_VALUE : array[i].toString();
                builder.append(toAppend);
                if (i != array.length - 1) {
                    builder.append(ARRAY_SEPARATOR);
                }
            }
        }
        builder.append(ARRAY_END);
        return builder.toString();
    }

    /**
     * <p>
     * Replaces a String with another String inside a larger String, for the first {@code max} values of the search
     * String.
     * </p>
     *
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     *
     * <pre>
     * StringUtils.replace(null, *, *, *)         = null
     * StringUtils.replace("", *, *, *)           = ""
     * StringUtils.replace("any", null, *, *)     = "any"
     * StringUtils.replace("any", *, null, *)     = "any"
     * StringUtils.replace("any", "", *, *)       = "any"
     * StringUtils.replace("any", *, *, 0)        = "any"
     * StringUtils.replace("abaa", "a", null, -1) = "abaa"
     * StringUtils.replace("abaa", "a", "", -1)   = "b"
     * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     *
     * @param text
     *            text to search and replace in, may be null
     * @param searchString
     *            the String to search for, may be null
     * @param replacement
     *            the String to replace it with, may be null
     * @return the text with any replacements processed, {@code null} if null String input
     */
    public static String replace(final String text, final String searchString, final String replacement) {
        if (stringIsBlank(text) || stringIsBlank(searchString) || replacement == null) {
            return text;
        }

        int start = 0;
        int end = text.indexOf(searchString, start);
        if (end == INDEX_NOT_FOUND) {
            return text;
        }

        final int replacedLength = searchString.length();

        final StringBuilder builder = new StringBuilder();
        while (end != INDEX_NOT_FOUND) {
            builder.append(text.substring(start, end)).append(replacement);
            start = end + replacedLength;
            end = text.indexOf(searchString, start);
        }
        builder.append(text.substring(start));
        return builder.toString();
    }

}
