package ru.stolexiy.leetcode

private fun isReflected(points: Array<IntArray>): Boolean {
    var xMin = Int.MAX_VALUE
    var xMax = Int.MIN_VALUE
    val pairs = points.map { it[0] to it[1] }
    val set = mutableSetOf<Pair<Int, Int>>().apply { addAll(pairs as MutableList) }
    pairs.forEach { p ->
        if (p.first < xMin)
            xMin = p.first
        if (p.first > xMax)
            xMax = p.first
    }
    val sum = (xMin + xMax)
    pairs.forEach { p ->
        if (!set.contains(Pair(sum - p.first, p.second)))
            return false
    }
    return true
}

fun main() {
    val points = arrayOf(
        intArrayOf(1,2),
        intArrayOf(2,5),
        intArrayOf(-6,5),
        intArrayOf(-5,2)
    )
    println(isReflected(points))
}