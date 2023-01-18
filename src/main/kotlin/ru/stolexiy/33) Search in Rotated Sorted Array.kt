package ru.stolexiy

import kotlin.math.max

private fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1
    while (right > left) {
        val mid = (left + right) / 2
        if (target == nums[mid])
            return mid
        if (nums[mid] > nums[left]) {
            if (target < nums[mid] && target >= nums[left])
                right = mid - 1
            else
                left = mid + 1
        } else {
            if (target >= nums[mid + 1] && target <= nums[right])
                left = mid + 1
            else
                right = mid - 1
        }
    }
    return if (target == nums[left])
        left
    else
        -1
}

private fun search2(nums: IntArray, target: Int): Int {
    val rotateIndex = rotateIndex(nums)
    return max(search2(nums, 0, rotateIndex - 1, target), search2(nums, rotateIndex, nums.size - 1, target))
}

private fun search2(nums: IntArray, start: Int, end: Int, target: Int): Int {
    if (end < start)
        return -1
    var left = start
    var right = end
    while (right > left) {
        val mid = (left + right) / 2
        if (target == nums[mid])
            return mid
        else if (target < nums[mid])
            right = mid - 1
        else
            left = mid + 1
    }
    return if (target == nums[left])
        left
    else
        -1
}

private fun rotateIndex(nums: IntArray): Int {
    var left = 0
    var right = nums.size - 1
    while (right > left) {
        val mid = (left + right) / 2
        if (nums[mid + 1] < nums[mid])
            return mid + 1
        else if (nums[left] > nums[mid])
            right = mid
        else
            left = mid + 1
    }
    return 0
}