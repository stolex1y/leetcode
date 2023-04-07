package ru.stolexiy.leetcode

private object Solution18 {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        nums.sort()
        return kSum(nums, target.toLong(), 4)
    }

    fun kSum(nums: IntArray, target: Long, k: Int, start: Int = 0): List<List<Int>> {
        if (k == 2) {
            return twoSum(nums, target, start)
        }
        val results = mutableListOf<List<Int>>()
        var i = start
        while (i < nums.size) {
            kSum(nums, target - nums[i], k - 1, i + 1).forEach {
                it.toMutableList().apply { add(nums[i]) }.run(results::add)
            }
            i = nextDistinct(nums, i)
        }
        return results
    }

    fun twoSum(nums: IntArray, target: Long, start: Int): List<List<Int>> {
        val results = mutableListOf<List<Int>>()
        var l = start
        var r = nums.size - 1
        while (l < r) {
            val sum = nums[l] + nums[r]
            if (sum.toLong() == target) {
                results.add(listOf(nums[l], nums[r]))
                l = nextDistinct(nums, l)
                r = prevDistinct(nums, r)
            } else if (sum < target) {
                l = nextDistinct(nums, l)
            } else {
                r = prevDistinct(nums, r)
            }
        }
        return results
    }

    fun nextDistinct(nums: IntArray, start: Int): Int {
        var i = start + 1
        while (i < nums.size && nums[i] == nums[start])
            i++
        return i
    }

    fun prevDistinct(nums: IntArray, start: Int): Int {
        var i = start - 1
        while (i >= 0 && nums[i] == nums[start])
            i--
        return i
    }
}

fun main() {
    val nums = intArrayOf(-4,-3,-2,-1,0,0,1,2,3,4)
    val target = 0
    println(Solution18.fourSum(nums, target).size)
}