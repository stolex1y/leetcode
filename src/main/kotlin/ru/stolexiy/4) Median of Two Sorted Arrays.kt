package ru.stolexiy

import kotlin.math.min

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val m1 = findMedianSortedArrays(nums1, 0, nums2, 0, (nums1.size + nums2.size + 1) / 2)
    val m2 = findMedianSortedArrays(nums1, 0, nums2, 0, (nums1.size + nums2.size + 2) / 2)
    return (m1 + m2) / 2.0
}

fun findMedianSortedArrays(nums1: IntArray, start1: Int, nums2: IntArray, start2: Int, k: Int): Double {
    if (start1 >= nums1.size)
        return nums2[start2 + k - 1].toDouble()
    if (start2 >= nums2.size)
        return nums1[start1 + k - 1].toDouble()
    if (k == 1)
        return min(nums1[start1], nums2[start2]).toDouble()
    val p1 = k / 2 - start1
    val p2 = k - p1 - 1
    val m1 = nums1[start1 + p1]
    val m2 = nums2[start2 + p2]
    return if (m1 < m2) {
        findMedianSortedArrays(nums1, start1 + k / 2, nums2, start2, k - k / 2)
    } else {
        findMedianSortedArrays(nums1, start1, nums2, start2 + k / 2, k - k / 2)
    }
}


fun findMedianSortedArrays1(nums1: IntArray, nums2: IntArray): Double {
    if (nums1.isEmpty())
        return findMedianSortedArray(nums2)
    else if (nums2.isEmpty())
        return findMedianSortedArray(nums1)

    val firstMedianPos = (nums1.size + nums2.size - 1) / 2
    var firstMedianPart: Int
    var secondMedianPart: Int

    firstMedianPart = searchElementToInsertTargetPosition(nums1, nums2, firstMedianPos)
    if (firstMedianPart == -1) {
        firstMedianPart = nums2[searchElementToInsertTargetPosition(nums2, nums1, firstMedianPos)]
    } else {
        firstMedianPart = nums1[firstMedianPart]
    }
    if ((nums1.size + nums2.size) % 2 == 0) {
        secondMedianPart = searchElementToInsertTargetPosition(nums1, nums2, firstMedianPos + 1)
        if (secondMedianPart == -1) {
            secondMedianPart = nums2[searchElementToInsertTargetPosition(nums2, nums1, firstMedianPos + 1)]
        } else {
            secondMedianPart = nums1[secondMedianPart]
        }
    } else {
        secondMedianPart = firstMedianPart
    }
    return (firstMedianPart + secondMedianPart) / 2.0
}

private fun searchElementToInsertTargetPosition(sourceArray: IntArray, targetArray: IntArray, targetPosition: Int): Int {
    var l = 0
    var r = sourceArray.size - 1
    while (r > l) {
        val m = (l + r) / 2
        val minInsertPos = searchInsertPosition(targetArray, sourceArray[m], true) + m
        val maxInsertPos = searchInsertPosition(targetArray, sourceArray[m], false) + m
        if (targetPosition in minInsertPos..maxInsertPos)
            return m
        else if (minInsertPos > targetPosition)
            r = m - 1
        else
            l = m + 1
    }
    return if (searchInsertPosition(targetArray, sourceArray[l]) + l == targetPosition)
        l
    else
        -1
}

private fun searchInsertPosition(array: IntArray, i: Int, insertToLeft: Boolean = true): Int {
    var l = 0
    var r = array.size - 1
    while (r > l) {
        val m = (l + r) / 2
        if (insertToLeft && i <= array[m]) {
            r = m - 1
        } else if (!insertToLeft && i < array[m]) {
            r = m - 1
        } else {
            l = m + 1
        }
    }
    return if (insertToLeft && i <= array[l])
        l
    else if (!insertToLeft && i < array[l])
        l
    else
        l + 1
}

private fun findMedianSortedArray(array: IntArray, l: Int = 0, r: Int = array.size - 1): Double {
    if (array.isEmpty())
        return 0.0
    val arrSize = r - l + 1
    val firstMedianPos = (l + r) / 2
    return if (arrSize % 2 == 0)
        (array[firstMedianPos] + array[firstMedianPos + 1]) / 2.0
    else
        array[firstMedianPos].toDouble()
}

fun main() {
//    val first = intArrayOf(10,11,12,13,14,14,15)
    val first = intArrayOf(1,3)
//    val second = intArrayOf(1,2,3,4,5)
    val second = intArrayOf(2)
    println(findMedianSortedArrays(first, second))
}