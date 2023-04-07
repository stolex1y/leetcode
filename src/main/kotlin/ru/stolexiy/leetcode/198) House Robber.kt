package ru.stolexiy.leetcode

fun rob(nums: IntArray): Int {
    val maxLoots = IntArray(nums.size) { 0 }
    nums.forEachIndexed { i, num ->
        maxLoots[i] = num + (maxLoots.sliceArray(0 until i - 1).maxOrNull() ?: 0)
    }
    return maxLoots.maxOf { it }
}

fun main() {
    val nums = intArrayOf(1, 2, 3, 1)
    println(rob(nums))
}