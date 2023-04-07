package ru.stolexiy.leetcode

import kotlin.math.max



private fun maximalRectangleTLE(matrix: Array<CharArray>): Int {
    if (matrix.size == 0 || matrix.first().size == 0)
        return 0
    val prefixSum = calcPrefixSum(matrix)
    var max = 0
    for (topLeftX in matrix.indices) {
        for (topLeftY in matrix.first().indices) {
            val topLeft = topLeftX to topLeftY
            for (lowerRightX in topLeftX until matrix.size) {
                for (lowerRightY in topLeftY until matrix.first().size) {
                    val lowerRight = lowerRightX to lowerRightY
                    val rectSum = getRectSum(prefixSum, topLeft, lowerRight)
                    if (rectSum == (lowerRightY - topLeftY + 1) * (lowerRightX - topLeftX + 1))
                        max = max(max, rectSum)
                }
            }
        }
    }
    return max
}

private fun getRectSum(prefixSum: Array<IntArray>, topLeft: Pair<Int, Int>, lowerRight: Pair<Int, Int>): Int {
    val sum = prefixSum[lowerRight.first][lowerRight.second]
    val leftSum = prefixSum.get(lowerRight.first).getOrNull(topLeft.second - 1) ?: 0
    val topSum = prefixSum.getOrNull(topLeft.first - 1)?.get(lowerRight.second) ?: 0
    val topLeftSum = prefixSum
        .getOrNull(topLeft.first - 1)
        ?.getOrNull(topLeft.second - 1) ?: 0
    return if (topLeft.first == 0) {
        sum - leftSum
    } else if (topLeft.second == 0) {
        sum - topSum
    } else {
        sum - leftSum - topSum + topLeftSum
    }
}

private fun calcPrefixSum(matrix: Array<CharArray>): Array<IntArray> {
    val prefixSum = Array(matrix.size) { IntArray(matrix.first().size) { 0 } }
    for (row in matrix.indices) {
        for (column in matrix.first().indices) {
            prefixSum[row][column] = (prefixSum[row].getOrNull(column - 1) ?: 0) +
                    (prefixSum.getOrNull(row - 1)?.get(column) ?: 0) -
                    (prefixSum.getOrNull(row - 1)?.getOrNull(column - 1) ?: 0) +
                    matrix[row][column].toInt() - '0'.toInt()
        }
    }
    return prefixSum
}

/*
fun main() {
    val matrix = arrayOf(
        charArrayOf('1','0','1','0','0'),
        charArrayOf('1','0','1','1','1'),
        charArrayOf('1','1','1','1','1'),
        charArrayOf('1','0','0','1','0')
    )
    println(maximalRectangle(matrix))
}*/
