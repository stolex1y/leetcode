package ru.stolexiy.leetcode

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import ru.stolexiy.ListNode.Companion.generateLinkedList
import kotlin.test.assertEquals

class MiddleNodeTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 5, 6, 10, 25, 100])
    internal fun test(listSize: Int) {
        val list = generateLinkedList(1..listSize)
        val expectedVal = when (listSize) {
            0 -> null
            1 -> 1
            else -> listSize / 2 + 1
        }
        assertEquals(expectedVal, middleNode(list)?.`val`, "Failed with list size = $listSize!")
    }
}