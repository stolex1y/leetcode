package ru.stolexiy

import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private object Solution374 {

    var num: Int = 1

    fun guess(pick: Int): Int {
        return num.compareTo(pick)
    }

    fun guessNumber(n: Int): Int {
        var l = 1
        var r = n
        while (r > l) {
            val m = l + (r - l) / 2
            val guessed = guess(m)
            if (guessed == 0) {
                return m
            } else if (guessed < 0) {
                r = m - 1
            } else {
                l = m + 1
            }
        }
        return l
    }
}

fun main() {
    Solution374.num = 1702766719
    val n = 2126753390
    runBlocking {
        val time = measureTimeMillis {
            println(Solution374.guessNumber(n))
        }
        println("Time = $time ms")
    }
}