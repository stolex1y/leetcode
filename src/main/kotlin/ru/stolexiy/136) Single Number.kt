package ru.stolexiy

private fun singleNumber(nums: IntArray): Int {
    var x = 0
    nums.forEach {
        x = x.xor(it)
    }
    return x
}