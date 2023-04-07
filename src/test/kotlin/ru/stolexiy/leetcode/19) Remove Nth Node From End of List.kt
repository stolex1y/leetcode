package ru.stolexiy.leetcode

import org.junit.jupiter.api.Test
import ru.stolexiy.ListNode.Companion.generateLinkedList
import kotlin.test.assertContentEquals

class RemoveNthNodeFromEndTest {

    private fun removeNthFromEndList(items: Iterable<Int>, remove: Int): List<Int>? {
        val result = items.filterIndexed { index, _ ->
            index != items.count() - remove
        }.toList()
        return result.ifEmpty { null }
    }

    @Test
    internal fun test1() {
        val items = 1..5
        val list = generateLinkedList(items)
        val remove = 2
        val expected = removeNthFromEndList(items, remove)
        val actual = removeNthFromEnd(list, remove)?.toList()
        assertContentEquals(expected, actual)
    }

    @Test
    internal fun test2() {
        val items = 1..2
        val list = generateLinkedList(items)
        val remove = 1
        val expected = removeNthFromEndList(items, remove)
        val actual = removeNthFromEnd(list, remove)?.toList()
        assertContentEquals(expected, actual)
    }

    @Test
    internal fun test3() {
        val items = 1..1
        val list = generateLinkedList(items)
        val remove = 1
        val expected = removeNthFromEndList(items, remove)
        val actual = removeNthFromEnd(list, remove)?.toList()
        assertContentEquals(expected, actual)
    }
}