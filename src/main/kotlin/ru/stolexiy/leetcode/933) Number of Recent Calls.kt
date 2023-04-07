package ru.stolexiy.leetcode

import java.util.*

private class RecentCounter() {
    val queue = LinkedList<Int>()
    fun ping(t: Int): Int {
        queue.add(t)
        val smallest = t - 3000
        while (queue.peek() < smallest)
            queue.poll()
        return queue.size
    }
}