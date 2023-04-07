package ru.stolexiy.leetcode

fun firstUniqChar(s: String): Int {
    val charFreq = mutableMapOf<Char, Int>()
    s.forEach {
        charFreq[it] = charFreq.getOrDefault(it, 0) + 1
    }
    s.forEachIndexed { idx, ch ->
        if (charFreq[ch] == 1)
            return idx
    }
    return -1
}