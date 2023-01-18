package ru.stolexiy

private fun subarraysDivByK(nums: IntArray, k: Int): Int {
    val prefixSums = mutableMapOf<Int, Int>().apply { put(0, 1) }
    var result = 0
    var prefixSum = 0
    nums.forEach { i ->
        prefixSum += i
        val remainder = (prefixSum % k).let {
            if (it < 0)
                k + it
            else
                it
        }
        result += prefixSums.getOrDefault(remainder, 0)
        prefixSums[remainder] = prefixSums.getOrDefault(remainder, 0) + 1
    }
    return result
}