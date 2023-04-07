package ru.stolexiy.leetcode

import kotlin.math.abs

fun reverse(x: Int): Int {
    var reversed = 0
    var div = abs(x)
    while (div > 0 && Int.MAX_VALUE / 10 >= reversed) {
        reversed = reversed * 10 + div % 10
        div /= 10
    }
    return if (div > 0)
            0
    else if (x < 0)
        -reversed
    else
        reversed
}

fun main() {
    val x = 1534236469
    println(reverse(x))
}