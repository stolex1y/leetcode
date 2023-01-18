package ru.stolexiy

fun findPeakElement(nums: IntArray): Int {
    var l = 0
    var r = nums.size - 1
    while (r > l) {
        val m = (l + r) / 2
        if (nums[m + 1] > nums[m])
            l = m + 1
        else
            r = m
    }
    return l
}