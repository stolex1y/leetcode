package ru.stolexiy.leetcode

private class Solution {
    fun generateMatrix(n: Int): Array<IntArray> {
        val matrix = Array(n) {
            IntArray(n) { 0 }
        }
        var curPoint = 0 to -1
        var curDir = Direction.RIGHT
        var i = 1
        while (true) {
            val start = i
            while (matrix.get(curPoint.nextByDir(curDir)) == 0) {
                curPoint = curPoint.nextByDir(curDir)
                matrix.set(curPoint, i++)
            }
            if (i == start)
                break
            curDir = curDir.next()
        }
        return matrix
    }

    private fun Pair<Int, Int>.nextByDir(dir: Direction) = first + dir.di to second + dir.dj
    private fun Array<IntArray>.get(p: Pair<Int, Int>) = getOrNull(p.first)?.getOrNull(p.second)
    private fun Array<IntArray>.set(p: Pair<Int, Int>, value: Int) = this[p.first].set(p.second, value)

    private enum class Direction(val di: Int, val dj: Int) {
        UP(-1,0),
        DOWN(1,0),
        RIGHT(0,1),
        LEFT(0,-1);

        fun next(): Direction {
            return when (this) {
                UP -> RIGHT
                DOWN -> LEFT
                RIGHT -> DOWN
                LEFT -> UP
            }
        }
    }
}