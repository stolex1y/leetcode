package ru.stolexiy.leetcode

import kotlin.math.ceil
import kotlin.math.min
import kotlin.math.sqrt

private fun numSquares(n: Int): Int {
    val perfectSquares = IntArray(sqrt(n.toDouble()).toInt()) { (it + 1) * (it + 1) }
    val leastNumbers = IntArray(n + 2) { it }
    for (i in 1..n) {
        for (perfectSquare in perfectSquares) {
            if (perfectSquare > i)
                break
            else
                leastNumbers[i] = min(leastNumbers[i], 1 + (leastNumbers[i - perfectSquare]))
        }
    }
    return leastNumbers[n]
}

private fun numSquares2(n: Int): Int {
    val leastNumbers = IntArray(n + 2) { it }
    for (i in 1..n) {
        if (i.isPerfectSquare()) {
            leastNumbers[i] = 1
        } else {
            var min = leastNumbers[i]
            val mid = ceil(i / 2.0).toInt()
            for (k in 1 .. mid) {
                min = min(leastNumbers[k] + leastNumbers[i - k], min)
            }
            leastNumbers[i] = min
        }
    }
    return leastNumbers[n]
}

private fun Int.isPerfectSquare(): Boolean {
    val sqrt = sqrt(this.toDouble()).toInt()
    return sqrt * sqrt == this
}