package ru.stolexiy.leetcode

import kotlin.math.max

fun maxSubArrayBad(nums: IntArray): Int {
    return maxSubarrayRecursive(nums, 0, nums.size - 1).sum
}

fun maxSubarrayRecursive(nums: IntArray, l: Int, r: Int): Subarr {
    if (l == r)
        return Subarr(l, l, nums[l])
    val m = (l + r) / 2

    val leftMaxSubarr = maxSubarrayRecursive(nums, l, m)
    val rightMaxSubarr = maxSubarrayRecursive(nums, m + 1, r)
    val midMaxSubarr = maxMidSubarray(nums, l, r)
    return if (leftMaxSubarr.sum >= rightMaxSubarr.sum &&
        leftMaxSubarr.sum >= midMaxSubarr.sum)
        leftMaxSubarr
    else if (rightMaxSubarr.sum >= leftMaxSubarr.sum &&
        rightMaxSubarr.sum >= midMaxSubarr.sum)
        rightMaxSubarr
    else
        midMaxSubarr
}

fun maxMidSubarray(nums: IntArray, l: Int, r: Int): Subarr {
    val m = (l + r) / 2
    var leftSumMax = Int.MIN_VALUE
    var left = m
    var sum = 0
    for (i in m downTo l) {
        sum += nums[i]
        if (sum > leftSumMax) {
            left = i
            leftSumMax = sum
        }
    }
    var rightSumMax = 0
    var right = m
    sum = 0
    for (i in (m + 1)..r) {
        sum += nums[i]
        if (sum > rightSumMax) {
            right = i
            rightSumMax = sum
        }
    }
    return Subarr(left, right, leftSumMax + rightSumMax)
}

data class Subarr(val left: Int, val right: Int, val sum: Int)

fun maxSubArrayLinear(nums: IntArray): Int {
    if (nums.isEmpty())
        return 0
    val maxSubarr = IntArray(nums.size)
    maxSubarr[0] = nums[0]
    var maxSum = maxSubarr[0]
    for (i in 1 until nums.size) {
        if (maxSubarr[i - 1] > 0)
            maxSubarr[i] = nums[i] + maxSubarr[i - 1]
        else
            maxSubarr[i] = nums[i]
        maxSum = max(maxSum, maxSubarr[i])
    }
    return maxSum
}
