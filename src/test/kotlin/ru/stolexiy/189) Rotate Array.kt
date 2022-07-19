package ru.stolexiy

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class RotateArrayTest {

    @Test
    internal fun rotateInPlaceArray() {
        val testArray = intArrayOf(1,2,3,4,5,6,7)
        (0..testArray.size * 2).forEach { k ->
            val expected = IntArray(testArray.size)
            testArray.forEachIndexed { index, _ ->
                expected[(index + k) % testArray.size] = testArray[index]
            }
            val result = testArray.copyOf()
            rotateInPlace(result, k)
            assertContentEquals(expected, result, "k = $k")
        }
    }

    @Test
    internal fun rotateReversing() {
        val testArray = intArrayOf(1,2,3,4,5,6,7)
        (0..testArray.size * 2).forEach { k ->
            val expected = IntArray(testArray.size)
            testArray.forEachIndexed { index, _ ->
                expected[(index + k) % testArray.size] = testArray[index]
            }
            val result = testArray.copyOf()
            rotateReversing(result, k)
            assertContentEquals(expected, result, "k = $k")
        }
    }

    @Test
    internal fun rotateArrayTest() {
        val testArray = intArrayOf(1,2,3,4,5,6,7)
        (0..testArray.size * 2).forEach { k ->
            val expected = IntArray(testArray.size)
            testArray.forEachIndexed { index, _ ->
                expected[(index + k) % testArray.size] = testArray[index]
            }
            val result = testArray.copyOf()
            rotate(result, k)
            assertContentEquals(expected, result, "k = $k")
        }
    }
}