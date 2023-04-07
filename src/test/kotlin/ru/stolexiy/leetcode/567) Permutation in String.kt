package ru.stolexiy.leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PermutationInStringTest {

    @Test
    internal fun test1() {
        val s1 = "ab"
        val s2 = "eidbaooo"
        val expected = true
        val actual = checkInclusion(s1, s2)
        assertEquals(expected, actual)
    }

    @Test
    internal fun test2() {
        val s1 = "ab"
        val s2 = "eidboaoo"
        val expected = false
        val actual = checkInclusion(s1, s2)
        assertEquals(expected, actual)
    }

    @Test
    internal fun test3() {
        val s1 = "12345"
        val s2 = "1ad2d345assdecsddcdd12"
        val expected = false
        val actual = checkInclusion(s1, s2)
        assertEquals(expected, actual)
    }

    @Test
    internal fun test4() {
        val s1 = "adc"
        val s2 = "dcda"
        val expected = true
        val actual = checkInclusion(s1, s2)
        assertEquals(expected, actual)
    }
}