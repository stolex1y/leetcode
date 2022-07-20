package ru.stolexiy

import kotlin.streams.toList

fun reverseWords(s: String) = s.split(" ")
    .joinToString(separator = " ", transform = String::reversed)

fun reverseWordsWithoutLib(s: String): String {
    val result = StringBuilder()
    val curString = mutableListOf<Char>()
    s.forEach { c ->
        if (c == ' ') {
            result.append(String(curString.toCharArray()).reversed()).append(' ')
            curString.clear()
        } else {
            curString += c
        }
    }
    if (curString.isNotEmpty())
        result.append(String(curString.toCharArray()).reversed())
    return result.toString()
}

private fun String.reversed(): String {
    val reversed = CharArray(this.length)
    this.toCharArray().forEachIndexed { index, c ->
        reversed[this.length - 1 - index] = c
    }
    return String(reversed)
}