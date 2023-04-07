package ru.stolexiy.leetcode

fun rotate(nums: IntArray, k: Int) {
    val rotated = IntArray(nums.size)
    nums.forEachIndexed { index, i ->
        rotated[(index + k) % nums.size] = i
    }
    rotated.copyInto(nums)
}

fun rotateInPlace(nums: IntArray, k: Int) {
    val k1 = k % nums.size
    if (k1 == nums.size || k1 == 0)
        return
    var cur = 0
    var saved = nums[cur]
    var begin = 0
    for (i in nums.indices) {
        var next = (cur + k1) % nums.size
        val t = nums[next]
        nums[next] = saved
        if (next == begin && begin < nums.size - 1) {
            next = ++begin
            saved = nums[next]
        } else
            saved = t
        cur = next
    }
}

fun rotateReversing(nums: IntArray, k: Int) {
    val _k = k % nums.size
    nums.reversed()
    nums.reversed(0, _k)
    nums.reversed(start = _k)
}

//end exclusive
private fun IntArray.reversed(start: Int = 0, end: Int = size) {
    val mid = (end + start) / 2
    for (i in 0 until (mid - start)) {
        val t = this[i + start]
        this[i + start] = this[end - 1 - i]
        this[end - 1 - i] = t
    }
}