package ru.stolexiy.leetcode

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertContentEquals

class TwoSumIITest {

    @ParameterizedTest
    @ValueSource(ints = [9,10,12,14,15,24,18,20,23,29,32])
    internal fun testWithSearch(target: Int) {
        val testArray = intArrayOf(2,7,8,10,12,15,17)
        val expected = intArrayOf(-1, -1)
        for (index1 in testArray.indices) {
            val i1 = testArray[index1]
            for (index2 in testArray.indices) {
                val i2 = testArray[index2]
                if (i1 + i2 == target && index1 != index2) {
                    expected[1] = index2 + 1
                    break
                }
            }
            if (expected[1] != -1) {
                expected[0] = index1 + 1
                break
            }
        }
        val actual = twoSumWithSearch(testArray, target)
        assertContentEquals(expected, actual, "Failed with find sum $target. " +
                "Expected ${testArray[expected[0] - 1]} + ${testArray[expected[1] - 1]}, " +
                "actual ${testArray[actual[0] - 1]} + ${testArray[actual[1] - 1]}")
    }

    @ParameterizedTest
    @ValueSource(ints = [9,10,12,14,15,24,18,20,23,29,32])
    internal fun testLinear(target: Int) {
        val testArray = intArrayOf(2,7,8,10,12,15,17)
        val expected = intArrayOf(-1, -1)
        for (index1 in testArray.indices) {
            val i1 = testArray[index1]
            for (index2 in testArray.indices) {
                val i2 = testArray[index2]
                if (i1 + i2 == target && index1 != index2) {
                    expected[1] = index2 + 1
                    break
                }
            }
            if (expected[1] != -1) {
                expected[0] = index1 + 1
                break
            }
        }
        val actual = twoSumLinear(testArray, target)
        assertContentEquals(expected, actual, "Failed with find sum $target. " +
                "Expected ${testArray[expected[0] - 1]} + ${testArray[expected[1] - 1]}, " +
                "actual ${testArray[actual[0] - 1]} + ${testArray[actual[1] - 1]}")
    }
}