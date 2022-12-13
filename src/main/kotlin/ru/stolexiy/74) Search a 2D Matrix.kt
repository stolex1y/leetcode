package ru.stolexiy

fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    val row = matrix[searchRow(matrix, target)]
    var l = 0
    var r = row.size - 1
    while (r - l > 1) {
        val m = (l + r) / 2
        val mValue = row[m]
        if (mValue == target)
            return true
        else if (mValue > target)
            r = m
        else
            l = m
    }
    return row[r] == target || row[l] == target
}

fun searchRow(matrix: Array<IntArray>, target: Int): Int {
    var l = 0
    var r = matrix.size - 1
    while (r - l > 1) {
        val m = (l + r) / 2
        val firstInM = matrix[m].first()
        if (firstInM == target)
            return m
        else if (firstInM > target)
            r = m
        else
            l = m
    }
    return if (matrix[r].first() > target)
        l
    else
        r
}