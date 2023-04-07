package ru.stolexiy.leetcode

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import ru.stolexiy.leetcode.lengthOfLongestSubstring
import ru.stolexiy.leetcode.lengthOfLongestSubstringN2
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