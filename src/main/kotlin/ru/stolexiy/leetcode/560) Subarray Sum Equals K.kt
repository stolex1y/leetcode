package ru.stolexiy.leetcode

private fun subarraySum(nums: IntArray, k: Int): Int {
    var result = 0
    val prefixSum = mutableMapOf<Int, Int>().apply { put(0, 1) }
    var sum = 0
    nums.forEach { n ->
        sum += n
        result += prefixSum.getOrDefault(sum - k, 0)
        prefixSum[sum] = prefixSum.getOrDefault(sum, 0) + 1
    }
    return result
}