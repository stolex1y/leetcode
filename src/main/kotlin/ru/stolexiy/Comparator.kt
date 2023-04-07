package ru.stolexiy

import ru.stolexiy.leetcode.twoSumFirst
import ru.stolexiy.leetcode.twoSumSecond

fun main() {
    val testCount = 1000

    //read input
    val intArr = readln().split(",").map(String::toInt).toIntArray()
    val target = readln().toInt()

    val start1 = System.currentTimeMillis()
    for (i in 1..testCount) {
        twoSumFirst(intArr, target)
    }
    val end1 = System.currentTimeMillis()
    val start2 = System.currentTimeMillis()
    for (i in 1..testCount) {
        twoSumSecond(intArr, target)
    }
    val end2 = System.currentTimeMillis()
    println("1) ${end1 - start1}\n2) ${end2 - start2}")
}