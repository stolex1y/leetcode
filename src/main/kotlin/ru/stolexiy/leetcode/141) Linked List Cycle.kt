package ru.stolexiy.leetcode

import ru.stolexiy.ListNode

fun hasCycle(head: ListNode?): Boolean {
    val nodes = mutableSetOf<ListNode>()
    var next = head
    while (next != null) {
        if (nodes.contains(next))
            return true
        nodes += next
        next = next.next
    }
    return false
}