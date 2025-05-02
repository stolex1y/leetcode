package ru.stolexiy.leetcode

fun canCrossRecursive(stones: IntArray): Boolean {
    return getPathLenRecursive(stones, 1, 1, stones.last()) != -1
}

private fun getPathLenRecursive(stones: IntArray, start: Int, k: Int, target: Int): Int {
    if (start == target)
        return 0
    else if (k == 0 || !stones.contains(start))
        return -1
    else {
        for (jump in k - 1..k + 1) {
            val pathLen = getPathLenRecursive(stones, start + jump, jump, target)
            if (pathLen != -1)
                return jump + pathLen
        }
        return -1
    }
}

fun canCross(stones: IntArray): Boolean {
    val paths = mutableMapOf<Int, MutableSet<Int>>()
    for (stone in stones) {
        paths += stone to mutableSetOf()
    }

    for (i in stones.size - 2 downTo 0) {
        val start = stones[i]
        paths[start]!!.add(stones.last() - start)
        for (y in i + 1 until stones.size) {
            val target = stones[y]
            val jump = target - start
            for (k in jump - 1..jump + 1) {
                if (paths[start + jump]?.contains(k) == true)
                    paths[start]!!.add(jump)
            }
        }
    }
    return paths[stones.first()]!!.contains(1)
}

fun main() {
    val tests = mutableListOf<IntArray>()
    val expected = mutableListOf<Boolean>()
    tests.add(intArrayOf(0,1,3,5,6,8,12,17)); expected.add(true)
    tests.add(intArrayOf(0,1,2,3,4,8,9,11)); expected.add(false)
    tests.add(intArrayOf(0,1,2,3,4,5,7,10,12,14)); expected.add(true)
    tests.add(intArrayOf(0,1)); expected.add(true)
    tests.add(intArrayOf(0,2)); expected.add(false)
    for (testIdx in tests.indices) {
        val test = tests[testIdx]
        val expectedResult = expected[testIdx]
        println("expected $expectedResult, but actually ${canCross(test)}")
    }
}