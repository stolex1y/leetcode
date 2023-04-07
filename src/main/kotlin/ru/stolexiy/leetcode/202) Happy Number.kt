package ru.stolexiy.leetcode

private object Solution202 {
    fun isHappy(n: Int): Boolean {
        val prevN = mutableSetOf<Int>()
        var nextN = n
        while (nextN != 1 && !prevN.contains(nextN)) {
            prevN.add(nextN)
            var curN = nextN
            nextN = 0
            while (curN > 0) {
                val digit = curN % 10
                curN /= 10
                nextN += digit * digit
            }
        }
        return nextN == 1
    }
}