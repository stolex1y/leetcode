package ru.stolexiy.leetcode

import kotlin.math.max

private fun merge(intervals: Array<IntArray>): Array<IntArray> {
    if (intervals.isEmpty())
        return emptyArray()
    intervals.sortBy { it[0] }
    val result = mutableListOf(intervals.first())
    for (i in 1 until intervals.size) {
        val curInterval = intervals[i]
        if (curInterval[0] <= result.last()[1]) {
            result[result.size - 1][1] = max(curInterval[1], result.last()[1])
        } else {
            result.add(curInterval)
        }
    }
    return result.toTypedArray()
}