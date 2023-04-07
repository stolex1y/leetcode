package ru.stolexiy.leetcode

fun searchRange(nums: IntArray, target: Int): IntArray {
    val firstPos = binarySearch(nums, target)
    if (firstPos == -1)
        return intArrayOf(-1, -1)
    val lastPos = binarySearch(nums, target, needFirst = false)
    return intArrayOf(firstPos, lastPos)
}

private fun binarySearch(nums: IntArray, target: Int, needFirst: Boolean = true): Int {
    if (nums.isEmpty())
        return -1
    var l = 0
    var r = nums.size - 1
    while (r - l > 1) {
        val m = (l + r) / 2
        if (needFirst) {
            if (target <= nums[m])
                r = m
            else
                l = m
        } else {
            if (target >= nums[m])
                l = m
            else
                r = m
        }
    }
    return if (nums[l] == target && nums[r] == target) {
        if (needFirst)
            l
        else
            r
    } else if (nums[l] == target)
        l
    else if (nums[r] == target)
        r
    else
        -1
}

fun main() {
    val nums = intArrayOf(5,7,7,8,8,10)
    val target = 8
    println(searchRange(nums, target).joinToString(","))
}