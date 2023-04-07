package ru.stolexiy.leetcode

fun mergeInPlace(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    for (i in (m - 1) downTo 0) {
        nums1[i + n] = nums1[i]
    }
    var idx1 = 0
    var idx2 = 0
    for (i in 0 until (m+n)) {
        if (idx1 >= m) {
            nums1[i] = nums2[idx2++]
        } else if (idx2 >= n) {
            break
        } else {
            val curM = nums1[idx1 + n]
            val curN = nums2[idx2]
            if (curM <= curN) {
                nums1[i] = curM
                idx1++
            } else {
                nums1[i] = curN
                idx2++
            }
        }

    }
}

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    val temp = IntArray(m)
    for (i in 0 until m) {
        temp[i] = nums1[i]
    }
    var idx1 = 0
    var idx2 = 0
    for (i in 0 until (m+n)) {
        if (idx1 >= m) {
            nums1[i] = nums2[idx2++]
        } else if (idx2 >= n) {
            nums1[i] = temp[idx1++]
        } else {
            val curM = temp[idx1]
            val curN = nums2[idx2]
            if (curM <= curN) {
                nums1[i] = temp[idx1++]
            } else {
                nums1[i] = nums2[idx2++]
            }
        }

    }
}

fun mergeInPlace2(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var idx1 = m - 1
    var idx2 = n - 1
    for (i in (m + n - 1) downTo 0) {
        if (idx2 < 0)
            break
        else if (idx1 < 0 || nums2[idx2] > nums1[idx1]) {
            nums1[i] = nums2[idx2]
            idx2--
        } else {
            nums1[i] = nums1[idx1]
            idx1--
        }
    }
}

fun main() {
//    val nums1 = readln().split(",").map(String::toInt).toIntArray()
//    val m = readln().toInt()
//    val nums2 = readln().split(",").map(String::toInt).toIntArray()
//    val n = readln().toInt()
    val nums1 = intArrayOf(1,2,4,5,6,0)
    val nums2 = intArrayOf(3)
    val m = 5
    val n = 1
    merge(nums1, m, nums2, n)
    println(nums1.joinToString(","))
}