package ru.stolexiy.leetcode

fun matrixReshape(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
    val m = mat.size
    val n = mat.first().size
    if (m * n != r * c)
        return mat
    val result = Array(r) { IntArray(c) }
    mat.forEachIndexed { i, row ->
        row.forEachIndexed { y, el ->
            val elAbsIndex = i * n + y
            result[elAbsIndex / c][elAbsIndex % c] = el
        }
    }
    return result
}