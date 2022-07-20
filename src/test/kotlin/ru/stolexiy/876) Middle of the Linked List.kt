package ru.stolexiy

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

class MiddleNodeTest {

    private fun generateLinkedList(listSize: Int): ListNode? {
        if (listSize == 0)
            return null
        val head = ListNode(1)
        var curr = head
        (2..listSize).forEach { value ->
            curr.next = ListNode(value)
            curr = curr.next!!
        }
        return head
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 5, 6, 10, 25, 100])
    internal fun test(listSize: Int) {
        val list = generateLinkedList(listSize)
        val expectedVal = when (listSize) {
            0 -> null
            1 -> 1
            else -> listSize / 2 + 1
        }
        assertEquals(expectedVal, middleNode(list)?.`val`, "Failed with list size = $listSize!")
    }
}