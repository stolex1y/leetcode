package ru.stolexiy.leetcode

import java.util.*

private object Solution480 {
    fun medianSlidingWindow(nums: IntArray, k: Int): DoubleArray {
        val leftHalf = MultiSet<Int>()
        val rightHalf = MultiSet<Int>()
        for (i in 0 until k) {
            leftHalf.add(nums[i])
        }
        balanceHalves(leftHalf, rightHalf)
        val medians = DoubleArray(nums.size - k + 1) { 0.0 }
        medians[0] = getMedian(leftHalf, rightHalf)
        for (i in k until nums.size) {
            val removeNum = nums[i - k]
            leftHalf.add(nums[i])
            if (!leftHalf.remove(removeNum)) {
                rightHalf.remove(removeNum)
            }
            rightHalf.removeFirst()?.let { leftHalf.add(it) }
            balanceHalves(leftHalf, rightHalf)
            medians[i - k + 1] = getMedian(leftHalf, rightHalf)
        }
        return medians
    }

    private fun balanceHalves(leftHalf: MultiSet<Int>, rightHalf: MultiSet<Int>) {
        while (leftHalf.size > rightHalf.size) {
            leftHalf.removeLast()?.let { rightHalf.add(it) }
        }
    }

    private fun getMedian(leftHalf: MultiSet<Int>, rightHalf: MultiSet<Int>): Double {
        val size = leftHalf.size + rightHalf.size
        return if (size % 2 == 0)
            (leftHalf.last!!.toDouble() + rightHalf.first!!.toDouble()) / 2.0
        else
            rightHalf.first!!.toDouble()
    }

    private class MultiSet<T : Comparable<T>> {
        private val map = TreeMap<T, Int>()

        var size = 0
            private set

        val first: T?
            get() {
                return if (map.isEmpty())
                    null
                else
                    map.firstKey()
            }
        val last: T?
            get() {
                return if (map.isEmpty())
                    null
                else
                    map.lastKey()
            }

        fun add(t: T) {
            map.run {
                if (!contains(t)) {
                    put(t, 1)
                } else {
                    this[t] = this[t]!! + 1
                }
            }
            size++
        }

        fun remove(t: T): Boolean {
            val removed = map.run {
                if (contains(t)) {
                    if (get(t)!! > 1) {
                        this[t] = this[t]!! - 1
                    } else {
                        remove(t)
                    }
                    true
                } else {
                    false
                }
            }
            if (removed)
                size--
            return removed
        }

        fun removeLast(): T? {
            return last?.let { remove(it); it }
        }

        fun removeFirst(): T? {
            return first?.let { remove(it); it }
        }
    }
}

fun main() {
    val nums = intArrayOf(1,3,-1,-3,5,3,6,7)
    val k = 3
    println(Solution480.medianSlidingWindow(nums, k).joinToString(", "))
}