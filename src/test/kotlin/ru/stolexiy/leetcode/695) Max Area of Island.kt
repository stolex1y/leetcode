package ru.stolexiy.leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MaxAreaOfIslandTest {

    @Test
    internal fun test1() {
        val testGrid: Array<IntArray> = arrayOf(
            intArrayOf(0,0,1,0,0,0,0,1,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0),
            intArrayOf(0,1,1,0,1,0,0,0,0,0,0,0,0),
            intArrayOf(0,1,0,0,1,1,0,0,1,0,1,0,0),
            intArrayOf(0,1,0,0,1,1,0,0,1,1,1,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0,1,0,0),
            intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,1,1,0,0,0,0)
        )
        val expected = 6
        val actual = maxAreaOfIsland(testGrid)
        assertEquals(expected, actual,
            "Failed with test grid: \n" +
                    testGrid.toString() + ".\n" +
                    "Expected: $expected, but actual $actual."
        )
    }

    @Test
    internal fun test2() {
        val testGrid: Array<IntArray> = arrayOf(
            intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0),
        )
        val expected = 0
        val actual = maxAreaOfIsland(testGrid)
        assertEquals(expected, actual,
            "Failed with test grid: \n" +
                    testGrid.toString() + ".\n" +
                    "Expected: $expected, but actual $actual."
        )
    }

    @Test
    internal fun test3() {
        //[[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
        val testGrid: Array<IntArray> = arrayOf(
            intArrayOf(1,1,0,0,0),
            intArrayOf(1,1,0,0,0),
            intArrayOf(0,0,0,1,1),
            intArrayOf(0,0,0,1,1),
        )
        val expected = 4
        val actual = maxAreaOfIsland(testGrid)
        assertEquals(expected, actual,
            "Failed with test grid: \n" +
                    testGrid.toString() + ".\n" +
                    "Expected: $expected, but actual $actual."
        )
    }
}