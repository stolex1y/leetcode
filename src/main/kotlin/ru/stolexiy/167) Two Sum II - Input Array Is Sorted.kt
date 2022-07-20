package ru.stolexiy

fun twoSumWithSearch(numbers: IntArray, target: Int): IntArray {
    numbers.forEachIndexed { index, i ->
        val found = numbers.binarySearch(index + 1, target - i)
        if (found != -1)
            return intArrayOf(index + 1, found + 1)
    }
    return intArrayOf()
}

fun twoSumLinear(numbers: IntArray, target: Int): IntArray {
    var i2 = numbers.size - 1
    var i1 = 0
    numbers.forEach { _ ->
        if (i1 == i2)
            return@forEach

        val a2 = numbers[i2]
        val a1 = numbers[i1]
        if (a1 + a2 == target)
            return intArrayOf(i1 + 1, i2 + 1)
        else if (a1 + a2 > target)
            i2--
        else
            i1++
    }
    return intArrayOf()
}

private fun IntArray.binarySearch(start: Int = 0, target: Int): Int {
    var _start = start
    var end = size - 1
    var mid: Int
    while (end > _start) {
        mid = (end + _start) / 2
        if (this[mid] == target)
            return mid
        else if (target > this[mid])
            _start = mid + 1
        else
            end = mid - 1
    }
    return if (this[_start] == target)
        _start
    else
        -1
}