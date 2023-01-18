package ru.stolexiy

import kotlin.math.max

private fun longestSubarray(nums: IntArray): Int {
    var max = 0
    var prevLen = 0
    var curLen = 0
    nums.forEach { i ->
        if (i == 1) {
            curLen++
            max = max(max, prevLen + curLen)
        } else {
            prevLen = curLen
            curLen = 0
        }
    }
    return if (max == nums.size)
        max - 1
    else
        max
}

private fun longestSubarray2(nums: IntArray): Int {
    val maxEndings = MutableList(nums.size) { 0 }
    val maxStartings = MutableList(nums.size) { 0 }
    for (i in nums.indices) {
        if (i == 0)
            maxEndings[i] = nums[i]
        else if (nums[i] == 0)
            maxEndings[i] = 0
        else
            maxEndings[i] = maxEndings[i - 1] + 1
    }
    for (i in nums.size - 1 downTo 0) {
        if (i == nums.size - 1)
            maxStartings[i] = nums[i]
        else if (nums[i] == 0)
            maxStartings[i] = 0
        else
            maxStartings[i] = maxStartings[i + 1] + 1
    }
    var max = 0
    for (i in nums.indices) {
        max = max(
            max,
            maxEndings.getOrElse(i - 1) { 0 } + maxStartings.getOrElse(i + 1) { 0 }
        )
    }
    return max
}
