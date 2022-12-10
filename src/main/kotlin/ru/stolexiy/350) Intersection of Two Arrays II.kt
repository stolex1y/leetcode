package ru.stolexiy

fun intersectII(nums1: IntArray, nums2: IntArray): IntArray {
    val resultList = mutableListOf<Int>()
    val map = mutableMapOf<Int, Int>()
    val (itArray, mapArray) = if (nums1.size > nums2.size) {
        nums1 to nums2
    } else
        nums2 to nums1
    mapArray.forEach {
        map[it] = map.getOrDefault(it, 0) + 1
    }
    itArray.forEach {
        if (map.getOrDefault(it, 0) > 0) {
            map[it] = map[it]!! - 1
            resultList += it
        }
    }
    return resultList.toIntArray()
}

fun intersectIISorted(nums1: IntArray, nums2: IntArray): IntArray {
    val resultList = mutableListOf<Int>()
    val nums1Sorted = nums1.sortedArray()
    val nums2Sorted = nums2.sortedArray()
    var first = 0
    var second = 0
    while (first < nums1.size && second < nums2.size) {
        if (nums1Sorted[first] > nums2Sorted[second]) {
            second++
        } else if (nums1Sorted[first] < nums2Sorted[second]) {
            first++
        } else {
            resultList += nums1Sorted[first]
            first++
            second++
        }
    }
    return resultList.toIntArray()
}

fun main() {
    val nums1 = intArrayOf(4,9,5,4)
    val nums2 = intArrayOf(9,4,9,8,4)
    val expected = intArrayOf(9,4,4)
    val actual = intersectII(nums1, nums2)
    assert(expected.contentEquals(actual))
    println("Success!")
}