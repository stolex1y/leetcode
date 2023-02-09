package ru.stolexiy

import kotlin.math.max
private object Solution121 {

    fun maxProfitGenerally(prices: IntArray): Int {
        val maxProfit1 = IntArray(prices.size + 1) { 0 } // max profit with stock in hand on i-th day (1 transaction allowed)
        val maxProfit0 = IntArray(prices.size + 1) { 0 } // max profit without stock in hand on i-th day (1 transaction allowed)
        // maxProfit(day = n, stock = 0, trans = 1) = max(
        //          prices[day = n] + maxProfit(day = n - 1, stock = 1, trans = 1),
        //          maxProfit(day = n - 1, stock = 0, trans = 1)
        // )
        // maxProfit(day = n, stock = 1, trans = 1) = max(
        //          maxProfit(day = n - 1, stock = 1, trans = 1),
        //          -prices[day = n] + maxProfit(day = n - 1, stock = 0, trans = 0)
        // )
        maxProfit1[0] = Int.MIN_VALUE // mustn't have a stock on the 1st day
        for (i in prices.indices) {
            val p = prices[i]
            maxProfit0[i + 1] = max(maxProfit0[i], maxProfit1[i] + p)
            maxProfit1[i + 1] = max(maxProfit1[i], -p)
        }
        return maxProfit0.last()
    }

    fun maxProfitGenerallyOpt(prices: IntArray): Int {
        var maxProfit0 = 0
        var maxProfit1 = Int.MIN_VALUE
        for (i in prices) {
            maxProfit0 = max(maxProfit0, maxProfit1 + i)
            maxProfit1 = max(maxProfit1, -i)
        }
        return maxProfit0
    }

    fun maxProfit(prices: IntArray): Int {
        var min = Int.MAX_VALUE
        var max = 0
        prices.forEach { p ->
            if (p < min)
                min = p
            else if (p - min > max)
                max = p - min
        }
        return max
    }

    fun maxProfitII(prices: IntArray): Int {
        var prevDayPrice = prices.first()
        for (i in prices.indices) {
            val newPrevDayPrice = prices[i]
            prices[i] -= prevDayPrice
            prevDayPrice = newPrevDayPrice
        }
        val maxSums = IntArray(prices.size)
        maxSums[0] = 0
        var maxSum = 0
        for (i in 1 until prices.size) {
            if (maxSums[i - 1] > 0) {
                maxSums[i] = maxSums[i - 1] + prices[i]
            } else {
                maxSums[i] = prices[i]
            }
            maxSum = maxOf(maxSum, maxSums[i])
        }
        return maxSum
    }
}
