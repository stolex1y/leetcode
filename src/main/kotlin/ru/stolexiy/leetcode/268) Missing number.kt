package ru.stolexiy.leetcode

private fun missingNumber(nums: IntArray): Int {
    if (nums.isEmpty())
        return 0
    val n = nums.size
    var x = 0
    for (i in 0..n)
        x = x.xor(i)
    for (i in nums.indices)
        x = x.xor(nums[i])
    return x
}