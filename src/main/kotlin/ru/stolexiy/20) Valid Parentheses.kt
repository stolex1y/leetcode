package ru.stolexiy

import java.util.*

fun isValid(s: String): Boolean {
    val stack = Stack<Bracket>()
    s.forEach {
        val currBracket = Bracket.fromChar(it)
        if (stack.isNotEmpty() && stack.peek().getComplementary() == currBracket && currBracket.isClose())
            stack.pop()
        else
            stack.push(currBracket)
    }
    return stack.isEmpty()
}

private enum class Bracket(val char: Char) {
    PARENTHESIS_OPEN('('),
    PARENTHESIS_CLOSE(')'),
    SQUARE_OPEN('['),
    SQUARE_CLOSE(']'),
    BRACE_OPEN('{'),
    BRACE_CLOSE('}');

    fun isClose() = (this == PARENTHESIS_CLOSE || this == SQUARE_CLOSE || this == BRACE_CLOSE)

    fun getComplementary() = when (this) {
        PARENTHESIS_OPEN -> PARENTHESIS_CLOSE
        PARENTHESIS_CLOSE -> PARENTHESIS_OPEN
        SQUARE_OPEN -> SQUARE_CLOSE
        SQUARE_CLOSE -> SQUARE_OPEN
        BRACE_OPEN -> BRACE_CLOSE
        BRACE_CLOSE -> BRACE_OPEN
    }

    companion object {
        fun fromChar(char: Char) = when (char) {
            PARENTHESIS_OPEN.char -> PARENTHESIS_OPEN
            PARENTHESIS_CLOSE.char -> PARENTHESIS_CLOSE
            SQUARE_OPEN.char -> SQUARE_OPEN
            SQUARE_CLOSE.char -> SQUARE_CLOSE
            BRACE_OPEN.char -> BRACE_OPEN
            BRACE_CLOSE.char -> BRACE_CLOSE
            else -> throw IllegalArgumentException()
        }
    }
}

fun main() {
    val s = "({][})"
    println(isValid(s))
}
