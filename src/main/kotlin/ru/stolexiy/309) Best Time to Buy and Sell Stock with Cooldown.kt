package ru.stolexiy

import kotlin.math.max

private object Solution309 {
    fun maxProfit(prices: IntArray): Int {
        val maxProfit0 = Array(prices.size + 1) { IntArray(2) { 0 } }
        val maxProfit1 = IntArray(prices.size + 1) { Int.MIN_VALUE }
        for (i in prices.indices) {
            val p = prices[i]
            maxProfit0[i + 1][1] = max(maxProfit0[i][0], maxProfit0[i][1])
            maxProfit0[i + 1][0] = max(maxProfit0[i + 1][1], maxProfit1[i] + p)
            maxProfit1[i + 1] = max(maxProfit1[i], -p + maxProfit0[i][1])
        }
        return maxProfit0.last()[0]
    }
}