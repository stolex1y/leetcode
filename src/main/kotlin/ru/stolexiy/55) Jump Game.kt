package ru.stolexiy

import kotlin.math.max

fun canJump(nums: IntArray): Boolean {
    if (nums.isEmpty())
        return true
    var max = 0
    nums.forEachIndexed { i, num ->
        if (max >= i) {
            max = max(max, i + num)
        } else {
            return false
        }
        if (max >= nums.size - 1)
            return true
    }
    return false
}