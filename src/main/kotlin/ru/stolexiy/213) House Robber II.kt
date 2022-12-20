package ru.stolexiy

import kotlin.math.max

fun robII(nums: IntArray): Int {
    return if (nums.size == 1)
        nums.first()
    else
        max(rob(nums, start = 0, end = nums.size - 2), rob(nums, start = 1, end = nums.size - 1))
}

private fun rob(nums: IntArray, start: Int = 0, end: Int = nums.size - 1): Int {
    var last = 0
    var preLast = 0
    var max = 0
    for (i in start..end) {
        val curReward = nums[i]
        val curMax = max(curReward + preLast, last)
        preLast = last
        last = curMax
        max = max(max, last)
    }
    return max
}

fun main() {
//    val nums = intArrayOf(2,3,2) //3
//    val nums = intArrayOf(1,2,3,1) //4
//    val nums = intArrayOf(1,2,3) //3
//    val nums = intArrayOf(1) //1
    val nums = intArrayOf(1,1) //1
    println(robII(nums))
}