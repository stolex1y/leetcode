package ru.stolexiy.leetcode

import kotlin.math.max

private fun longestOnes(nums: IntArray, k: Int): Int {
    var zeroesCount = 0
    var left = 0
    var max = 0
    nums.forEachIndexed { right, num ->
        if (num == 0) {
            zeroesCount++
            while (zeroesCount > k && left <= right) {
                if (nums[left] == 0)
                    zeroesCount--
                left++
            }
        }
        max = max(max, right - left + 1)
    }
    return max
}