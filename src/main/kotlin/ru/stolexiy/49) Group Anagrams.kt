package ru.stolexiy

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val results = mutableMapOf<String, MutableList<String>>()
    val charCounts = IntArray(26) { 0 }
    for (str in strs) {
        calcCharCounts(str, charCounts)
        val encoded = charCounts.joinToString(",")
        results.getOrPut(encoded) { mutableListOf() }.apply { this.add(str) }
    }
    return results.values.toList()
}

private fun calcCharCounts(str: String, charCounts: IntArray) {
    charCounts.fill(0)
    str.forEach { ch ->
        charCounts[ch.code - 'a'.code]++
    }
}