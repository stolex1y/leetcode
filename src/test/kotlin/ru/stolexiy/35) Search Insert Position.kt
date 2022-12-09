package ru.stolexiy

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SearchInsertPositionTest {
    private val testArray = intArrayOf(1, 2, 4, 6, 7, 9)

    @Test
    internal fun testExisted() {
        testArray.forEachIndexed { index, target ->
            assertEquals(index, searchInsert(testArray, target), "failed with $target")
        }
    }

    @ParameterizedTest
    @ValueSource(ints = intArrayOf(0, 3, 5, 8, 10))
    internal fun testNotExisted(target: Int) {
        val expected = testArray.filter {
            it < target
        }.size
        assertEquals(expected, searchInsert(testArray, target), "failed with $target")
    }
}