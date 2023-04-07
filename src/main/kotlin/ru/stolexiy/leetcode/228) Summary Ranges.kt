package ru.stolexiy.leetcode

private fun summaryRanges(nums: IntArray): List<String> {
    val result = mutableListOf<String>()
    if (nums.isEmpty())
        return result
    var cur = 1
    var start = 0
    while (cur < nums.size) {
        if (nums[cur] - nums[cur - 1] != 1) {
            result.add((nums[start]..nums[cur - 1]).str())
            start = cur
        }
        cur++
    }
    result.add((nums[start]..nums[cur - 1]).str())
    return result
}

private fun IntRange.str(): String {
    return if (first == last)
        "$first"
    else
        "$first->$last"
}