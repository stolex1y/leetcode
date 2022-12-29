package ru.stolexiy

import java.util.*

fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
    val insertPos = insertPositionSearch(arr, x)
    if (insertPos == 0)
        return arr.take(k)
    else if (insertPos == arr.size)
        return arr.takeLast(k)
    var p1 = insertPos - 1
    var p2 = insertPos
    val result = LinkedList<Int>()
    for (i in 0 until k) {
        if (p1 < 0)
            result.addLast(arr[p2++])
        else if (p2 >= arr.size)
            result.addFirst(arr[p1--])
        else {
            if (x - arr[p1] <= arr[p2] - x)
                result.addFirst(arr[p1--])
            else
                result.addLast(arr[p2++])
        }
    }
    return result.toList()
}

private fun insertPositionSearch(arr: IntArray, target: Int): Int {
    if (arr.isEmpty())
        return 0
    var l = 0
    var r = arr.size - 1
    while (r > l) {
        val m = (l + r) / 2
        if (arr[m] == target)
            return m
        else if (target < arr[m])
            r = m - 1
        else
            l = m + 1
    }
    return if (target >= arr[l])
        l + 1
    else
        l
}

