package ru.stolexiy

import java.lang.StringBuilder

private fun compress(chars: CharArray): Int {
    if (chars.isEmpty())
        return 0
    var cur = 1
    var start = 0
    var writePtr = 0
    while (cur < chars.size) {
        if (chars[cur] != chars[cur - 1]) {
            compressChar(chars, start until cur).forEach {
                chars[writePtr++] = it
            }
            start = cur
        }
        cur++
    }
    compressChar(chars, start until cur).forEach {
        chars[writePtr++] = it
    }
    return writePtr
}

private fun compressChar(chars: CharArray, range: IntRange): String {
    val sb = StringBuilder()
    sb.append(chars[range.last()])
    range.count().takeIf { it > 1 }?.let { count ->
        sb.append(count.toString())
    }
    return sb.toString()
}