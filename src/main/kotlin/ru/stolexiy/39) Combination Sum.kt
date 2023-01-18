package ru.stolexiy

import java.util.*

private object Solution39 {
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        return mutableListOf<List<Int>>().apply {
            combinationSum(
                candidates,
                target.toLong(),
                results = this
            )
        }
    }

    fun combinationSum(
        candidates: IntArray,
        target: Long,
        start: Int = 0,
        temp: Pair<LinkedList<Int>, Long> = LinkedList<Int>() to 0,
        results: MutableList<List<Int>>
    ) {
        if (temp.second == target) {
            results.add(mutableListOf<Int>().apply { addAll(temp.first) })
        } else if (temp.second < target) {
            for (i in start until candidates.size) {
                val newSum: Long = temp.second + candidates[i].toLong()
                if (newSum <= target) {
                    temp.first.addLast(candidates[i])
                    val newTemp = temp.first to newSum
                    combinationSum(candidates, target, i, newTemp, results)
                    temp.first.removeLast()
                }
            }
        }
    }
}