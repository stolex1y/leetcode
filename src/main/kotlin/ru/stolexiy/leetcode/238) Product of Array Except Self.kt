package ru.stolexiy.leetcode

private fun productExceptSelf(nums: IntArray): IntArray {
    val output = IntArray(nums.size) { 1 }
    for (i in 0 .. nums.size - 2) {
        output[i + 1] = nums[i] * output[i]
    }
    var curSuffix = 1
    for (i in nums.size - 1 downTo 1) {
        curSuffix *= nums[i]
        output[i - 1] *= curSuffix
    }
    return output
}

private fun productExceptSelf2(nums: IntArray): IntArray {
    val prefixProducts = IntArray(nums.size + 1) { 1 }
    val suffixProducts = IntArray(nums.size + 1) { 1 }
    nums.forEachIndexed { i, num ->
        prefixProducts[i + 1] = prefixProducts[i] * num
        val idxFromEnd = nums.size - 1 - i
        suffixProducts[idxFromEnd] = suffixProducts[idxFromEnd + 1] * nums[idxFromEnd]
    }
    val output = IntArray(nums.size) { 0 }
    for (i in output.indices) {
        output[i] = prefixProducts[i] * suffixProducts[i + 1]
    }
    return output
}

fun main() {
    val array = intArrayOf(1,2,3,4)
    println(productExceptSelf(array).joinToString(","))
}