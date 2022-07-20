package ru.stolexiy

fun reverseString(s: CharArray) {
    val mid = s.size / 2
    for (i in 0 until mid) {
        val t = s[i]
        s[i] = s[s.size - 1 - i]
        s[s.size - 1 - i] = t
    }
}

fun reverseStringWithTwoPointers(s: CharArray) {
    var left = 0
    var right = s.size - 1
    while (left < right) {
        val t = s[left]
        s[left] = s[right]
        s[right] = t
        left++
        right--
    }
}