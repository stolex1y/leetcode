package ru.stolexiy

import java.lang.Integer.max

fun lengthOfLongestSubstring(s: String): Int {
    if (s.isEmpty())
        return 0
    val characters = mutableMapOf<Char, Int>()
    var substringStart = 0
    var longestLength = 1
    s.forEachIndexed { index, c ->
        if (characters.containsKey(c))
            substringStart = max(characters[c]!! + 1, substringStart)
        characters[c] = index
        longestLength = max(longestLength, index - substringStart + 1)
    }
    return longestLength
}

fun lengthOfLongestSubstringN2(s: String): Int {
    var longestLength = 0
    val characters = mutableSetOf<Char>()
    for (i1 in s.indices) {
        for (c2 in s.substring(i1, s.length)) {
            if (characters.contains(c2)) {
                longestLength = max(longestLength, characters.size)
                characters.clear()
                break
            } else {
                characters += c2
            }
        }
    }
    longestLength = max(longestLength, characters.size)
    return longestLength
}