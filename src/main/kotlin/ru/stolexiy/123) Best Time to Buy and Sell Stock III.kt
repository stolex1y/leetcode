package ru.stolexiy

import kotlin.math.max

private object Solution123 {
    fun maxProfitGenerallyOpt(prices: IntArray): Int {
        val maxProfit0 = Array(3) { 0 }
        val maxProfit1 = Array(3) { Int.MIN_VALUE }
        for (i in prices.indices) {
            val p = prices[i]
            val prevMaxProfit0 = maxProfit0.copyOf()
            for (trans in 0..2) {
                maxProfit0[trans] = max(maxProfit0[trans], p + maxProfit1[trans])
                maxProfit1[trans] = if (trans == 0)
                    maxProfit1[trans]
                else
                    max(maxProfit1[trans], -p + prevMaxProfit0[trans - 1])
            }
        }
        return maxProfit0[2]
    }

    fun maxProfitGenerally(prices: IntArray): Int {
        val maxProfit0 = Array(3) {
            IntArray(prices.size + 1) { 0 }
        }
        val maxProfit1 = Array(3) {
            IntArray(prices.size + 1) { Int.MIN_VALUE }
        }
        // maxProfit(day = n, stock = 0, trans = k) = max(
        //          prices[day = n] + maxProfit(day = n - 1, stock = 1, trans = k),
        //          maxProfit(day = n - 1, stock = 0, trans = k)
        // )
        // maxProfit(day = n, stock = 1, trans = k) = max(
        //          maxProfit(day = n - 1, stock = 1, trans = k),
        //          -prices[day = n] + maxProfit(day = n - 1, stock = 0, trans = k - 1)
        // )
        for (i in prices.indices) {
            val p = prices[i]
            for (trans in 0..2) {
                maxProfit0[trans][i + 1] = max(maxProfit0[trans][i], p + maxProfit1[trans][i])
                maxProfit1[trans][i + 1] = if (trans == 0)
                    maxProfit1[trans][i]
                else
                    max(maxProfit1[trans][i], -p + maxProfit0[trans - 1][i])
            }
        }
        return maxProfit0[2].last()
    }
}