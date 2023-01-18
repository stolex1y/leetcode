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
    var left = 0
    var right = numbers.size - 1
    while (left < right) {
        val sum = numbers[left] + numbers[right]
        if (sum == target)
            return intArrayOf(left + 1, right + 1)
        else if (sum > target)
            right--
        else
            left++
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