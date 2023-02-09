package ru.stolexiy

import kotlin.math.max

private object Solution763 {
    fun partitionLabels(s: String): List<Int> {
        if (s.isEmpty())
            return listOf(0)
        val rangesMap = LinkedHashMap<Char, IntRange>()
        s.forEachIndexed { i, ch ->
            if (rangesMap.contains(ch))
                rangesMap[ch] = rangesMap[ch]!!.first..i
            else
                rangesMap[ch] = i..i
        }
        val results = mutableListOf<Int>()
        var cur = 0..0
        rangesMap.values.forEach { ithRange ->
            if (ithRange.first <= cur.last)
                cur = cur.first .. max(cur.last, ithRange.last)
            else {
                results.add(cur.last - cur.first + 1)
                cur = ithRange
            }
        }
        results.add(cur.last - cur.first + 1)
        return results
    }

    fun partitionLabelsII(s: String): List<Int> {
        val lastIdx = IntArray(26) { 0 }
        s.forEachIndexed { i, ch ->
            lastIdx[ch.arrIdx()] = i
        }
        var curRange = 0..0
        val results = mutableListOf<Int>()
        s.forEachIndexed { i, ch ->
            curRange = curRange.first .. max(curRange.last, lastIdx[ch.arrIdx()])
            if (curRange.last == i) {
                results.add(curRange.last - curRange.first + 1)
                curRange = i + 1 .. i + 1
            }
        }
        return results
    }

    private fun Char.arrIdx() = this.toInt() - 'a'.toInt()
}

fun main() {
    val string = "ababcbacadefegdehijhklij"
    println(Solution763.partitionLabels(string).joinToString(","))
}
