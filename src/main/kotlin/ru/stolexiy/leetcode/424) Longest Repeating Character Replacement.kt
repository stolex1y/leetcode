package ru.stolexiy.leetcode

import kotlin.math.max

private object Solution424 {
    fun characterReplacement2(s: String, k: Int): Int {
        return s.toCharArray()
            .distinct()
            .toList()
            .map { longestOneLetterSubstring(s, it, k) }
            .maxOrNull() ?: 0
    }

    private fun longestOneLetterSubstring(s: String, letter: Char, k: Int): Int {
        var start = 0
        var replaced = 0
        var max = 0
        s.forEachIndexed { i, ch ->
            if (ch != letter)
                replaced++
            while (replaced > k) {
                if (s[start] != letter)
                    replaced--
                start++
            }
            max = max(max, i - start + 1)
        }
        return max
    }

    fun characterReplacement(s: String, k: Int): Int {
        val charFreq = IntArray(26) { 0 }
        var start = 0
        var maxFreq = 0
        var maxLen = 0
        for (end in 0 until s.length) {
            val windowSize = end - start + 1
            val chIdx = s[end].getArrIdx()
            charFreq[chIdx]++
            maxFreq = max(maxFreq, charFreq[chIdx])
            if (windowSize - maxFreq > k) {
                charFreq[s[start].getArrIdx()]--
                start++
            } else {
                maxLen = end - start + 1
            }
        }
        return maxLen
    }

    private fun Char.getArrIdx() = this.toInt() - 'A'.toInt()
}