package ru.stolexiy.leetcode

private object Solution347 {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val numFreq = mutableMapOf<Int, Int>()
        nums.forEach {
            numFreq[it] = numFreq.getOrElse(it) { 0 } + 1
        }
        val buckets = Array<MutableList<Int>?>(nums.size + 1) { null }
        numFreq.entries.forEach { e ->
            if (buckets[e.value] == null)
                buckets[e.value] = mutableListOf()
            buckets[e.value]!!.add(e.key)
        }
        val results = mutableListOf<Int>()
        for (i in buckets.size - 1 downTo 0) {
            if (results.size == k)
                break
            buckets[i]?.let {
                results.addAll(it.take(k - results.size))
            }
        }
        return results.toIntArray()
    }
}