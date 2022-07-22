package ru.stolexiy

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.Integer.max
import kotlin.math.exp
import kotlin.test.assertEquals

class LengthOfLongestSubstringTest {

    @ParameterizedTest
    @ValueSource(strings = ["abba", "aaa", "", "abcdef", "abcdeb1234", "1234567899abcdefghijkl", " "])
    internal fun test1(testString: String) {
        val actual = lengthOfLongestSubstring(testString)
        val expected = lengthOfLongestSubstringN2(testString)
        assertEquals(expected, actual, "Failed with test string: $testString!")
    }
}