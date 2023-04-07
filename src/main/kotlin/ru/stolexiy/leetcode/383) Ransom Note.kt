package ru.stolexiy.leetcode

fun canConstruct(ransomNote: String, magazine: String): Boolean {
    val magazineCharFreq = mutableMapOf<Char, Int>()
    magazine.forEach {
        magazineCharFreq[it] = magazineCharFreq.getOrDefault(it, 0) + 1
    }
    ransomNote.forEach {
        if (magazineCharFreq.getOrDefault(it, 0) == 0)
            return false
        else
            magazineCharFreq[it] = magazineCharFreq.getOrDefault(it, 0) - 1
    }
    return true
}