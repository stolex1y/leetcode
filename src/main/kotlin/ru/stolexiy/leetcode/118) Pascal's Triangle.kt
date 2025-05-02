package ru.stolexiy.leetcode

fun generatePascalsTriangle(numRows: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    if (numRows == 0)
        return result

    val firstRow = mutableListOf<Int>()
    firstRow += 1
    result += firstRow

    for (i in 2..numRows) {
        val prevRow = result.last()
        val row = mutableListOf<Int>()
        result += row
        row += 1
        for (y in 1 until (i - 1)) {
            row += prevRow[y - 1] + prevRow[y]
        }
        row += 1
    }
    return result
}