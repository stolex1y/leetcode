package ru.stolexiy

fun checkInclusion(s1: String, s2: String): Boolean {
    var windowStart = 0
    val windowSize = s1.length
    val frequenciesS1 = mutableMapOf<Char, Int>()
    val frequenciesWindow = mutableMapOf<Char, Int>()

    calcFrequencies(frequenciesS1, s1)
    while (windowStart + windowSize <= s2.length) {
        if (windowStart == 0) {
            calcFrequencies(frequenciesWindow, s2.substring(windowStart, windowSize))
        } else {
            val removeChar = s2[windowStart - 1]
            val addChar = s2[windowStart + windowSize - 1]
            frequenciesWindow[removeChar] = frequenciesWindow.getOrDefault(removeChar, 1) - 1
            frequenciesWindow[addChar] = frequenciesWindow.getOrDefault(addChar, 0) + 1
        }
        if (isPermutation(frequenciesS1, frequenciesWindow))
            return true
        windowStart++
    }
    return false
}

fun isPermutation(freq1: MutableMap<Char, Int>, freq2: MutableMap<Char, Int>): Boolean {
    freq1.forEach { entry ->
        if (entry.value != freq2[entry.key])
            return false
    }
    return true
}

fun calcFrequencies(frequencies: MutableMap<Char, Int>, s: String) {
    s.forEach {
        frequencies[it] = frequencies.getOrDefault(it, 0) + 1
    }
}