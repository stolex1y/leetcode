package ru.stolexiy.leetcode

import java.util.*

fun dailyTemperatures(temperatures: IntArray): IntArray {
    val stack = Stack<Int>()
    val result = IntArray(temperatures.size) { 0 }
    temperatures.forEachIndexed { index, i ->
        while (stack.isNotEmpty() && temperatures[stack.peek()] < i) {
            val top = stack.pop()
            result[top] = index - top
        }
        stack.push(index)
    }
    return result
}

fun main() {
    // 5 4 3 2 1 6
    val temp = intArrayOf(73,74,75,71,69,72,76,73)
    println(dailyTemperatures(temp).joinToString(" "))
}