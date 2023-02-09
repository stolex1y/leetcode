package ru.stolexiy

import java.util.*

private object Solution322 {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val minCount = IntArray(amount + 1) { -1 }
        minCount[0] = 0
        val queue = LinkedList<Int>()
        coins.filter { it <= amount }.forEach {
            minCount[it] = 1
            queue.add(it)
        }
        while (queue.isNotEmpty()) {
            queue.pop().let { curAmount ->
                coins.forEach { coin ->
                    (curAmount + coin).takeIf { it in 0..amount }?.let { nextAmount ->
                        val nextCount = minCount[curAmount] + 1
                        if (minCount[nextAmount] == -1 || nextCount < minCount[nextAmount]) {
                            queue.add(nextAmount)
                            minCount[nextAmount] = nextCount
                        }
                    }
                }
            }
        }
        return minCount[amount]
    }
}
