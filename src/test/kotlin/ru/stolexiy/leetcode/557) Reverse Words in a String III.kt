package ru.stolexiy.leetcode

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

class ReverseWordsIIITest {

    @ParameterizedTest
    @ValueSource(strings = ["", "aaa aaa", "asdf", "Hello, World!", "123 2222 432111 123321"])
    internal fun test(testString: String) {
        val actual = reverseWords(testString)
        val expected = testString.split(" ")
            .joinToString(separator = " ", transform = String::reversed)
        assertEquals(expected, actual, "Failed with test string: $testString")
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "aaa aaa", "asdf", "Hello, World!", "123 2222 432111 123321"])
    internal fun testWithoutLib(testString: String) {
        val actual = reverseWordsWithoutLib(testString)
        val expected = testString.split(" ")
            .joinToString(separator = " ", transform = String::reversed)
        assertEquals(expected, actual, "Failed with test string: $testString")
    }
}