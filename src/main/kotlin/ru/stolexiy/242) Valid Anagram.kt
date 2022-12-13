package ru.stolexiy

fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length)
        return false
    val sCharFreq = mutableMapOf<Char, Int>()
    s.forEach {
        sCharFreq[it] = sCharFreq.getOrDefault(it, 0) + 1
    }
    t.forEach {
        if (sCharFreq.getOrDefault(it, 0) == 0)
            return false
        else
            sCharFreq[it] = sCharFreq.getOrDefault(it, 0) - 1
    }
    return true
}