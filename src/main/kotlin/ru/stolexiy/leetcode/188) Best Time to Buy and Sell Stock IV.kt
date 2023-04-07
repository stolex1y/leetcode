package ru.stolexiy.leetcode

import kotlin.math.max

private object Solution188 {
    fun maxProfit(k: Int, prices: IntArray): Int {
        val maxProfit0 = Array(k + 1) {
            IntArray(prices.size + 1) { 0 }
        }
        val maxProfit1 = Array(k + 1) {
            IntArray(prices.size + 1) { Int.MIN_VALUE }
        }
        for (i in prices.indices) {
            val p = prices[i]
            for (trans in 0..k) {
                maxProfit0[trans][i + 1] = max(maxProfit0[trans][i], p + maxProfit1[trans][i])
                maxProfit1[trans][i + 1] = if (trans == 0)
                    maxProfit1[trans][i]
                else
                    max(maxProfit1[trans][i], -p + maxProfit0[trans - 1][i])
            }
        }
        return maxProfit0.last().last()
    }
}