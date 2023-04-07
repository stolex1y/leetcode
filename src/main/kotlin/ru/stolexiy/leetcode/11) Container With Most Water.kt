package ru.stolexiy.leetcode

import kotlin.math.max
import kotlin.math.min

private object Solution11 {
    fun maxArea(h: IntArray): Int {
        var left = 0
        var right = h.size - 1
        var max = 0
        while (left < right) {
            max = max(max, (right - left) * min(h[left], h[right]))
            if (h[left] < h[right]) {
                left++
            } else {
                right--
            }
        }
        return max
    }
}

fun main() {
    val h = intArrayOf(1,8,6,2,5,4,8,3,7)
    println(Solution11.maxArea(h))
}