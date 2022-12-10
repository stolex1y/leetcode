package ru.stolexiy

fun maxProfit(prices: IntArray): Int {
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