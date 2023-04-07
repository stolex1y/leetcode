package ru.stolexiy.leetcode

fun twoSumFirst(nums: IntArray, target: Int): IntArray {
    val sortedNums = nums.sortedArray()
    var left = 0
    var right = nums.size - 1
    while (left != right) {
        val sum = sortedNums[left] + sortedNums[right]
        if (sum == target) {
            return findIndexes(nums, sortedNums[left], sortedNums[right])
        } else if (sum > target)
            right--
        else
            left++
    }
    return intArrayOf()
}

fun findIndexes(nums: IntArray, first: Int, second: Int): IntArray {
    var firstIdx = -1
    var secondIdx = -1
    nums.forEachIndexed { idx, i ->
        if (first == i && firstIdx == -1)
            firstIdx = idx
        else if (second == i && secondIdx == -1)
            secondIdx = idx
        else if (firstIdx != -1 && secondIdx != -1)
            return intArrayOf(firstIdx, secondIdx)
    }
    return intArrayOf(firstIdx, secondIdx)
}

fun twoSumSecond(nums: IntArray, target: Int): IntArray {
    val numsMap = mutableMapOf<Int, Int>()
    nums.forEachIndexed { index, i ->
        val complementary = target - i
        if (numsMap.contains(complementary))
            return intArrayOf(numsMap[complementary]!!, index)
        else
            numsMap[i] = index
    }
    return intArrayOf()
}

fun main() {
    val intArr = readln().split(",").map(String::toInt).toIntArray()
    val target = readln().toInt()
    println(twoSumFirst(intArr, target).joinToString(","))
}