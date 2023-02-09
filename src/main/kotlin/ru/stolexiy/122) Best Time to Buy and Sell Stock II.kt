package ru.stolexiy

import kotlin.math.max

private object Solution122 {

    fun maxProfitGenerally(prices: IntArray): Int {
        val maxProfit1 = IntArray(prices.size + 1) { 0 } // max profit with stock in hand on i-th day (max +Inf transactions allowed)
        val maxProfit0 = IntArray(prices.size + 1) { 0 } // max profit without stock in hand on i-th day (max +Inf transactions allowed)
        // maxProfit(day = n, stock = 0, trans = inf) = max(
        //          prices[day = n] + maxProfit(day = n - 1, stock = 1, trans = inf),
        //          maxProfit(day = n - 1, stock = 0, trans = inf)
        // )
        // maxProfit(day = n, stock = 1, trans = inf) = max(
        //          maxProfit(day = n - 1, stock = 1, trans = inf),
        //          -prices[day = n] + maxProfit(day = n - 1, stock = 0, trans = inf)
        // )
        for (i in prices.indices) {
            val p = prices[i]
            maxProfit0[i + 1] = max(maxProfit0[i], maxProfit1[i] + p)
            maxProfit1[i + 1] = max(maxProfit1[i], maxProfit0[i] - p)
        }
        return maxProfit0.last()
    }

    fun maxProfitGenerallyOpt(prices: IntArray): Int {
        var maxProfit0 = 0
        var maxProfit1 = Int.MIN_VALUE
        for (i in prices.indices) {
            val p = prices[i]
            val prevMaxProfit0 = maxProfit0 // not necessary
            maxProfit0 = max(maxProfit0, maxProfit1 + p)
            maxProfit1 = max(maxProfit1, prevMaxProfit0 - p)
        }
        return maxProfit0
    }

    fun maxProfit(prices: IntArray): Int {
        var totalSum = 0
        for (i in 1 until prices.size) {
            (prices[i] - prices[i - 1]).takeIf { it > 0 }?.let {
                totalSum += it
            }
        }
        return totalSum
    }

    fun maxProfitFirstSolution(prices: IntArray): Int {
        var start = Int.MAX_VALUE
        var end = Int.MIN_VALUE
        var totalSum = 0
        prices.forEach {
            if (it < end) {
                end = Int.MIN_VALUE
                start = it
            } else if (it < start) {
                start = it
            } else {
                totalSum += if (end == Int.MIN_VALUE)
                    it - start
                else
                    it - end
                end = it
            }
        }
        return totalSum
    }
}