package ru.stolexiy.leetcode

import kotlin.math.absoluteValue


fun sortedSquares(nums: IntArray): IntArray {
    val result = IntArray(nums.size)

    if (nums.isEmpty())
        return result

    val zeroIndex = nums.searchInsert(0)
    var currPositive = zeroIndex
    var currNegative = zeroIndex - 1

    var curr: Int
    for (i in nums.indices) {
        curr = if (currNegative < 0)
            nums[currPositive++]
        else if (currPositive == nums.size)
            nums[currNegative--]
        else if (nums[currPositive] <= nums[currNegative].absoluteValue)
            nums[currPositive++]
        else
            nums[currNegative--]
        result[i] = curr * curr
    }
    return result
}

private fun IntArray.searchInsert(target: Int): Int {
    var start = 0
    var end = size - 1
    var mid: Int
    while (end > start) {
        mid = (end + start) / 2
        if (this[mid] == target)
            return mid
        else if (target > this[mid])
            start = mid + 1
        else
            end = mid - 1
    }
    return if (target <= this[start])
        start
    else
        start + 1
}