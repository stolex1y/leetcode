package ru.stolexiy.leetcode

import kotlin.math.max

/**
 * maxLen(s1,s2) = { maxLen(s1-1,s2-1) + 1, if s1.last == s2.last]
 *                 { max(maxLen(s1-1,s2), maxLen(s1,s2-1), if s1.last != s2.last
 *                 { 0, if s1.size == 0 or s2.size == 0
 *  s1 = cabcde
 *  s2 = acabd
 *  lcs = cabd
 */

fun longestCommonSubsequence(s1: String, s2: String): Int {
    val lcsTable = Array(s1.length + 1) { IntArray(s2.length + 1) { 0 } }
    for (i in s1.indices) {
        for (j in s2.indices) {
            lcsTable[i + 1][j + 1] =
                if (s1[i] == s2[j])
                    lcsTable[i][j] + 1
                else
                    max(lcsTable[i + 1][j], lcsTable[i][j + 1])
        }
    }
    return lcsTable[s1.length][s2.length]
}

fun main() {
    val s1 = "cabcde"
    val s2 = "acabd"
    println(longestCommonSubsequence(s1, s2))
}