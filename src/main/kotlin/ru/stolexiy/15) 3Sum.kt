package ru.stolexiy

private object Solution15 {
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        val results = mutableListOf<List<Int>>()
        var i = 0
        while (i < nums.size) {
            val complement = twoSum(nums, i + 1, -nums[i])
            complement.forEach { p ->
                results.add(listOf(nums[i], p.first, p.second))
            }
            i = nextDistinct(nums, i)
        }
        return results
    }

    fun twoSum(nums: IntArray, start: Int, target: Int): List<Pair<Int, Int>> {
        val results = mutableListOf<Pair<Int, Int>>()
        var l = start
        var r = nums.size - 1
        while (l < r) {
            val sum = nums[l] + nums[r]
            if (sum == target) {
                results.add(nums[l] to nums[r])
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