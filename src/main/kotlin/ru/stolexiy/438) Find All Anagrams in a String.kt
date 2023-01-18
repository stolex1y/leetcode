package ru.stolexiy

import kotlin.math.min

private object Solution438 {
    fun findAnagrams(s: String, p: String): List<Int> {
        val windowCharFreq = IntArray(26) { 0 }
        val pCharFreq = IntArray(26) { 0 }
        val results = mutableListOf<Int>()
        calcCharFreq(s, windowCharFreq, start = 0, end = p.length - 1)
        calcCharFreq(p, pCharFreq)
        if (isAnagrams(windowCharFreq, pCharFreq))
            results.add(0)
        var i: Int = 1
        while (i < s.length - p.length + 1) {
            windowCharFreq[s[i - 1].getIndex()]--
            windowCharFreq[s[i + p.length - 1].getIndex()]++
            if (isAnagrams(windowCharFreq, pCharFreq))
                results.add(i)
            i++
        }
        return results
    }

    fun calcCharFreq(s: String, charFreq: IntArray, start: Int = 0, end: Int = s.length - 1) {
        for (i in start .. min(end, s.length - 1)) {
            charFreq[s[i].getIndex()]++
        }
    }

    fun isAnagrams(charFreq1: IntArray, charFreq2: IntArray): Boolean {
        charFreq1.forEachIndexed { i, freq1 ->
            if (charFreq2[i] != freq1)
                return false
        }
        return true
    }

    fun Char.getIndex() = this.toInt() - 'a'.toInt()
}