package ru.stolexiy.leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class MoveZeroesTest {

    @Test
    internal fun test1() {
        val testArray = intArrayOf(1,0,0,2,3,0,4,0,5)
        val zeroesCount = testArray.count { it == 0 }
        val expected = testArray.filter { it != 0 }.toList().apply {
            (this as MutableList) += (1..zeroesCount).map { 0 }.toList()
        }.toIntArray()
        moveZeroes(testArray)
        assertContentEquals(expected, testArray)
    }

    @Test
    internal fun `array without zeroes test`() {
        val testArray = intArrayOf(1,2,3,4,5)
        val expected = testArray.copyOf()
        moveZeroes(testArray)
        assertContentEquals(expected, testArray)
    }

    @Test
    internal fun `array with all zeroes test`() {
        val testArray = intArrayOf(0,0,0,0,0)
        val expected = testArray.copyOf()
        moveZeroes(testArray)
        assertContentEquals(expected, testArray)
    }

    @Test
    internal fun testSimpleMethod() {
        val testArray = intArrayOf(1,0,0,2,3,0,4,0,5)
        val zeroesCount = testArray.count { it == 0 }
        val expected = testArray.filter { it != 0 }.toList().apply {
            (this as MutableList) += (1..zeroesCount).map { 0 }.toList()
        }.toIntArray()
        moveZeroesSimple(testArray)
        assertContentEquals(expected, testArray)
    }
}