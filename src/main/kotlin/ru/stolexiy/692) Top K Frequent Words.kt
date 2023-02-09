package ru.stolexiy

import java.util.*

private typealias Item = Map.Entry<String, Int>

private object Solution692 {

    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        val wordFreq = mutableMapOf<String, Int>()
        words.forEach { w ->
            wordFreq[w] = wordFreq.getOrElse(w) { 0 } + 1
        }
        val comparator = Comparator<Item> { e1, e2 -> e1.value.compareTo(e2.value) }
            .then(Comparator<Item> { e1, e2 -> e1.key.compareTo(e2.key) }.reversed())
        val prQueue = PriorityQueue(comparator)
        val results = LinkedList<String>()
        wordFreq.entries.forEach { curEntry ->
            if (prQueue.size >= k) {
                prQueue.peek().let { min ->
                    if (comparator.compare(curEntry, min) > 0) {
                        prQueue.apply {
                            poll()
                            add(curEntry)
                        }
                    }
                }
            } else {
                prQueue.add(curEntry)
            }
        }
        while (prQueue.isNotEmpty()) {
            results.addFirst(prQueue.poll().key)
        }
        return results
    }

    fun topKFrequent2(words: Array<String>, k: Int): List<String> {
        val wordFreq = mutableMapOf<String, Int>()
        words.forEach { w ->
            wordFreq[w] = wordFreq.getOrElse(w) { 0 } + 1
        }
        val buckets = Array(words.size + 1) { mutableListOf<String>() }
        wordFreq.entries.forEach { e ->
            buckets[e.value].add(e.key)
        }
        buckets.forEach { it.sort() }
        val results = mutableListOf<String>()
        for (i in words.size - 1 downTo 0) {
            if (results.size == k)
                break
            results.addAll(buckets[i].take(k - results.size))
        }
        return results
    }
}
fun main() {
    val words = arrayOf("i","love","leetcode","i","love","coding")
    val k = 2
    println(Solution692.topKFrequent2(words, k).joinToString(", "))
}