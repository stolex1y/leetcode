package ru.stolexiy

private object Solution714 {
    fun maxProfit(prices: IntArray, fee: Int): Int {
        var maxProfit0 = 0
        var maxProfit1 = Int.MIN_VALUE
        for (p in prices) {
            val prevMaxProfit0 = maxProfit0
            (maxProfit1 + p).takeIf { it > maxProfit0 }?.let { maxProfit0 = it }
            (prevMaxProfit0 - p - fee).takeIf { it > maxProfit1 }?.let { maxProfit1 = it }
        }
        return maxProfit0
    }
}
