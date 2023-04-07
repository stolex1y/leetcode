package ru.stolexiy.leetcode

import java.util.*
import kotlin.math.max
import kotlin.math.min

private fun trap(height: IntArray): Int {
    if (height.size <= 1)
        return 0
    val monotonicStack = Stack<Int>()
    var result = 0
    var i = 0
    while (i < height.size) {
        val nextH = height[i]
        while (monotonicStack.isNotEmpty() && height[monotonicStack.peek()] < nextH) {
            val popIdx = monotonicStack.pop()
            val curH = height[popIdx]
            val (maxFillHeight, leftWallIdx) = if (monotonicStack.isNotEmpty()) {
                monotonicStack.peek().let {
                    min(nextH, height[it]) to it + 1
                }
            } else {
                curH to (popIdx - 1)
            }
            result += (i - leftWallIdx) * (maxFillHeight - curH)
        }
        monotonicStack.push(i)
        i++
    }
    return result
}

private fun trapDP(height: IntArray): Int {
    val leftMax = IntArray(height.size) { 0 }
    for (i in 0 .. height.size - 1) {
        leftMax[i] = if (i == 0)
            height[0]
        else
            max(leftMax[i - 1], height[i])
    }

    val rightMax = IntArray(height.size) { 0 }
    for (i in height.size - 1 downTo 0) {
        rightMax[i] = if (i == height.size - 1)
            height.last()
        else
            max(rightMax[i + 1], height[i])
    }

    var sum = 0
    height.forEachIndexed { i, h ->
        sum += min(leftMax[i], rightMax[i]) - h
    }
    return sum
}

private fun trapTwoPointers(height: IntArray): Int {
    var leftMax = 0
    var rightMax = 0
    var left = 0
    var right = height.size - 1
    var sum = 0
    while (left < right) {
        if (height[left] < height[right]) {
            if (height[left] >= leftMax) {
                leftMax = height[left]
            } else {
                sum += leftMax - height[left]
            }
            left++
        } else {
            if (height[right] >= rightMax) {
                rightMax = height[right]
            } else {
                sum += rightMax - height[right]
            }
            right--
        }
    }
    return sum
}