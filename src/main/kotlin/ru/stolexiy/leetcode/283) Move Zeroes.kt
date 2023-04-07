package ru.stolexiy.leetcode

fun moveZeroes(nums: IntArray) {
    var zeroesCount = 0
    nums.forEachIndexed { index, i ->
        if (i != 0) {
            if (zeroesCount > 0) {
                nums[index - zeroesCount] = nums[index]
                nums[index] = 0
            }
        } else
            zeroesCount++
    }
}

fun moveZeroesSimple(nums: IntArray) {
    val result = IntArray(nums.size) { 0 }
    var zeroesCount = 0
    nums.forEachIndexed { index, i ->
        if (i == 0)
            zeroesCount++
        else
            result[index - zeroesCount] = i
    }
    result.copyInto(nums)
}

//[1,0,0,2,3,0,4,0,5]
//NZP = 1
//idx = 3
//

//[1,0,2,0,0]