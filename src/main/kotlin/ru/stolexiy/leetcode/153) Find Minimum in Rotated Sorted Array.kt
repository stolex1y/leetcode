package ru.stolexiy.leetcode

private fun findMin(nums: IntArray): Int {
    var left = 0
    var right = nums.size - 1
    while (right > left) {
        val mid = (left + right) / 2
        if (nums[mid + 1] < nums[mid])
            return nums[mid + 1]
        else if (nums[left] > nums[mid])
            right = mid
        else
            left = mid + 1
    }
    return nums[0]
}