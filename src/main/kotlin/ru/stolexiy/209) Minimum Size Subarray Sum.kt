package ru.stolexiy

import kotlin.math.min

private fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var sum = 0
    var left = 0
    var min = Int.MAX_VALUE
    nums.forEachIndexed { right, num ->
        sum += num
        while (sum - nums[left] >= target) {
            sum -= nums[left++]
        }
        if (sum >= target)
            min = min(min, right - left + 1)
    }
    return if (min == Int.MAX_VALUE)
        0
    else
        min
}