package ru.stolexiy.leetcode

private fun rotate(matrix: Array<IntArray>): Unit {
    for (row in 0 until matrix.size / 2) {
        for (column in row until matrix.first().size - 1) {
            rotate(row to column, matrix)
        }
    }
}

private fun rotate(start: Pair<Int, Int>, matrix: Array<IntArray>) {
    var cur = start
    var next = start.rotate90().transfer(matrix.size - 1, 0)
    var nextVal = matrix.get(next)
    matrix.set(next, matrix.get(cur))
    cur = next
    while (cur != start) {
        next = cur.rotate90().transfer(matrix.size - 1, 0)
        val temp = matrix.get(next)
        matrix.set(next, nextVal)
        nextVal = temp
        cur = next
    }
}

private fun Array<IntArray>.set(p: Pair<Int, Int>, value: Int) = this[p.first].set(p.second, value)
private fun Array<IntArray>.get(p: Pair<Int, Int>) = this[p.first][p.second]
private fun Pair<Int, Int>.rotate90() = -this.second to this.first
private fun Pair<Int, Int>.transfer(dx: Int, dy: Int) = this.first + dx to this.second + dy

/*
private fun rotatedAndTransferCoord(source: Pair<Int, Int>, matrixW: Int): Pair<Int, Int> {
    return (source.first) to ((matrixW - 1) - source.second)
}*/
