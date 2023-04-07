package ru.stolexiy.leetcode

import org.junit.jupiter.api.Test
import kotlin.math.absoluteValue
import kotlin.test.assertContentEquals

class SortedSquaresTest {
    private val testArray = intArrayOf(-12,-10,-4,-1,1,3,10)

    @Test
    internal fun test() {
        val expected = testArray.map {
            it * it
        }.sorted().toIntArray()
        assertContentEquals(expected, sortedSquares(testArray))
    }

    @Test
    internal fun positiveArrTest() {
        val testArray = this.testArray.map {
            it.absoluteValue
        }.sorted().toIntArray()
        val expected = testArray.map {
            it * it
        }.sorted().toIntArray()
        assertContentEquals(expected, sortedSquares(testArray))
    }

    @Test
    internal fun negativeArrTest() {
        val testArray = this.testArray.map {
            -it.absoluteValue
        }.sorted().toIntArray()
        val expected = testArray.map {
            it * it
        }.sorted().toIntArray()
        assertContentEquals(expected, sortedSquares(testArray))
    }
}