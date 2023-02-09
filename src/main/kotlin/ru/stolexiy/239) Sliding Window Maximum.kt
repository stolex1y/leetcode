package ru.stolexiy

import java.util.*

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

fun maxSlidingWindow2(nums: IntArray, k: Int): IntArray {
    val map = TreeMap<Int, Int>()
    val results = IntArray(nums.size - k + 1) { Int.MIN_VALUE }
    for (i in 0 until k) {
        val n = nums[i]
        map[n] = map.getOrElse(n) { 0 } + 1
    }
    results[0] = map.lastKey()
    for (i in k until nums.size) {
        if (map[nums[i - k]] == 1)
            map.remove(nums[i - k])
        else
            map[nums[i - k]] = map[nums[i - k]]!! - 1

        val n = nums[i]
        map[n] = map.getOrElse(n) { 0 } + 1
        results[i - k + 1] = map.lastKey()
    }
    return results
}

fun main() {
    val nums = intArrayOf(1,3,-1,-3,5,3,6,7)
    val k = 3
    println(maxSlidingWindow(nums, k).joinToString(","))
}