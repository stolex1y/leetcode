package ru.stolexiy

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertContentEquals

class ReverseStringTest {

    @ParameterizedTest
    @ValueSource(strings = ["hello", "123456", "a", "aaa", ""])
    internal fun test(testString: String) {
        val actual = testString.toCharArray()
        val expected = actual.copyOf().reversedArray()
        reverseString(actual)
        assertContentEquals(expected, actual, "Failed with source string: $testString")
    }

    @ParameterizedTest
    @ValueSource(strings = ["hello", "123456", "a", "aaa", ""])
    internal fun testWithTwoPointers(testString: String) {
        val actual = testString.toCharArray()
        val expected = actual.copyOf().reversedArray()
        reverseStringWithTwoPointers(actual)
        assertContentEquals(expected, actual, "Failed with source string: $testString")
    }
}