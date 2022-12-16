package ru.stolexiy

import java.util.BitSet

fun findRepeatedDnaSequences(s: String): List<String> {
    val sequences = mutableSetOf<String>()
    val repeated = mutableSetOf<String>()
    for (i in 0 .. s.length - 10) {
        val substr = s.substring(i until i + 10)
        if (sequences.contains(substr)) {
            repeated += substr
        } else {
            sequences += substr
        }
    }
    return repeated.toList()
}

fun findRepeatedDnaSequencesBitSet(s: String): List<String> {
    val sequences = BitSet(1.shl(20))
    val repeated = BitSet(1.shl(20))
    val result = mutableListOf<String>()
    val sChars = s.toCharArray()
    for (i in 0 .. s.length - 10) {
        val hash = dnaSequenceHash(sChars, i)
        if (sequences.get(hash) && !repeated.get(hash)) {
            repeated.set(hash)
            result += String(sChars.sliceArray(i .. i + 9))
        } else {
            sequences.set(hash)
        }
    }
    return result
}

fun dnaSequenceHash(charArray: CharArray, l: Int, r: Int = l + 10): Int {
    var hash = 0
    for (i in l until r) {
        hash = hash.shl(2).or(Nucleotides.fromChar(charArray[i]).code)
    }
    return hash
}

private enum class Nucleotides(val code: Int) {
    A(0),
    C(1),
    G(2),
    T(3);

    companion object {
        fun fromChar(ch: Char) = when (ch) {
            'A' -> A
            'C' -> C
            'G' -> G
            'T' -> T
            else -> throw IllegalArgumentException()
        }
    }
}

fun main() {
    val s = "AAAAAAAAAAA"
    println(findRepeatedDnaSequencesBitSet(s).joinToString(", "))
}