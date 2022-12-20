package ru.stolexiy

import java.util.*

/**
 * s1   = a c a d b e
 * s2   = c a b e d
 * scs  =
 *
 * scs(s1,s2) = { "", if s1.length == 0 && s2.length == 0
 *              { s1, if s2.length == 0
 *              { s2, if s1.length == 0
 *              { s1.last + min_len(scs(s1-1,s2), scs(s1,s2-1))
 *              {
 *              {
 */

fun shortestCommonSupersequence(s1: String, s2: String): String {
    val scsTable = Array(s1.length + 1) { IntArray(s2.length + 1) { 0 } }

    s1.indices.forEach { i ->
        scsTable[i + 1][0] = i + 1
    }

    s2.indices.forEach { i ->
        scsTable[0][i + 1] = i + 1
    }

    for (i in 1 .. s1.length) {
        for (j in 1 .. s2.length) {
            if (s1[i - 1] == s2[j - 1])
                scsTable[i][j] = scsTable[i - 1][j - 1] + 1
            else if (scsTable[i - 1][j] <= scsTable[i][j - 1])
                scsTable[i][j] = scsTable[i - 1][j] + 1
            else
                scsTable[i][j] = scsTable[i][j - 1] + 1
        }
    }

    return getResultSuperseq(s1, s2, scsTable)
}

fun getResultSuperseq(s1: String, s2: String, scsTable: Array<IntArray>): String {
    var row = s1.length
    var column = s2.length
    val resultSuperseq = StringBuilder()
    while (row > 0 && column > 0) {
        if (s1[row - 1] == s2[column - 1]) {
            resultSuperseq.append(s1[row - 1])
            row--
            column--
        } else if (scsTable[row - 1][column] <= scsTable[row][column - 1]) {
            resultSuperseq.append(s1[row - 1])
            row--
        } else {
            resultSuperseq.append(s2[column - 1])
            column--
        }
    }
    while (row > 0) {
        resultSuperseq.append(s1[row - 1])
        row--
    }
    while (column > 0) {
        resultSuperseq.append(s2[column - 1])
        column--
    }
    return resultSuperseq.reversed().toString()
}

fun main() {
    val s1 = "cabdd"
    val s2 = "acaddb"
    println(shortestCommonSupersequence(s1, s2))
}

