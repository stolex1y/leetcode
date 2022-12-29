package ru.stolexiy

import java.util.LinkedList

fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    val queue = LinkedList<Int>()
    val results = IntArray(nums.size - k + 1) { 0 }
    for (i in nums.indices) {
        while (queue.isNotEmpty() && (i - queue.first()) >= k)
            queue.removeFirst()
        while (queue.isNotEmpty() && nums[queue.last()] < nums[i])
            queue.removeLast()
        queue.add(i)
        if (i >= k - 1)
            results[i - k + 1] = nums[queue.first()]
    }
    return results
}

fun main() {
    val nums = intArrayOf(1,3,-1,-3,5,3,6,7)
    val k = 3
    println(maxSlidingWindow(nums, k).joinToString(","))
}