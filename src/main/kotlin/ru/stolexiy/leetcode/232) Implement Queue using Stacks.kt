package ru.stolexiy.leetcode

import java.util.Stack

class MyQueue() {
    private val stack = Stack<Int>()
    private val reversedStack = Stack<Int>()

    fun push(x: Int) {
        stack.push(x)
    }

    fun pop(): Int {
        peek()
        return reversedStack.pop()
    }

    fun peek(): Int {
        if (reversedStack.isEmpty())
            fromStackToReversedStack()
        return reversedStack.peek()
    }

    fun empty(): Boolean {
        return reversedStack.isEmpty() && stack.isEmpty()
    }

    private fun fromStackToReversedStack() {
        while (stack.isNotEmpty())
            reversedStack.push(stack.pop())
    }
}

fun main() {
    val queue = MyQueue()
    queue.push(1)
    queue.push(2)
    var x = queue.pop()
    println(x)
    x = queue.peek()
    println(x)
    println(queue.empty())
}