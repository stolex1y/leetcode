package ru.stolexiy.leetcode

import java.util.Stack

fun evalRPN(tokens: Array<String>): Int {
    if (tokens.isEmpty())
        return 0
    val operands = Stack<Int>()
    tokens.forEach { token ->
        val value = token.toIntOrNull() ?: Operator.fromChar(token.first()) ?: throw IllegalArgumentException()
        if (value is Int) {
            operands.push(value)
        } else if (value is Operator) {
            if (operands.size < 2)
                throw IllegalStateException()
            val second = operands.pop()
            val first = operands.pop()
            operands.push(value.apply(first, second))
        }
    }
    return operands.pop()
}

private enum class Operator(val sign: Char, private val func: (Int, Int) -> Int) {
    PLUS('+', { i1, i2 -> i1 + i2 }),
    MULT('*', { i1, i2 -> i1 * i2 }),
    DIV('/', { i1, i2 -> i1 / i2 }),
    MINUS('-', { i1, i2 -> i1 - i2 });

    fun apply(i1: Int, i2: Int) = this.func(i1, i2)

    companion object {
        fun fromChar(char: Char): Operator? {
            return Operator.values()
                .filter { it.sign == char }
                .getOrNull(0)
        }
    }
}

fun main() {
    val tokens = arrayOf("4", "13", "5", "/", "+")
    println(evalRPN(tokens))
}