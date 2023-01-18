package ru.stolexiy
private fun checkInclusion(s1: String, s2: String): Boolean {
    if (s2.length < s1.length)
        return false
    else if (s1.isEmpty())
        return true
    val s1Freqs = calcCharFreq(s1)
    var s2WindowFreqs: IntArray? = null
    for (right in s1.length .. s2.length) {
        if (s2WindowFreqs == null)
            s2WindowFreqs = calcCharFreq(s2, left = 0, right = right)
        else {
            val addChar = s2[right - 1]
            val removeChar = s2[right - 1 - s1.length]
            s2WindowFreqs[addChar.toInt() - 'a'.toInt()]++
            s2WindowFreqs[removeChar.toInt() - 'a'.toInt()]--
        }
        if (isAnagrams(s1Freqs, s2WindowFreqs))
            return true
    }
    return false
}

private fun calcCharFreq(s: String, left: Int = 0, right: Int = s.length): IntArray {
    val freqs = IntArray(26) { 0 }
    for (i in left until right) {
        val ch = s[i]
        freqs[ch.toInt() - 'a'.toInt()]++
    }
    return freqs
}

private fun isAnagrams(s1Freqs: IntArray, s2Freqs: IntArray): Boolean {
    s1Freqs.forEachIndexed { i, freq1 ->
        if (freq1 != s2Freqs[i])
            return false
    }
    return true
}