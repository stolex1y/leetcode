package ru.stolexiy

import kotlin.math.max

private fun prefixFunc(str: String): IntArray {
    val result = IntArray(str.length) { 0 }
    for (i in 1 until str.length) {
        if (str[result[i - 1]] == str[i])
            result[i] = result[i - 1] + 1
        else {
            var j = result[max(result[i - 1] - 1, 0)]
            while (j > 0 && str[j] != str[i]) {
                j = result[j - 1]
            }
            result[i] = if (str[j] == str[i])
                j + 1
            else
                0
        }

    }
    return result
}

private fun strStr(source: String, target: String): Int {
    val concat = "$target#$source"
    val prefix = prefixFunc(concat)
    for (i in target.length + 1 until concat.length) {
        if (prefix[i] == target.length)
            return i - 2 * target.length
    }
    return -1
}



fun main() {
    val str = "llllet"
    val find = "lllet"
    println(strStr(str, find))
}
