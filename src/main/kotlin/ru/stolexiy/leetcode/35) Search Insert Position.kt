package ru.stolexiy.leetcode

fun searchInsert(nums: IntArray, target: Int): Int {
    var start = 0
    var end = nums.size - 1
    var mid: Int
    while (end > start) {
        mid = (end + start) / 2
        if (nums[mid] == target)
            return mid
        else if (target > nums[mid])
            start = mid + 1
        else
            end = mid - 1
    }
    return if (target <= nums[start])
        start
    else
        start + 1
}