package ru.stolexiy

import java.util.PriorityQueue

fun findRelativeRanks(score: IntArray): Array<String> {
    val maxHeap = MaxHeap(score.mapIndexed { index, i -> i to index }.toTypedArray<Pair<Int, Int?>>())
    val result = Array(score.size) { "" }
    var place = 1
    for (i in score.indices) {
        result[maxHeap.extractMax()!!] = getRank(place++)
    }
    return result
}

fun findRelativeRanks2(score: IntArray): Array<String> {
    val maxHeap = PriorityQueue<Pair<Int, Int>> { p1, p2 -> p2.first.compareTo(p1.first) }
    maxHeap.addAll(score.mapIndexed { index, i -> i to index }.toTypedArray<Pair<Int, Int>>())
    val result = Array(score.size) { "" }
    var place = 1
    for (i in score.indices) {
        result[maxHeap.poll().second] = getRank(place++)
    }
    return result
}

private fun getRank(i: Int): String {
    return when (i) {
        1 -> "Gold Medal"
        2 -> "Silver Medal"
        3 -> "Bronze Medal"
        else -> "$i"
    }
}

fun main() {
    val scores = intArrayOf(1,2,3,4,5)
    println(findRelativeRanks2(scores).joinToString(","))
}