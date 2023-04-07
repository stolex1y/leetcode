package ru.stolexiy.leetcode

import kotlin.math.pow

fun subsets(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<MutableList<Int>>()
    for (i in 0 until 2.0.pow(nums.size).toInt()) {
        val list = mutableListOf<Int>()
        for (bitN in nums.indices) {
            if (i.shr(bitN).and(1) == 1) {
                list += nums[bitN]
            }
        }
        result += list
    }
    return result
}

fun subsetsBacktracking(nums: IntArray): List<List<Int>> {
    val results = mutableListOf<List<Int>>()
    subsetsBacktrackingRecursive(nums, results, mutableListOf())
    return results
}

fun subsetsBacktrackingRecursive(nums: IntArray, results: MutableList<List<Int>>, prefix: MutableList<Int>, start: Int = 0) {
    results += mutableListOf<Int>().apply { addAll(prefix) }
    for (i in start until nums.size) {
        prefix += nums[i]
        subsetsBacktrackingRecursive(nums, results, prefix, i + 1)
        prefix -= nums[i]
    }
}

fun main() {
    val nums = intArrayOf(1, 2, 3)
    println(subsetsBacktracking(nums).joinToString("\n", "[", "]") { it.joinToString(" ", "[", "]") })
}