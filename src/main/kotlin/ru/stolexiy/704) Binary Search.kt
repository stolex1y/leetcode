package ru.stolexiy

private fun search(nums: IntArray, target: Int): Int {
    return search(nums, 0, nums.size - 1, target)
}

private fun search(nums: IntArray, start: Int, end: Int, target: Int): Int {
    if (end <= start) {
        return if (nums[start] == target)
            start
        else
            -1
    }
    val mid = (start + end) / 2
    return if (nums[mid] == target)
        mid
    else if (target > nums[mid])
        search(nums, mid + 1, end, target)
    else
        search(nums, start, mid - 1, target)
}

private fun search_iteration(nums: IntArray, target: Int): Int {
    var start = 0
    var end = nums.size - 1
    var mid: Int
    while (end > start) {
        mid = (end + start) / 2
        if (nums[mid] == target)
            return mid
        else if (target > nums[mid])
            start = mid + 1
        else
            end = mid - 1
    }
    return if (nums[start] == target)
        start
    else
        -1
}