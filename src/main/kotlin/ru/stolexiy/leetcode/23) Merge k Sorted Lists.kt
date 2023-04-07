package ru.stolexiy.leetcode

import ru.stolexiy.ListNode
import java.util.PriorityQueue

fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    val minHeap = PriorityQueue<ListNode> {
            p1, p2 -> p1.`val`.compareTo(p2.`val`)
    }
    lists.forEach { list ->
        list?.let { minHeap.add(it) }
    }
    var head: ListNode? = null
    var currNode: ListNode? = null
    while (minHeap.isNotEmpty()) {
        val minNode = minHeap.remove()
        if (head == null) {
            head = minNode
            currNode = minNode
        } else {
            currNode?.next = minNode
            currNode = currNode?.next
        }
        minNode.next?.let {
            minHeap.add(it)
        }
    }
    return head
}

fun main() {
    val lists = arrayOf(
        ListNode.generateLinkedList(listOf(1, 4, 5)),
        ListNode.generateLinkedList(listOf(1, 3, 4)),
        ListNode.generateLinkedList(listOf(2, 6)),
    )
    println(mergeKLists(lists)?.toList()?.joinToString(",", "[", "]") ?: "[]")
}